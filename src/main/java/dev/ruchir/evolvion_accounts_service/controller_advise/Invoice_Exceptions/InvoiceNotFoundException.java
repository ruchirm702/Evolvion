package dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}