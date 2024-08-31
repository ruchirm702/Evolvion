package dev.ruchir.evolvion_accounts_service.service.Implementation;

import dev.ruchir.evolvion_accounts_service.DTOs.PaymentDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentUpdateException;
import dev.ruchir.evolvion_accounts_service.mappers.PaymentMapper;
import dev.ruchir.evolvion_accounts_service.models.Core.Payment;
import dev.ruchir.evolvion_accounts_service.repository.PaymentRepository;
import dev.ruchir.evolvion_accounts_service.service.Interface.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) throws PaymentCreationException {
        try {
            Payment payment = paymentMapper.toEntity(paymentDTO);
            Payment savedPayment = paymentRepository.save(payment);
            return paymentMapper.toDTO(savedPayment);
        } catch (Exception e) {
            throw new PaymentCreationException("Failed to create payment", e);
        }
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) throws PaymentNotFoundException, PaymentUpdateException {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        try {
            Payment updatedPayment = paymentMapper.toEntity(paymentDTO);
            updatedPayment.setId(id);
            updatedPayment.setCreatedAt(existingPayment.getCreatedAt());
            Payment savedPayment = paymentRepository.save(updatedPayment);
            return paymentMapper.toDTO(savedPayment);
        } catch (Exception e) {
            throw new PaymentUpdateException("Failed to update payment", e);
        }
    }

    @Override
    public PaymentDTO getPaymentById(Long id) throws PaymentNotFoundException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.toDTOList(payments);
    }

    @Override
    public void deletePayment(Long id) throws PaymentNotFoundException {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
