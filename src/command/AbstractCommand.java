package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import commandmanagers.BasicCommandManager;

import java.util.Objects;

public abstract class AbstractCommand {

    private final String name;
    private final BasicClientIO io;
    private final FileCollectionManager fcm;

    private BasicCommandManager bcm;

    protected AbstractCommand(String name, BasicClientIO io, FileCollectionManager fcm) {
        this.name = name;
        this.io = io;
        this.fcm = fcm;
    }

    protected AbstractCommand(String name, BasicClientIO io, FileCollectionManager fcm, BasicCommandManager bcm) {
        this.name = name;
        this.io = io;
        this.fcm = fcm;
        this.bcm = bcm;
    }

    abstract public void execute(String[] args);

    public String getName() {
        return name;
    }

    public BasicClientIO getIO() {
        return this.io;
    }

    public FileCollectionManager getFileCollectionManager() {
        return this.fcm;
    }

    public BasicCommandManager getBasicCommandManager() {
        return bcm;
    }

    abstract public String getUsage();

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
