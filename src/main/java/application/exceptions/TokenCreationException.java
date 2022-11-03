package application.exceptions;

public class TokenCreationException extends Exception{

    private int exceptionType;
    public TokenCreationException(int type) {
        exceptionType = type;
    }

    public String toString() {
        if(exceptionType == 0) {
            return "json does not include token value";
        }
        return "Wrong token format";
    }
}
