package dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String message) {
        super(message);
    }
    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}