package dev.ruchir.evolvion_accounts_service.DTOs;

import dev.ruchir.evolvion_accounts_service.models.enums.InvoiceStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private Double amount;
    private InvoiceStatus status;
    private String createdAt;  // Change to LocalDateTime for better date handling
    private String updatedAt;
}
