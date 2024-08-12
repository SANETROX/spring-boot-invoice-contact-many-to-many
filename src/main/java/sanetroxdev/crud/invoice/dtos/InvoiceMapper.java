package sanetroxdev.crud.invoice.dtos;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sanetroxdev.crud.invoice.models.InvoiceEntity;

import java.util.List;

@Mapper
public interface InvoiceMapper {

    InvoiceMapper mapper = Mappers.getMapper(InvoiceMapper.class);

    InvoiceDto toInvoiceDto(InvoiceEntity invoiceEntity);

    InvoiceEntity toInvoiceEntity(InvoiceDto invoiceDto);

    List<InvoiceDto> toInvoiceDtoList(List<InvoiceEntity> invoiceEntityList);
}
