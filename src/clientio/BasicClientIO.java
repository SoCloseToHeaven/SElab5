package clientio;

import util.LRUCache;

import java.io.*;
import java.util.LinkedList;

public class BasicClientIO {

    private final LinkedList<BufferedReader> stack = new LinkedList<>();
    private final BufferedWriter writer;

    public BasicClientIO() {
        this(System.in, System.out);
    }

    public BasicClientIO(InputStream in, OutputStream out) {
        this.stack.add(new BufferedReader(new InputStreamReader(in)));
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
    }


    public void write(Object obj) {
        try {
            writer.write(obj.toString());
            writer.flush();
        } catch (IOException e) {
            System.err.printf("%s: %s%n", "Something went wrong with userIO", e.getMessage());
        } catch (NullPointerException e) {
            this.write("null");
        }
    }

    public void writeln(Object obj) {
        try {
            writer.write(obj.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.printf("%s: %s%n", "Something went wrong with userIO", e.getMessage());
        } catch (NullPointerException e) {
            this.writeln("null");
        }
    }

    private BufferedReader getReader() {
        return stack.get(0);
    }

    public String readLine() {
        try {
            return getReader().readLine();
        } catch (IOException e) {
            return "%s: %s".formatted("Something went wrong with userIO", e.getMessage());
        }
    }


    public String read() {
        try {
            String input;
            BufferedReader reader = stack.getLast();
            do {
                input = reader.readLine();
                if (input == null) {
                    //stream ended
                    //move to next stream
                    removeAndClose();
                    continue;
                }
                break;
            } while (stack.size() > 0);
            if (stack.size() == 0)
                System.exit(-1);
            return input;
        } catch (IOException e) {
            return "%s: %s".formatted("Something went wrong with userIO", e.getMessage());
        }
    }

    public String read(String message) {
        this.write(message);
        return this.read();
    }

    public String readLine(String message) {
        this.write(message);
        return this.readLine();
    }

    public String readLineWithNull() {
        try {
            String line = getReader().readLine();
            return (line.isEmpty()) ? null : line;
        } catch (IOException e) {
            return "%s: %s".formatted("Something went wrong with userIO", e.getMessage());
        }
    }

    public String readLineWithNull(String message) {
        this.write(message);
        return this.readLineWithNull();
    }

    public void add(BufferedReader reader) {
        stack.add(reader);
    }

    public void removeAndClose() {
        try {
            stack.getLast().close();
            stack.removeLast();
        } catch (IOException e) {

        }
    }
}
