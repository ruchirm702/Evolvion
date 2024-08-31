package dev.ruchir.evolvion_accounts_service.DTOs;

import dev.ruchir.evolvion_accounts_service.models.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private Long id;
    private String description;
    private Double amount;
    private ExpenseCategory category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
