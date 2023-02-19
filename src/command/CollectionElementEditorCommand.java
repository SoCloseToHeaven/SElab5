package command;

import clientio.BasicClientIO;
import collectionmanagers.FileCollectionManager;
import common.Coordinates;
import common.Dragon;
import common.DragonCave;
import common.DragonType;
import exceptions.InvalidFieldValueException;
import util.TerminalColors;


public abstract class CollectionElementEditorCommand extends AbstractCommand {

    private final BasicClientIO localIO = getIO();

    public CollectionElementEditorCommand(String name, BasicClientIO io, FileCollectionManager fcm) {
        super(name, io, fcm);
    }


    public String inputName() {
        try {
            String name = localIO.readLine("Type element's name: ");
            Dragon.VALIDATOR.validateName(name);
            return name;
        } catch (InvalidFieldValueException e) {
            localIO.writeln(e.getMessage());
            return inputName();
        }
    }

    public Integer inputX() {
        try {
            Integer x = Integer.parseInt(localIO.readLineWithNull("Type coordinate X: "));
            Coordinates.VALIDATOR.validateX(x);
            return x;
        } catch (NumberFormatException|InvalidFieldValueException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputX();
        }
    }

    public double inputY() {
        try {
            double y = Double.parseDouble(localIO.readLine("Type coordinate Y: "));
            Coordinates.VALIDATOR.validateY(y);
            return y;
        } catch (NumberFormatException|InvalidFieldValueException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputY();
        }
    }

    public Long inputAge() {
        try {
            String ageString = localIO.readLineWithNull("Type age: "); //age can be null
            Long age = (ageString == null) ? null : Long.parseLong(ageString);
            Dragon.VALIDATOR.validateAge(age);
            return age;
        } catch (NumberFormatException|InvalidFieldValueException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputAge();
        }
    }

    public String inputDescription() {
        try {
            String description = localIO.readLineWithNull("Type description: ");//description
            Dragon.VALIDATOR.validateDescription(description);
            return description;
        } catch (InvalidFieldValueException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputDescription();
        }
    }

    public Integer inputWingspan() {
        try {
            Integer wingspan = Integer.parseInt(localIO.readLineWithNull("Type wingspan: "));//wingspan
            Dragon.VALIDATOR.validateWingspan(wingspan);
            return wingspan;
        } catch (InvalidFieldValueException|NumberFormatException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputWingspan();
        }
    }

    public long inputDepth() {
        try {
            return Long.parseLong(localIO.readLine("Type cave's depth: "));
        } catch (NumberFormatException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputDepth();
        }
    }
    public int inputNumberOfTreasures() {
        try {
            int numberOfTreasures = Integer.parseInt(localIO.read("Type number of treasures: "));//CaveNumberOfTreasures
            DragonCave.VALIDATOR.validateNumberOfTreasures(numberOfTreasures);
            return numberOfTreasures;
        } catch (NumberFormatException|InvalidFieldValueException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputNumberOfTreasures();
        }
    }

    public DragonType inputDragonType() {
        try {
            return DragonType.parseDragonType(localIO.readLine(
                    "%s(%s): ".formatted("Type dragon's type", DragonType.stringValues())));
        } catch (UnsupportedOperationException e) {
            localIO.writeln(TerminalColors.setColor(e.getMessage(), TerminalColors.RED));
            return inputDragonType();
        }
    }
}
