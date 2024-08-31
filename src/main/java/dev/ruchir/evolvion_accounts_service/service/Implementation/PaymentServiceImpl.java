package dev.ruchir.evolvion_accounts_service.service.Implementation;

import dev.ruchir.evolvion_accounts_service.DTOs.PaymentDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentUpdateException;
import dev.ruchir.evolvion_accounts_service.mappers.PaymentMapper;
import dev.ruchir.evolvion_accounts_service.models.Core.Payment;
import dev.ruchir.evolvion_accounts_service.repository.PaymentRepository;
import dev.ruchir.evolvion_accounts_service.service.Interface.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) throws PaymentCreationException {
        logger.info("Creating payment: {}", paymentDTO);
        try {
            Payment payment = paymentMapper.toEntity(paymentDTO);
            Payment savedPayment = paymentRepository.save(payment);
            logger.info("Payment created successfully: {}", savedPayment);
            return paymentMapper.toDTO(savedPayment);
        } catch (Exception e) {
            logger.error("Failed to create payment", e);
            throw new PaymentCreationException("Failed to create payment", e);
        }
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) throws PaymentNotFoundException, PaymentUpdateException {
        logger.info("Updating payment with id: {}", id);
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        try {
            Payment updatedPayment = paymentMapper.toEntity(paymentDTO);
            updatedPayment.setId(id);
            updatedPayment.setCreatedAt(existingPayment.getCreatedAt());
            Payment savedPayment = paymentRepository.save(updatedPayment);
            logger.info("Payment updated successfully: {}", savedPayment);
            return paymentMapper.toDTO(savedPayment);
        } catch (Exception e) {
            logger.error("Failed to update payment", e);
            throw new PaymentUpdateException("Failed to update payment", e);
        }
    }

    @Override
    public PaymentDTO getPaymentById(Long id) throws PaymentNotFoundException {
        logger.info("Fetching payment with id: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        logger.info("Fetched payment: {}", payment);
        return paymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        logger.info("Fetching all payments");
        List<Payment> payments = paymentRepository.findAll();
        logger.info("Fetched {} payments", payments.size());
        return paymentMapper.toDTOList(payments);
    }

    @Override
    public void deletePayment(Long id) throws PaymentNotFoundException {
        logger.info("Deleting payment with id: {}", id);
        if (!paymentRepository.existsById(id)) {
            logger.error("Payment not found with id: {}", id);
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
        logger.info("Payment deleted successfully with id: {}", id);
    }
}
