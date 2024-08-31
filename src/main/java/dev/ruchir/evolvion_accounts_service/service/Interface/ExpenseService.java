package dev.ruchir.evolvion_accounts_service.service.Interface;

import dev.ruchir.evolvion_accounts_service.DTOs.ExpenseDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseUpdateException;

import java.util.List;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO expenseDTO) throws ExpenseCreationException;
    ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) throws ExpenseNotFoundException, ExpenseUpdateException;
    ExpenseDTO getExpenseById(Long id) throws ExpenseNotFoundException;
    List<ExpenseDTO> getAllExpenses();
    void deleteExpense(Long id) throws ExpenseNotFoundException;
}
