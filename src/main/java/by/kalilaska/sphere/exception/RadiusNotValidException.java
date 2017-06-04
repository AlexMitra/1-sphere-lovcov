package by.kalilaska.sphere.exception;

/**
 * Created by lovcov on 29.05.2017.
 */
public class RadiusNotValidException extends Exception{

    public RadiusNotValidException() {
    }

    public RadiusNotValidException(String message) {
        super(message);
    }

    public RadiusNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RadiusNotValidException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "RadiusNotValidException, msg: " + this.getMessage();
    }
}
