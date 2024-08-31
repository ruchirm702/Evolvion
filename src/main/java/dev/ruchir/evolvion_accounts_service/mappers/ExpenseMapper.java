package dev.ruchir.evolvion_accounts_service.mappers;

import dev.ruchir.evolvion_accounts_service.DTOs.ExpenseDTO;
import dev.ruchir.evolvion_accounts_service.models.Core.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ExpenseDTO toDTO(Expense expense);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Expense toEntity(ExpenseDTO expenseDTO);
    List<ExpenseDTO> toDTOList(List<Expense> expenses);
    List<Expense> toEntityList(List<ExpenseDTO> expenseDTOs);
}
