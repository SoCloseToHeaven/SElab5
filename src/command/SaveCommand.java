package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class SaveCommand extends AbstractCommand{

    public SaveCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("save", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        if (this.getFileCollectionManager().save())
            this.getIO().writeln(TerminalColors.setColor("Collection was successfully saved", TerminalColors.GREEN));
        else
            this.getIO().writeln(TerminalColors.setColor("Collection wasn't saved, try again", TerminalColors.RED));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("save", TerminalColors.GREEN),
                TerminalColors.setColor(" - saves collection to file",
                        TerminalColors.BLUE));
    }
}
