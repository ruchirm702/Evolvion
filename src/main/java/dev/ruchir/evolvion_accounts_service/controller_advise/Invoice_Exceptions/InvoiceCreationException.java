package dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions;

public class InvoiceCreationException extends RuntimeException {
    public InvoiceCreationException(String message) {
        super(message);
    }
    public InvoiceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}