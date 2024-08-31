package dev.ruchir.evolvion_accounts_service.repository;

import dev.ruchir.evolvion_accounts_service.models.Core.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentMethod(String paymentMethod);
    boolean existsByPaymentMethod(String paymentMethod);
}