package dev.ruchir.evolvion_accounts_service.service.Implementation;

import dev.ruchir.evolvion_accounts_service.DTOs.InvoiceDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceUpdateException;
import dev.ruchir.evolvion_accounts_service.mappers.InvoiceMapper;
import dev.ruchir.evolvion_accounts_service.models.Core.Invoice;
import dev.ruchir.evolvion_accounts_service.repository.InvoiceRepository;
import dev.ruchir.evolvion_accounts_service.service.Interface.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceDTO createInvoice(@Valid InvoiceDTO invoiceDTO) throws InvoiceCreationException {
        try {
            Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
            Invoice savedInvoice = invoiceRepository.save(invoice);
            return invoiceMapper.toDTO(savedInvoice);
        } catch (Exception e) {
            throw new InvoiceCreationException("Failed to create invoice", e);
        }
    }

    @Override
    public InvoiceDTO updateInvoice(Long id, @Valid InvoiceDTO invoiceDTO) throws InvoiceNotFoundException, InvoiceUpdateException {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + id));
        try {
            Invoice updatedInvoice = invoiceMapper.toEntity(invoiceDTO);
            updatedInvoice.setId(id);
            updatedInvoice.setCreatedAt(existingInvoice.getCreatedAt());
            Invoice savedInvoice = invoiceRepository.save(updatedInvoice);
            return invoiceMapper.toDTO(savedInvoice);
        } catch (Exception e) {
            throw new InvoiceUpdateException("Failed to update invoice", e);
        }
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + id));
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoiceMapper.toDTOList(invoices);
    }

    @Override
    public void deleteInvoice(Long id) throws InvoiceNotFoundException {
        if (!invoiceRepository.existsById(id)) {
            throw new InvoiceNotFoundException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }
}
