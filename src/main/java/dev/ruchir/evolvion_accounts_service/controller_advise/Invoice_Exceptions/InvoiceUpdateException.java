package dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions;

public class InvoiceUpdateException extends RuntimeException {
    public InvoiceUpdateException(String message) {
        super(message);
    }
    public InvoiceUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}