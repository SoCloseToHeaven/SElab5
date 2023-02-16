package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class SortCommand extends AbstractCommand{

    public SortCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("sort", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        getFileCollectionManager().sort();
        getIO().writeln(TerminalColors.setColor("Collection was sorted by ID", TerminalColors.GREEN));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("sort", TerminalColors.GREEN),
                TerminalColors.setColor(" - sorts the collection in ascending order by ID",
                        TerminalColors.BLUE));
    }
}
