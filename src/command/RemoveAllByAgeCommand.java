package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class RemoveAllByAgeCommand extends AbstractCommand{

    public RemoveAllByAgeCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("remove_all_by_age", io, fcm);
    }

    @Override
    public void execute(String[] args) {

    }
    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("execute_script {filepath}", TerminalColors.GREEN),
                TerminalColors.setColor(" - runs script from your file, {filepath} have to be absolute",
                        TerminalColors.BLUE));
    } // rewrite
}
