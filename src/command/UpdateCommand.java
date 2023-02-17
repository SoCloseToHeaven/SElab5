package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import common.Dragon;
import common.DragonType;
import exceptions.InvalidCommandArgumentException;
import util.TerminalColors;

public class UpdateCommand extends AbstractCommand{

    public UpdateCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("update", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2 || !args[1].matches("[1-9]\\d*"))
            throw new InvalidCommandArgumentException("Invalid ID");
        long id = Long.parseLong(args[1]);
        BasicClientIO io = getIO();
        String name = io.readLineWithNull("Type element's name: "); // name
        Integer x = Integer.parseInt(io.readLineWithNull("Type coordinate X: "));// CoordinateX
        double y = Double.parseDouble(io.readLine("Type coordinate Y: "));//CoordinateY
        String ageString = io.readLineWithNull("Type age: ");//age can be null
        Long age = (ageString == null) ? null : Long.parseLong(ageString);
        String description = io.readLineWithNull("Type description: ");//description
        Integer wingspan = Integer.parseInt(io.readLineWithNull("Type wingspan: "));//wingspan
        long depth = Long.parseLong(io.readLine("Type cave's depth: "));//CaveDepth
        int numberOfTreasures = Integer.parseInt(io.read("Type number of treasures: "));//CaveNumberOfTreasures
        DragonType type = DragonType.parseDragonType(io.readLine(
                "%s(%s): ".formatted("Type dragon's type", DragonType.stringValues())));//Dragon type
        Dragon dragon = getFileCollectionManager().getByID(id);
        dragon.update(name,x,y,age, description,wingspan,type,depth,numberOfTreasures);
        io.writeln(TerminalColors.setColor("Element updated successfully",TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("update {ID} {element}", TerminalColors.GREEN),
                TerminalColors.setColor(" - updates data(in the same way as in {add} command) with the same ID",
                        TerminalColors.BLUE));
    }
}
