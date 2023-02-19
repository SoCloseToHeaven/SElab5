import clientio.BasicClientIO;
import commandmanagers.BasicCommandManager;


/**
 * Main-Class
 */
public class Client {

    /**
     * private class-constructor
     */
    private Client() {
        throw new UnsupportedOperationException("This is utility class!");
    }

    /**
     * main method, runs ConsoleClient
     * @param args command-string arguments
     */
    public static void main(String[] args) {
        ConsoleClient client = new ConsoleClient(new BasicClientIO(), new BasicCommandManager());
        client.run();
    }
}