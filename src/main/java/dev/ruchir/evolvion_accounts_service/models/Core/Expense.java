package dev.ruchir.evolvion_accounts_service.models.Core;

import dev.ruchir.evolvion_accounts_service.models.enums.ExpenseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends BaseEntity {

    private String description;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;
}
