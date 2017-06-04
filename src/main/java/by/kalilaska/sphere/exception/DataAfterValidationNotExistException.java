package by.kalilaska.sphere.exception;

/**
 * Created by lovcov on 29.05.2017.
 */
public class DataAfterValidationNotExistException extends Exception{

    public DataAfterValidationNotExistException() {
    }

    public DataAfterValidationNotExistException(String message) {
        super(message);
    }

    public DataAfterValidationNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAfterValidationNotExistException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "DataAfterValidationNotExistException, msg: " + this.getMessage();
    }
}
