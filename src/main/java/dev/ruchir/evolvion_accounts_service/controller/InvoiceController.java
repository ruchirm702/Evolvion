package dev.ruchir.evolvion_accounts_service.controller;

import dev.ruchir.evolvion_accounts_service.DTOs.InvoiceDTO;
import dev.ruchir.evolvion_accounts_service.service.Interface.InvoiceService;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            InvoiceDTO createdInvoice = invoiceService.createInvoice(invoiceDTO);
            return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
        } catch (InvoiceCreationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
        try {
            InvoiceDTO updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);
            return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
        } catch (InvoiceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InvoiceUpdateException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        try {
            InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);
            return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
        } catch (InvoiceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InvoiceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
