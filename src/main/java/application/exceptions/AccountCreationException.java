package application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreationException extends Exception {
    private int exceptionType;

    public AccountCreationException(int exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String toString() {
        String res;
        switch (exceptionType) {
            case 0:
                res = "Login info does not exists";
                break;
            case 1:
                res = "Password info does not exists";
                break;
            default:
                res = "Unknown exception";
        }
        return res;
    }

}
