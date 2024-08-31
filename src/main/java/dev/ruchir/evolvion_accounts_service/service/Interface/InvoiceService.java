package dev.ruchir.evolvion_accounts_service.service.Interface;

import dev.ruchir.evolvion_accounts_service.DTOs.InvoiceDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceUpdateException;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) throws InvoiceCreationException;
    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) throws InvoiceNotFoundException, InvoiceUpdateException;
    InvoiceDTO getInvoiceById(Long id) throws InvoiceNotFoundException;
    List<InvoiceDTO> getAllInvoices();
    void deleteInvoice(Long id) throws InvoiceNotFoundException;
}
