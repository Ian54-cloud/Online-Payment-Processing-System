package Exceptions;

public class IncorretCardNumberException extends RuntimeException {
    public IncorretCardNumberException(String message) {
        super(message);
    }
}
