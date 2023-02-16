package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class AddCommand extends AbstractCommand {

    public AddCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("add", io, fcm);
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("add {element}", TerminalColors.GREEN),
                TerminalColors.setColor(" - adds new collection element, fill the fields with values line by line",
                TerminalColors.BLUE));
    }
}
