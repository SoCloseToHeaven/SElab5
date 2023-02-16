package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import util.TerminalColors;

public class ExitCommand extends AbstractCommand {

    public ExitCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("exit", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        this.getIO().writeln(TerminalColors.setColor("Program stopped, thanks for using it :)", TerminalColors.GREEN));
        System.exit(-1);
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("exit", TerminalColors.GREEN),
                TerminalColors.setColor(" - stops this program",
                        TerminalColors.BLUE));
    }
}
