package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import commandmanagers.BasicCommandManager;
import exceptions.InvalidCommandArgumentException;
import util.TerminalColors;

import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {

    private static boolean inProgress = false;
    public ExecuteScriptCommand(BasicClientIO io, FileCollectionManager fcm, BasicCommandManager bcm) {
        super("execute_script", io, fcm, bcm);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2)
            throw new InvalidCommandArgumentException();
        if (inProgress)
            throw new UnsupportedOperationException("{execute_script} is unable while executing script to avoid recursion");
        LocalScriptManager lsm = new LocalScriptManager(new File(args[1]));


    }
    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("execute_script {filepath}", TerminalColors.GREEN),
                TerminalColors.setColor(" - runs script from your file, {filepath} have to be absolute",
                        TerminalColors.BLUE));
    }

    private static class LocalScriptManager {

        private File file;

        private final StringBuilder fileData = new StringBuilder();
        LocalScriptManager(File file) {
            if (!file.isAbsolute())
                throw new InvalidCommandArgumentException("Filepath is not absolute!");
            this.file = file;
        }


        private void readFromFile() {
            try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                in.lines().forEach(this.fileData::append);
            } catch (IOException e) {
                System.err.printf("%s: %s%n", "Something went wrong while reading file", e.getMessage());
            }
        }


    }
}
