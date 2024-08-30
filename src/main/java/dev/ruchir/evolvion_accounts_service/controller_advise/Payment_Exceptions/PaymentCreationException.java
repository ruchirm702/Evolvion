package dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions;

public class PaymentCreationException extends RuntimeException {
    public PaymentCreationException(String message) {
        super(message);
    }
}