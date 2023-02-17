package clientio;

import util.LRUCache;

import java.io.*;

public class BasicClientIO {

    private final LRUCache<BufferedReader> cache = new LRUCache<>(1);
    private final BufferedWriter writer;

    public BasicClientIO() {
        this(System.in, System.out);
    }

    public BasicClientIO(InputStream in, OutputStream out) {
        this.cache.add(new BufferedReader(new InputStreamReader(in)));
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
        return cache.getArray().get(0);
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
            BufferedReader reader = getReader();
            do {
                input = reader.readLine();
                if (input == null) {
                    //stream ended
                    //move to next stream
                    reader.close();
                    cache.clear();
                    continue;
                }
                break;
            } while (cache.size() > 0);
            if (cache.size() == 0)
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
}
