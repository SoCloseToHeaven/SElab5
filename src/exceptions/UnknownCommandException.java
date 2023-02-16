package exceptions;

public class UnknownCommandException extends Exception{

    public UnknownCommandException(String name) {
        super("There is no command with name: %s".formatted(name));
    }
}
