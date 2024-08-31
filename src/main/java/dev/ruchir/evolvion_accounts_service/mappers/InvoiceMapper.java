package dev.ruchir.evolvion_accounts_service.mappers;

import dev.ruchir.evolvion_accounts_service.DTOs.InvoiceDTO;
import dev.ruchir.evolvion_accounts_service.models.Core.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    InvoiceDTO toDTO(Invoice invoice);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Invoice toEntity(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> toDTOList(List<Invoice> invoices);

    List<Invoice> toEntityList(List<InvoiceDTO> invoiceDTOs);
}
