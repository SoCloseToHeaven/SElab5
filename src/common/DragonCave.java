package common;

import exceptions.InvalidFieldValueException;

public class DragonCave {

    public static final Validator VALIDATOR = new Validator();

    private long depth;

    private int numberOfTreasures; //Значение поля должно быть больше 0

    public DragonCave(long startDepth, int startNumberOfTreasures) {
        this.depth = startDepth;
        this.numberOfTreasures = startNumberOfTreasures;
        VALIDATOR.validate(this);
    }

    public void setCave(long depth, int numberOfTreasures) {
        VALIDATOR.validateNumberOfTreasures(numberOfTreasures);
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    @Override
    public String toString() {
        return "[%d, %d]".formatted(depth, numberOfTreasures);
    }

    public static class Validator implements AbstractValidator<DragonCave> {
        @Override
        public void validate(DragonCave cave) throws InvalidFieldValueException {
            validateNumberOfTreasures(cave.numberOfTreasures);
        }

        public void validateNumberOfTreasures(int numberOfTreasures) throws InvalidFieldValueException{
            if (numberOfTreasures <= 0)
                throw new InvalidFieldValueException("Number of treasures can't be lower than zero");
        }
    }
}
