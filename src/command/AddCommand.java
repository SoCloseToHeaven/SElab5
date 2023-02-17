package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import common.Coordinates;
import common.Dragon;
import common.DragonCave;
import common.DragonType;
import exceptions.InvalidCommandArgumentException;
import util.TerminalColors;

public class AddCommand extends AbstractCommand {

    public AddCommand(BasicClientIO io, FileCollectionManager fcm) {
        super("add", io, fcm);
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1)
            throw new InvalidCommandArgumentException(this.getName());
        BasicClientIO io = getIO();
        String name = io.readLineWithNull("Type element's name: "); // name
        Integer x = Integer.parseInt(io.readLineWithNull("Type coordinate X: "));// CoordinateX
        double y = Double.parseDouble(io.readLine("Type coordinate Y: "));//CoordinateY
        Coordinates cords = new Coordinates(x,y);
        String ageString = io.readLineWithNull("Type age: ");//age can be null
        Long age = (ageString == null) ? null : Long.parseLong(ageString);
        String description = io.readLineWithNull("Type description: ");//description
        Integer wingspan = Integer.parseInt(io.readLineWithNull("Type wingspan: "));//wingspan
        long depth = Long.parseLong(io.readLine("Type cave's depth: "));//CaveDepth
        int numberOfTreasures = Integer.parseInt(io.read("Type number of treasures: "));//CaveNumberOfTreasures
        DragonCave cave = new DragonCave(depth, numberOfTreasures);
        DragonType type = DragonType.parseDragonType(io.readLine(
                "%s(%s): ".formatted("Type dragon's type", DragonType.stringValues())));//Dragon type
        Dragon dragon = new Dragon(name, cords, age, description, wingspan, type, cave);
        getFileCollectionManager().add(dragon);
        io.writeln(TerminalColors.setColor("Element added successfully",TerminalColors.BLUE));
    }

    @Override
    public String getUsage() {
        return "%s%s".formatted(
                TerminalColors.setColor("add {element}", TerminalColors.GREEN),
                TerminalColors.setColor(" - adds new collection element, fill the fields with values line by line",
                TerminalColors.BLUE));
    }
}
