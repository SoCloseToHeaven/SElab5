package exceptions;

public class InvalidFieldValueException extends RuntimeException {

    public InvalidFieldValueException(String description) {
        super(description);
    }
}
