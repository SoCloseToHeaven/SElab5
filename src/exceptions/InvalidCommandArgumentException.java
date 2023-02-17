package exceptions;

public class InvalidCommandArgumentException extends RuntimeException{

    public InvalidCommandArgumentException(){}

    public InvalidCommandArgumentException(String message) {
        super("%s - %s".formatted(message, "Invalid arguments"));
    }

}
