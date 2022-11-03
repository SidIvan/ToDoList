package application.services;

import application.entities.AccessTokenEntity;
import application.entities.AccountEntity;
import application.entities.RefreshTokenEntity;
import application.repositories.AccessTokenRepository;
import application.repositories.AccountRepository;
import application.repositories.RefreshTokenRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public String login(String jsonString) {
        try {
            AccountEntity accountInfo = new AccountEntity(jsonString);
            Optional<AccountEntity> account = accountRepository.findByLogin(accountInfo.getLogin());
            if (account.isEmpty() || !account.get().getPassword().equals(accountInfo.getPassword())) {
                return "permission denied";
            }
            AccessTokenEntity accessToken = accessTokenRepository.saveAndFlush(new AccessTokenEntity());
            accountRepository.updateAccess(account.get().getId(), accessToken.getId());
            RefreshTokenEntity refreshToken = refreshTokenRepository.saveAndFlush(new RefreshTokenEntity());
            accountRepository.updateRefresh(account.get().getId(), refreshToken.getId());
            return "permission accessed";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return "Unpredicted behavior";
    }

    public String access(String jsonString) {
        try {
            AccessTokenEntity accessToken = new AccessTokenEntity(jsonString);
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            if (!json.containsKey("login")) {
                return "permission denied (no login)";
            }
            Optional<AccountEntity> account = accountRepository.findByLogin(json.get("login").toString());
            if (account.isEmpty()) {
                return "permission denied (account does not exist)";
            }
            if (!account.get().getAccessToken().getValue().equals(accessToken.getValue())) {
                return "permission denied (wrong access token)";
            }
            return "permission accessed";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Unpredicted behavior";
    }

    public String refresh(String jsonString) {
        try {
            RefreshTokenEntity refreshToken = new RefreshTokenEntity(jsonString);
            JSONObject json = (JSONObject) JSONValue.parseWithException(jsonString);
            if (!json.containsKey("login")) {
                return "permission denied (no login)";
            }
            Optional<AccountEntity> account = accountRepository.findByLogin(json.get("login").toString());
            if (account.isEmpty()) {
                return "permission denied (account does not exist)";
            }
            if (!account.get().getRefreshToken().getValue().equals(refreshToken.getValue())) {
                return "permission denied (wrong access token)";
            }
            int accessId = account.get().getAccessToken().getId();
            int refreshId = account.get().getRefreshToken().getId();
            account.get().setAccessToken(null);
            account.get().setRefreshToken(null);
            accessTokenRepository.deleteById(accessId);
            refreshTokenRepository.deleteById(refreshId);
            AccessTokenEntity accessToken = accessTokenRepository.saveAndFlush(new AccessTokenEntity());
            accountRepository.updateAccess(account.get().getId(), accessToken.getId());
            RefreshTokenEntity newRefreshToken = refreshTokenRepository.saveAndFlush(new RefreshTokenEntity());
            accountRepository.updateRefresh(account.get().getId(), newRefreshToken.getId());
            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Unpredicted behavior";
    }
}
