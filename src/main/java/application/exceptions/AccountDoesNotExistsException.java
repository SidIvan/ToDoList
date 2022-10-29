package application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDoesNotExistsException extends Exception{
    private int id;

    public AccountDoesNotExistsException(int id) {
        this.id = id;
    }

    public String toString() {
        return "Account with id =" + id + "does not exists";
    }
}
