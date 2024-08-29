package dev.ruchir.evolvion_accounts_service.models.Core;

import dev.ruchir.evolvion_accounts_service.models.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    private String paymentMethod;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
