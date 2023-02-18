import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import com.google.gson.JsonParseException;
import commandmanagers.BasicCommandManager;
import command.*;
import exceptions.ExecutingScriptException;
import exceptions.InvalidCommandArgumentException;
import exceptions.InvalidFieldValueException;
import exceptions.UnknownCommandException;
import util.TerminalColors;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class ConsoleClient {

    private final BasicClientIO io;

    private final BasicCommandManager cm;

    private static final String INPUT_PREFIX = TerminalColors.setColor("> ", TerminalColors.CYAN);


    private FileCollectionManager fcm;
    public ConsoleClient(BasicClientIO io, BasicCommandManager cm){
        this.io = io;
        this.cm = cm;
    }

    public void run() {
        addBasicCommands(this.io, this.cm, this.fcm);
        io.writeln(TerminalColors.setColor("Type the name of environment variable(selabjson): ", TerminalColors.GREEN));
        try {
            String filePath = System.getenv(io.readLine(INPUT_PREFIX));
            this.fcm = new FileCollectionManager(filePath);
            fcm.open();
        } catch (FileNotFoundException
                 | NullPointerException
                 | JsonParseException e) {
            io.write(TerminalColors.setColor("%s - exiting program".formatted(e.getMessage()), TerminalColors.RED));
            System.exit(-1);
        }
        this.addBasicCommands(this.io, this.cm, this.fcm);
        io.writeln(TerminalColors.setColor("Collection with these elements was loaded:",TerminalColors.BLUE));
        fcm.getCollection().forEach(element -> io.writeln(element.toString()));
        io.writeln(TerminalColors.setColor(
                "Type [help] to get information about all usable commands or type another command:",
                TerminalColors.GREEN));
        String input;
        while ((input = io.read(INPUT_PREFIX)) != null) {
            try {
                cm.manage(input);
            } catch (UnknownCommandException |
                     InvalidFieldValueException |
                     InvalidCommandArgumentException |
                     NullPointerException |
                     NumberFormatException |
                     ExecutingScriptException |
                     UnsupportedOperationException e) {
                io.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            }
        }
    }


    private void addBasicCommands(BasicClientIO io, BasicCommandManager bcm, FileCollectionManager fcm) {
        Arrays.asList(
                new AddCommand(io, fcm),
                new ClearCommand(io, fcm),
                new CountLessThanAgeCommand(io, fcm),
                new ExecuteScriptCommand(io, fcm, bcm),
                new ExitCommand(io, fcm),
                new GroupCountingByCreationDateCommand(io, fcm),
                new HelpCommand(io, fcm, bcm),
                new HistoryCommand(io, fcm, bcm),
                new InfoCommand(io, fcm),
                new RemoveAllByAgeCommand(io, fcm),
                new RemoveAtCommand(io, fcm),
                new RemoveByIDCommand(io, fcm),
                new SaveCommand(io, fcm),
                new ShowCommand(io, fcm),
                new SortCommand(io, fcm),
                new UpdateCommand(io, fcm)).forEach(bcm::addCommand);
    }
}
