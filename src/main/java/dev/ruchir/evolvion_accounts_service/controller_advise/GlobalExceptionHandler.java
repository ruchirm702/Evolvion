package dev.ruchir.evolvion_accounts_service.controller_advise;

import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Expense_Exceptions.ExpenseUpdateException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceUpdateException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseUpdateException.class)
    public ResponseEntity<ErrorResponse> handleExpenseUpdateException(ExpenseUpdateException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "EXP_UPDATE_ERR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExpenseNotFoundException(ExpenseNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "EXP_NOT_FOUND_ERR",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseCreationException.class)
    public ResponseEntity<ErrorResponse> handleExpenseCreationException(ExpenseCreationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "EXP_CREATION_ERR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvoiceNotFoundException(InvoiceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INV_NOT_FOUND_ERR",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(PaymentNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PAY_NOT_FOUND_ERR",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoiceCreationException.class)
    public ResponseEntity<ErrorResponse> handleInvoiceCreationException(InvoiceCreationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INV_CREATION_ERR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvoiceUpdateException.class)
    public ResponseEntity<ErrorResponse> handleInvoiceUpdateException(InvoiceUpdateException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INV_UPDATE_ERR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentCreationException.class)
    public ResponseEntity<ErrorResponse> handlePaymentCreationException(PaymentCreationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PAY_CREATION_ERR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentUpdateException.class)
    public ResponseEntity<ErrorResponse> handlePaymentUpdateException(PaymentUpdateException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "PAY_UPDATE_ERR",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "GLOBAL_ERR",
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                UUID.randomUUID().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
