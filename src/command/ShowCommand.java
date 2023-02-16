package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class ShowCommand extends AbstractCommand{

    public ShowCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("show", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        getFileCollectionManager().getCollection().forEach(element -> getIO().writeln(element.toString()));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("show", TerminalColors.GREEN),
                TerminalColors.setColor(" - displays all elements of collection with their data",
                        TerminalColors.BLUE));
    }
}
