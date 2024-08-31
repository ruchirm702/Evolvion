package dev.ruchir.evolvion_accounts_service.controller;

import dev.ruchir.evolvion_accounts_service.DTOs.InvoiceDTO;
import dev.ruchir.evolvion_accounts_service.service.Interface.InvoiceService;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        logger.info("Request to create invoice: {}", invoiceDTO);
        try {
            InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
            return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
        } catch (InvoiceCreationException e) {
            logger.error("Error creating invoice", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceDTO invoiceDTO) {
        logger.info("Request to update invoice with id: {} and data: {}", id, invoiceDTO);
        try {
            InvoiceDTO updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);
            return ResponseEntity.ok(updatedInvoice);
        } catch (InvoiceNotFoundException | InvoiceUpdateException e) {
            logger.error("Error updating invoice", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        logger.info("Request to get invoice with id: {}", id);
        try {
            InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);
            return ResponseEntity.ok(invoiceDTO);
        } catch (InvoiceNotFoundException e) {
            logger.error("Invoice not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        logger.info("Request to get all invoices");
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        logger.info("Request to delete invoice with id: {}", id);
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build();
        } catch (InvoiceNotFoundException e) {
            logger.error("Error deleting invoice", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
