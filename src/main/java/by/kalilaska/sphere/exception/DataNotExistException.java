package by.kalilaska.sphere.exception;

/**
 * Created by lovcov on 25.05.2017.
 */
public class DataNotExistException extends Exception {

    public DataNotExistException() {
    }

    public DataNotExistException(String message) {
        super(message);
    }

    public DataNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotExistException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "DataNotExistException, msg: " + this.getMessage();
    }
}
