package common;

import exceptions.InvalidFieldValueException;

@FunctionalInterface
public interface AbstractValidator<T> {
    void validate(T somethingToValidate) throws InvalidFieldValueException;

    static void checkIfNull(Object obj, String fieldName) throws  InvalidFieldValueException {
        if (obj == null)
            throw new InvalidFieldValueException(fieldName);
    }
}
