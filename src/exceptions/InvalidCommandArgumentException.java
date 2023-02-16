package exceptions;

public class InvalidCommandArgumentException extends RuntimeException{

    public InvalidCommandArgumentException(){}

    public InvalidCommandArgumentException(String message) { super(message); }
}
