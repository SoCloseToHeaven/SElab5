package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class ClearCommand extends AbstractCommand{

    public ClearCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("clear", io, fcm);
    }

    @Override
    public void execute(String[] args) {

        this.getFileCollectionManager().clear();
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("clear", TerminalColors.GREEN),
                TerminalColors.setColor(" - removes all elements out of collection",
                        TerminalColors.BLUE));
    }
}
