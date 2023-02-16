package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class GroupCountingByCreationDateCommand extends AbstractCommand {

    public GroupCountingByCreationDateCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("group_counting_by_creation_date", io, fcm);
    }


    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor(
                        "group_counting_by_creation_date",
                        TerminalColors.GREEN),
                TerminalColors.setColor(
                        " - splits collection to groups with the same creation date, then prints amount of them",
                        TerminalColors.BLUE));
    }
}
