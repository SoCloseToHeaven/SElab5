package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import commandmanagers.BasicCommandManager;
import exceptions.ExecutingScriptException;
import exceptions.InvalidCommandArgumentException;
import exceptions.InvalidFieldValueException;
import exceptions.UnknownCommandException;
import util.TerminalColors;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class ExecuteScriptCommand extends AbstractCommand {

    private static final HashSet<File> OPENED_FILES = new HashSet<>();

    public ExecuteScriptCommand(BasicClientIO io, FileCollectionManager fcm, BasicCommandManager bcm) {
        super("execute_script", io, fcm, bcm);
    }

    @Override
    public void execute(String[] args){
        if (args.length != 2)
            throw new InvalidCommandArgumentException(this.getName());
        File file = new File(args[1]);
        if (OPENED_FILES.contains(file))
            throw new ExecutingScriptException("{execute_script} is unable in opened file to avoid recursion");
        LocalScriptManager lsm = new LocalScriptManager(file);
        lsm.run();
    }
    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("execute_script {filepath}", TerminalColors.GREEN),
                TerminalColors.setColor(" - runs script from your file, {filepath} have to be absolute",
                        TerminalColors.BLUE));
    }

    private class LocalScriptManager {

        private final File file;

        private final BasicCommandManager bcm = getBasicCommandManager();

        private final BasicClientIO io = getIO();

        private final StringBuilder fileData = new StringBuilder();
        LocalScriptManager(File file) {
            if (!file.isAbsolute())
                throw new InvalidCommandArgumentException("Filepath is not absolute!");
            this.file = file;
        }


        private void readFromFile() {
            try {
                io.add(new BufferedReader(new InputStreamReader(new FileInputStream(file))){
                    @Override
                    public void close() throws IOException {
                        super.close();
                        ExecuteScriptCommand.OPENED_FILES.remove(file);
                    }
                });
                fileData.append(io.read());
            } catch (IOException e) {
                System.err.printf("%s: %s%n", "Something went wrong while reading file", e.getMessage());
            }
        }

        private String[] getArgs() {
            this.readFromFile();
            return this.fileData.toString().split("\n");
        }

        public void run() {
            OPENED_FILES.add(file);
            String[] args = getArgs();
            Arrays.stream(args).forEach(arguments -> {
                try {
                    bcm.manage(arguments);
                } catch (UnknownCommandException |
                         InvalidFieldValueException |
                         InvalidCommandArgumentException |
                         NullPointerException |
                         NumberFormatException |
                         UnsupportedOperationException e) {
                    io.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
                }
            });
        }
    }
}
