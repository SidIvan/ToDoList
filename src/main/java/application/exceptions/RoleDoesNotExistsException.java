package application.exceptions;

public class RoleDoesNotExistsException extends Exception {
    private int id;

    public RoleDoesNotExistsException(int id) {
        this.id = id;
    }

    public String toString() {
        return "Role with id = " + id + " does not exists";
    }
}
