package Exceptions;

public class IncorrectNameException extends RuntimeException {
    public IncorrectNameException() {
        super("invalid enter");
    }
}
