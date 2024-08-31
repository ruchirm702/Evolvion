package dev.ruchir.evolvion_accounts_service.service.Implementation;

import dev.ruchir.evolvion_accounts_service.DTOs.ExpenseDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseUpdateException;
import dev.ruchir.evolvion_accounts_service.mappers.ExpenseMapper;
import dev.ruchir.evolvion_accounts_service.models.Core.Expense;
import dev.ruchir.evolvion_accounts_service.repository.ExpenseRepository;
import dev.ruchir.evolvion_accounts_service.service.Interface.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) throws ExpenseCreationException {
        logger.info("Creating expense: {}", expenseDTO);
        try {
            Expense expense = expenseMapper.toEntity(expenseDTO);
            Expense savedExpense = expenseRepository.save(expense);
            logger.info("Expense created successfully: {}", savedExpense);
            return expenseMapper.toDTO(savedExpense);
        } catch (Exception e) {
            logger.error("Failed to create expense", e);
            throw new ExpenseCreationException("Failed to create expense", e);
        }
    }

    @Override
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) throws ExpenseNotFoundException, ExpenseUpdateException {
        logger.info("Updating expense with id: {}", id);
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        try {
            Expense updatedExpense = expenseMapper.toEntity(expenseDTO);
            updatedExpense.setId(id);
            updatedExpense.setCreatedAt(existingExpense.getCreatedAt());
            Expense savedExpense = expenseRepository.save(updatedExpense);
            logger.info("Expense updated successfully: {}", savedExpense);
            return expenseMapper.toDTO(savedExpense);
        } catch (Exception e) {
            logger.error("Failed to update expense", e);
            throw new ExpenseUpdateException("Failed to update expense", e);
        }
    }

    @Override
    public ExpenseDTO getExpenseById(Long id) throws ExpenseNotFoundException {
        logger.info("Fetching expense with id: {}", id);
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        logger.info("Fetched expense: {}", expense);
        return expenseMapper.toDTO(expense);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        logger.info("Fetching all expenses");
        List<Expense> expenses = expenseRepository.findAll();
        logger.info("Fetched {} expenses", expenses.size());
        return expenseMapper.toDTOList(expenses);
    }

    @Override
    public void deleteExpense(Long id) throws ExpenseNotFoundException {
        logger.info("Deleting expense with id: {}", id);
        if (!expenseRepository.existsById(id)) {
            logger.error("Expense not found with id: {}", id);
            throw new ExpenseNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
        logger.info("Expense deleted successfully with id: {}", id);
    }
}
