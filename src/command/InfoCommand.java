package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class InfoCommand extends AbstractCommand{

    public InfoCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("info", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        getIO().writeln(TerminalColors.setColor(getFileCollectionManager().toString(),TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("info", TerminalColors.GREEN),
                TerminalColors.setColor(" - displays information about the collection",
                        TerminalColors.BLUE));
    }
}
