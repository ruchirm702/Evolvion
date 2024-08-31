package dev.ruchir.evolvion_accounts_service.controller;

import dev.ruchir.evolvion_accounts_service.DTOs.ExpenseDTO;
import dev.ruchir.evolvion_accounts_service.service.Interface.ExpenseService;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        logger.info("Request to create expense: {}", expenseDTO);
        try {
            ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
            return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
        } catch (ExpenseCreationException e) {
            logger.error("Error creating expense", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {
        logger.info("Request to update expense with id: {} and data: {}", id, expenseDTO);
        try {
            ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDTO);
            return ResponseEntity.ok(updatedExpense);
        } catch (ExpenseNotFoundException | ExpenseUpdateException e) {
            logger.error("Error updating expense", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        logger.info("Request to get expense with id: {}", id);
        try {
            ExpenseDTO expenseDTO = expenseService.getExpenseById(id);
            return ResponseEntity.ok(expenseDTO);
        } catch (ExpenseNotFoundException e) {
            logger.error("Expense not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        logger.info("Request to get all expenses");
        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        logger.info("Request to delete expense with id: {}", id);
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        } catch (ExpenseNotFoundException e) {
            logger.error("Error deleting expense", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
