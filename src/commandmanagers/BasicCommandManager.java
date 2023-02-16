package commandmanagers;

import command.*;
import exceptions.InvalidCommandArgumentException;
import exceptions.InvalidFieldValueException;
import exceptions.UnknownCommandException;
import util.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class BasicCommandManager implements CommandManager{
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();

    private static final int MAX_HISTORY_SIZE = 13;
    private final LRUCache<AbstractCommand> commandHistory = new LRUCache<>(MAX_HISTORY_SIZE) {
        @Override
        public String toString() {
            StringBuilder commandHistoryString = new StringBuilder();
            this.getArray().forEach(command -> commandHistoryString.append("%s ".formatted(command.getName())));
            return commandHistoryString.toString();
        }
    };

    @Override
    public void manage(String commandString)
            throws UnknownCommandException, InvalidCommandArgumentException, InvalidFieldValueException {
        String[] args = commandString.toLowerCase().split("\\s+");
        if (commands.get(args[0]) == null)
            throw new UnknownCommandException("%s - %s".formatted(args[0], "Type {help} to get all usable commands."));
        commands.get(args[0]).execute(args);
        commandHistory.add(commands.get(args[0]));
    }

    @Override
    public void addCommand(AbstractCommand command) {
        commands.put(command.getName(), command);
    }

    @Override
    public LRUCache<AbstractCommand> getCommandHistory() {
        return this.commandHistory;
    }

    @Override
    public Map<String, ? extends AbstractCommand> getCommands() {
        return commands;
    }
}
