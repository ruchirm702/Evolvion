package dev.ruchir.evolvion_accounts_service.models.Core;

import dev.ruchir.evolvion_accounts_service.models.enums.InvoiceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity {

    private String invoiceNumber;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
}
