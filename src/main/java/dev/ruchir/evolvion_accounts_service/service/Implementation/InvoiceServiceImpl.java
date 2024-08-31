package dev.ruchir.evolvion_accounts_service.service.Implementation;

import dev.ruchir.evolvion_accounts_service.DTOs.InvoiceDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Invoice_Exceptions.InvoiceUpdateException;
import dev.ruchir.evolvion_accounts_service.mappers.InvoiceMapper;
import dev.ruchir.evolvion_accounts_service.models.Core.Invoice;
import dev.ruchir.evolvion_accounts_service.repository.InvoiceRepository;
import dev.ruchir.evolvion_accounts_service.service.Interface.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) throws InvoiceCreationException {
        logger.info("Creating invoice: {}", invoiceDTO);
        try {
            Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
            Invoice savedInvoice = invoiceRepository.save(invoice);
            logger.info("Invoice created successfully: {}", savedInvoice);
            return invoiceMapper.toDTO(savedInvoice);
        } catch (Exception e) {
            logger.error("Failed to create invoice", e);
            throw new InvoiceCreationException("Failed to create invoice", e);
        }
    }

    @Override
    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO) throws InvoiceNotFoundException, InvoiceUpdateException {
        logger.info("Updating invoice with id: {}", id);
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + id));
        try {
            Invoice updatedInvoice = invoiceMapper.toEntity(invoiceDTO);
            updatedInvoice.setId(id);
            updatedInvoice.setCreatedAt(existingInvoice.getCreatedAt());
            Invoice savedInvoice = invoiceRepository.save(updatedInvoice);
            logger.info("Invoice updated successfully: {}", savedInvoice);
            return invoiceMapper.toDTO(savedInvoice);
        } catch (Exception e) {
            logger.error("Failed to update invoice", e);
            throw new InvoiceUpdateException("Failed to update invoice", e);
        }
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) throws InvoiceNotFoundException {
        logger.info("Fetching invoice with id: {}", id);
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + id));
        logger.info("Fetched invoice: {}", invoice);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices() {
        logger.info("Fetching all invoices");
        List<Invoice> invoices = invoiceRepository.findAll();
        logger.info("Fetched {} invoices", invoices.size());
        return invoiceMapper.toDTOList(invoices);
    }

    @Override
    public void deleteInvoice(Long id) throws InvoiceNotFoundException {
        logger.info("Deleting invoice with id: {}", id);
        if (!invoiceRepository.existsById(id)) {
            logger.error("Invoice not found with id: {}", id);
            throw new InvoiceNotFoundException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
        logger.info("Invoice deleted successfully with id: {}", id);
    }
}
