package by.kalilaska.sphere.exception;

/**
 * Created by lovcov on 29.05.2017.
 */
public class CentreCoordinateNotValidException extends Exception{

    public CentreCoordinateNotValidException() {
    }

    public CentreCoordinateNotValidException(String message) {
        super(message);
    }

    public CentreCoordinateNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CentreCoordinateNotValidException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "CentreCoordinateNotValidException, msg: " + this.getMessage();
    }
}
