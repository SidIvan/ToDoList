package application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleCreationException extends Exception {

    private int exceptionType;

    public RoleCreationException(int exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String toString() {
        String res;
        switch (exceptionType) {
            case 0:
                res = "Role title does not entered";
            default:
                res = "Unknown exception";
        }
        return res;
    }
}
