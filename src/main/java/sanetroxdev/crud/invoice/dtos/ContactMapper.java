package sanetroxdev.crud.invoice.dtos;


import org.mapstruct.Mapper;
import sanetroxdev.crud.invoice.models.ContactEntity;

@Mapper
public interface ContactMapper {

    ContactDto toContactDto(ContactEntity contactEntity);

    ContactEntity toContactEntity(ContactDto contactDto);
}
