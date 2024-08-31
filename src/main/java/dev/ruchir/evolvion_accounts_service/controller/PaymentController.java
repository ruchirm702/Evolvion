package dev.ruchir.evolvion_accounts_service.controller;

import dev.ruchir.evolvion_accounts_service.DTOs.PaymentDTO;
import dev.ruchir.evolvion_accounts_service.service.Interface.PaymentService;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        logger.info("Request to create payment: {}", paymentDTO);
        try {
            PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
            return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
        } catch (PaymentCreationException e) {
            logger.error("Error creating payment", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        logger.info("Request to update payment with id: {} and data: {}", id, paymentDTO);
        try {
            PaymentDTO updatedPayment = paymentService.updatePayment(id, paymentDTO);
            return ResponseEntity.ok(updatedPayment);
        } catch (PaymentNotFoundException | PaymentUpdateException e) {
            logger.error("Error updating payment", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        logger.info("Request to get payment with id: {}", id);
        try {
            PaymentDTO paymentDTO = paymentService.getPaymentById(id);
            return ResponseEntity.ok(paymentDTO);
        } catch (PaymentNotFoundException e) {
            logger.error("Payment not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        logger.info("Request to get all payments");
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        logger.info("Request to delete payment with id: {}", id);
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.noContent().build();
        } catch (PaymentNotFoundException e) {
            logger.error("Error deleting payment", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
