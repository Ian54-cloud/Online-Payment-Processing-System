package Exceptions;

public class EmptyAccountHolder extends RuntimeException {
    public EmptyAccountHolder(String message) {
        super(message);
    }
}
