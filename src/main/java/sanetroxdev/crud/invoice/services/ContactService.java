package sanetroxdev.crud.invoice.services;



import org.springframework.stereotype.Service;
import sanetroxdev.crud.invoice.dtos.ContactDto;
import sanetroxdev.crud.invoice.models.ContactEntity;
import sanetroxdev.crud.invoice.repository.ContactRepository;

import java.util.Optional;

@Service
public class ContactService {


    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactEntity updateContact(ContactDto contactDto) {

        Optional<ContactEntity> contactExisting = contactRepository.findByDocumentNumberAndDocumentType(contactDto.getDocumentNumber(), contactDto.getDocumentType());

        ContactEntity contact = contactExisting.orElse(new ContactEntity());
        contact.setName(contactDto.getName());
        contact.setDocumentNumber(contactDto.getDocumentNumber());
        contact.setDocumentType(contactDto.getDocumentType());

        return contactRepository.save(contact);
    }

}
