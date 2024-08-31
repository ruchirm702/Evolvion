package dev.ruchir.evolvion_accounts_service.service.Implementation;

import dev.ruchir.evolvion_accounts_service.DTOs.ExpenseDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseUpdateException;
import dev.ruchir.evolvion_accounts_service.mappers.ExpenseMapper;
import dev.ruchir.evolvion_accounts_service.models.Core.Expense;
import dev.ruchir.evolvion_accounts_service.repository.ExpenseRepository;
import dev.ruchir.evolvion_accounts_service.service.Interface.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public ExpenseDTO createExpense(@Valid ExpenseDTO expenseDTO) throws ExpenseCreationException {
        try {
            Expense expense = expenseMapper.toEntity(expenseDTO);
            Expense savedExpense = expenseRepository.save(expense);
            return expenseMapper.toDTO(savedExpense);
        } catch (Exception e) {
            throw new ExpenseCreationException("Failed to create expense", e);
        }
    }

    @Override
    public ExpenseDTO updateExpense(Long id, @Valid ExpenseDTO expenseDTO) throws ExpenseNotFoundException, ExpenseUpdateException {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        try {
            Expense updatedExpense = expenseMapper.toEntity(expenseDTO);
            updatedExpense.setId(id);
            updatedExpense.setCreatedAt(existingExpense.getCreatedAt());
            Expense savedExpense = expenseRepository.save(updatedExpense);
            return expenseMapper.toDTO(savedExpense);
        } catch (Exception e) {
            throw new ExpenseUpdateException("Failed to update expense", e);
        }
    }

    @Override
    public ExpenseDTO getExpenseById(Long id) throws ExpenseNotFoundException {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        return expenseMapper.toDTO(expense);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenseMapper.toDTOList(expenses);
    }

    @Override
    public void deleteExpense(Long id) throws ExpenseNotFoundException {
        if (!expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }
}
