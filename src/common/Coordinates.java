package common;

import com.google.gson.annotations.Expose;
import exceptions.InvalidFieldValueException;

public class Coordinates {

    public static final Validator VALIDATOR = new Validator();
    @Expose
    private Integer x; //Поле не может быть null
    @Expose
    private double y; //Максимальное значение поля: 36

    public Coordinates(Integer startX, double startY) {
        this.x = startX;
        this.y = startY;
        VALIDATOR.validate(this);
    }

/*    public Integer getX() {
        return x;
    }
    public double getY() {
        return y;
    }*/
    @Override
    public String toString() {
        return "[%d, %f]".formatted(x,y);
    }


    public static class Validator implements AbstractValidator<Coordinates> {

        @Override
        public void validate(Coordinates cords) throws InvalidFieldValueException {
            validateX(cords.x);
            validateY(cords.y);
        }

        public void validateX(Integer x) throws InvalidFieldValueException{
            AbstractValidator.checkIfNull(x, "Field X can't be null");
        }

        public void validateY(double y) throws InvalidFieldValueException{
            if (y >= 36.0)
                throw new InvalidFieldValueException("Field Y can't be greater than 36");
        }
    }
}
