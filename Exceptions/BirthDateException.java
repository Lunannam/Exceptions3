package Exceptions;

public class BirthDateException extends Exception {
    public BirthDateException(String message) {
        super("incorrect date of birth");
    }
}
