package commandmanagers;

import command.AbstractCommand;
import exceptions.UnknownCommandException;
import util.LRUCache;

import java.util.Map;

public interface CommandManager {

    void manage(String commandString) throws UnknownCommandException;
    Map<String, ? extends AbstractCommand> getCommands();
    void addCommand(AbstractCommand command);

    LRUCache<AbstractCommand> getCommandHistory();
}
