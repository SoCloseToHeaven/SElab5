package common;

import exceptions.InvalidFieldValueException;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;

public class Dragon {

    public static final Validator VALIDATOR = new Validator();


    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private final Long age; //Значение поля должно быть больше 0, Поле может быть null

    private final String description; //Поле не может быть null

    private final Integer wingspan; //Значение поля должно быть больше 0, Поле не может быть null

    private final DragonType type; //Поле не может быть null

    private final DragonCave cave; //Поле не может быть null

    private final static long ID_MIN_VALUE = 1L;

    public Dragon(String name, Coordinates coordinates, Long age, String description, Integer wingspan, DragonType type, DragonCave cave) {
        do {
            this.id = new Random().nextLong(Integer.MAX_VALUE) + ID_MIN_VALUE;
        } while (VALIDATOR.usedID.contains(this.id));
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.age = age;
        this.description = description;
        this.wingspan = wingspan;
        this.type = type;
        this.cave = cave;
        VALIDATOR.validate(this);
    }

    public Long getID() {
        return this.id;
    }

    public String getName() {return this.name;}

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getAge() {return this.age;}

    public String getDescription() {return this.description;}

    public Integer getWingspan() {return this.wingspan;}

    public DragonType getType() {return this.type;}

    public DragonCave getCave() {return this.cave;}

    @Override
    public String toString() {
        return "Name: %s; ID: %d; CreationDate: %s; Age: %d; Description: %s; Wingspan: %d; Type: %s; Cave: %s; Coordinates: %s".
                formatted(
                        getName(),
                        getID(),
                        getCreationDate().toString(),
                        getAge(), getDescription(),
                        getWingspan(),
                        getType().toString(),
                        getCave().toString(),
                        getCoordinates().toString());
    }

    public static class Validator implements AbstractValidator<Dragon> {

        private HashSet<Long> usedID = new HashSet<>();
        @Override
        public void validate(Dragon dragon) throws InvalidFieldValueException {
            validateID(dragon.id);
            validateName(dragon.name);
            validateCoordinates(dragon.coordinates);
            validateCreationDate(dragon.creationDate);
            validateAge(dragon.age);
            validateDescription(dragon.description);
            validateWingspan(dragon.wingspan);
            validateDragonCave(dragon.cave);
            validateDragonType(dragon.type);
        }

        public void validateID(Long id) throws InvalidFieldValueException{
            if (id.intValue() <= 0 || usedID.contains(id))
                throw new InvalidFieldValueException("Invalid ID value!");
            AbstractValidator.checkIfNull(id, "ID can't be null!");
            usedID.add(id);
        }

        public void validateName(String name) throws InvalidFieldValueException{
            if (name.isEmpty())
                throw new InvalidFieldValueException("Field name can't be empty!");
            AbstractValidator.checkIfNull(name, "Name can't be null!");
        }

        public void validateCoordinates(Coordinates coordinates) throws InvalidFieldValueException {
            AbstractValidator.checkIfNull(coordinates, "Coordinates can't be null!");
        }

        public void validateCreationDate(Date creationDate) throws InvalidFieldValueException {
            AbstractValidator.checkIfNull(creationDate, "Creation date can't be null!");
        }

        public void validateAge(Long age) throws InvalidFieldValueException {
            if (age == null) return;
            if (age <= 0) throw new InvalidFieldValueException("Age can't be lower than zero!");
        }

        public void validateDescription(String description) throws InvalidFieldValueException {
            AbstractValidator.checkIfNull(description, "Description can't be null!");
        }

        public void validateWingspan(Integer wingspan) throws InvalidFieldValueException {
            if (wingspan <= 0)
                throw new InvalidFieldValueException("Wingspan value can't be lower than zero!");
            AbstractValidator.checkIfNull(wingspan, "Wingspan can't be null!");
        }

        public void validateDragonType(DragonType dragonType) throws InvalidFieldValueException {
            AbstractValidator.checkIfNull(dragonType, "Dragon type can't be null!");
        }

        public void validateDragonCave(DragonCave dragonCave) throws InvalidFieldValueException {
            AbstractValidator.checkIfNull(dragonCave, "Dragon cave can't be null!");
        }

        public void clearUsedID() {
            usedID = new HashSet<>();
        }

        public void removeUsedID(Long id) {
            usedID.remove(id);
        }
    }
}