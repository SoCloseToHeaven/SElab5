package clientio;

import java.io.*;
import java.util.LinkedList;

/**
 * Class is provided for processing user's input and output stream
 */
public class BasicClientIO {
    /**
     * stack of input streams
     */

    private final LinkedList<BufferedReader> stack = new LinkedList<>();

    /**
     * output stream writer
     */
    private final BufferedWriter writer;

    /**
     * default BasicClientIO constructor
     */
    public BasicClientIO() {
        this(System.in, System.out);
    }

    /**
     * adds new input stream to streams {@link #stack} and output stream to {@link #writer}
     * @param in input stream
     * @param out output stream
     */

    public BasicClientIO(InputStream in, OutputStream out) {
        this.stack.add(new BufferedReader(new InputStreamReader(in)));
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
    }


    /**
     * writes object to output stream
     * @param obj any java object
     */
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

    /**
     * the same method as {@link #write} but next output starts with new line
     * @param obj any java object
     */
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

    /**
     *
     * @return first element of {@link #stack}
     */
    private BufferedReader getReader() {
        return stack.get(0);
    }

    /**
     *
     * @return String read from input stream
     */
    public String readLine() {
        try {
            return getReader().readLine();
        } catch (IOException e) {
            return "%s: %s".formatted("Something went wrong with userIO", e.getMessage());
        }
    }

    /**
     *
     * @return reads symbols from input stream until it ends
     */
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

    /**
     *
     * @param message that will be written in output stream before reading data from input stream
     * @return String read from input stream
     */
    public String read(String message) {
        this.write(message);
        return this.read();
    }
    /**
     * the same method as {@link #read()} but it reads only one line
     * @param message that will be written in output stream before reading data from input stream
     * @return String read from input stream
     */
    public String readLine(String message) {
        this.write(message);
        return this.readLine();
    }
    /**
     * the same method as {@link #readLine()} but if String is empty it'll return null
     * @return String read from input stream
     */
    public String readLineWithNull() {
        try {
            String line = getReader().readLine();
            return (line.isEmpty()) ? null : line;
        } catch (IOException e) {
            return "%s: %s".formatted("Something went wrong with userIO", e.getMessage());
        }
    }

    /**
     * the same method as {@link #readLine()} but if String is empty it'll return null
     * @param message writes this message in output stream before reading from input stream
     * @return String read from input stream
     */
    public String readLineWithNull(String message) {
        this.write(message);
        return this.readLineWithNull();
    }

    /**
     * @param reader will be added to {@link #stack}
     */
    public void add(BufferedReader reader) {
        stack.add(reader);
    }

    /**
     * removes last stream from {@link #stack}
     */
    public void removeAndClose() {
        try {
            stack.removeLast().close();
        } catch (IOException e) {
            System.err.printf("%s: %s%n", "Something went wrong with userIO", e.getMessage());
        }
    }
}
