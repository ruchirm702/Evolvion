package dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions;

public class ExpenseCreationException extends RuntimeException {
    public ExpenseCreationException(String message) {
        super(message);
    }

    public ExpenseCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}

