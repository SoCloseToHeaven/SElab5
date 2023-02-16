import clientio.BasicClientIO;
import commandmanagers.BasicCommandManager;



public class Client {

    private Client() {
        throw new UnsupportedOperationException("This is utility class!");
    }

    public static void main(String[] args) {
        ConsoleClient client = new ConsoleClient(new BasicClientIO(), new BasicCommandManager());
        client.run();
    }
}