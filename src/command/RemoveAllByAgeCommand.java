package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import exceptions.InvalidCommandArgumentException;
import util.TerminalColors;

public class RemoveAllByAgeCommand extends AbstractCommand{

    public RemoveAllByAgeCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("remove_all_by_age", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2 || !args[1].matches("[1-9]\\d*"))
            throw new InvalidCommandArgumentException("Invalid Age value");
        long parsedAge = Long.parseLong(args[1]);
        if (getFileCollectionManager().getCollection().removeIf(element -> element.getAge().equals(parsedAge)))
            getIO().writeln(TerminalColors.setColor(
                    "%s - %d".formatted("Successfully removed elements with age", parsedAge),
                    TerminalColors.BLUE));
        else
            getIO().writeln(TerminalColors.setColor(
                    "%s - %d".formatted("No elements found with age", parsedAge),
                    TerminalColors.RED));
    }
    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("remove_all_by_age {age}", TerminalColors.GREEN),
                TerminalColors.setColor(" - removes all collection elements with the same age",
                        TerminalColors.BLUE));
    }
}
