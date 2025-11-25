package Exceptions;

public class IncorrectPaymentMethodException extends Exception {
    public IncorrectPaymentMethodException(String message) {
        super(message);
    }
}
