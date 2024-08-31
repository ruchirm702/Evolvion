package dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions;

public class ExpenseUpdateException extends RuntimeException {
    public ExpenseUpdateException(String message) {
        super(message);
    }
    public ExpenseUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}