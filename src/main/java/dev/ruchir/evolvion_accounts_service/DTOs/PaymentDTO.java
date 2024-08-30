package dev.ruchir.evolvion_accounts_service.DTOs;

import dev.ruchir.evolvion_accounts_service.models.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private String paymentMethod;
    private Double amount;
    private PaymentStatus status;
    private String createdAt;  // Change to LocalDateTime for better date handling
    private String updatedAt;
}
