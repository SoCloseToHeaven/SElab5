package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class UpdateCommand extends AbstractCommand{

    public UpdateCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("update", io, fcm);
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("update {ID} {element}", TerminalColors.GREEN),
                TerminalColors.setColor(" - updates data(in the same way as in {add} command) with the same ID",
                        TerminalColors.BLUE));
    }
}
