package dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}