package dev.ruchir.evolvion_accounts_service.repository;

import dev.ruchir.evolvion_accounts_service.models.Core.Expense;
import dev.ruchir.evolvion_accounts_service.models.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByDescription(String description);
    boolean existsByDescription(String description);
    List<Expense> findByCategory(ExpenseCategory category);
}