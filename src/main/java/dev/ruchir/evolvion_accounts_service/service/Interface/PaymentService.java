package dev.ruchir.evolvion_accounts_service.service.Interface;

import dev.ruchir.evolvion_accounts_service.DTOs.PaymentDTO;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentCreationException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentNotFoundException;
import dev.ruchir.evolvion_accounts_service.controller_advise.Payment_Exceptions.PaymentUpdateException;

import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO) throws PaymentCreationException;
    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) throws PaymentNotFoundException, PaymentUpdateException;
    PaymentDTO getPaymentById(Long id) throws PaymentNotFoundException;
    List<PaymentDTO> getAllPayments();
    void deletePayment(Long id) throws PaymentNotFoundException;
}

