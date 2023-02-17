package util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import common.Dragon;
import exceptions.InvalidFieldValueException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JSONFileManager {

    private final File file;


    public final StringBuilder fileData = new StringBuilder();

    public final JSONCollectionReader parser = new JSONCollectionReader();

    private final JSONCollectionWriter writer = new JSONCollectionWriter();

    public JSONFileManager(String inputFileName) throws FileNotFoundException{
        file = new File(inputFileName);
        if (!file.exists())
            throw new FileNotFoundException("File with name/path: %s doesn't exist".formatted(inputFileName));
    }

    public ArrayList<Dragon> readFromFile(){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            in.lines().forEach(this.fileData::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Dragon> dragons = parser.parse(this.fileData.toString());
        dragons.removeIf(dragon -> {
            try {
                Dragon.VALIDATOR.validate(dragon);
            } catch (InvalidFieldValueException e) {
                System.err.printf("Object: %s - can't be added due to invalid field values%n", dragon.toString());
                return true;
            }
            return false;
        });
        return dragons; // refactor
    }

    public boolean saveToFile(ArrayList<Dragon> collection) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(writer.parse(collection).getBytes());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private static class JSONCollectionWriter {

        private final Gson gson = new Gson();

        public String parse(ArrayList<Dragon> collection) {
            return gson.toJson(collection);
        }
    }

    private static class JSONCollectionReader {

        private final Gson gson = new Gson();
        public ArrayList<Dragon> parse(String jsonString) throws JsonParseException{
            Dragon[] dragons;
            try {
                dragons = gson.fromJson(jsonString, Dragon[].class);
            } catch (NumberFormatException| JsonSyntaxException exception) {
                throw new JsonParseException("Invalid file structure or value for fields");
            }
            return new ArrayList<>(Arrays.asList(dragons));
        }
    }
}
