package sanetroxdev.crud.invoice.services;


import org.springframework.stereotype.Service;
import sanetroxdev.crud.invoice.dtos.ContactDto;
import sanetroxdev.crud.invoice.dtos.InvoiceDto;
import sanetroxdev.crud.invoice.dtos.InvoiceMapper;
import sanetroxdev.crud.invoice.models.ContactEntity;
import sanetroxdev.crud.invoice.models.InvoiceEntity;
import sanetroxdev.crud.invoice.repository.InvoiceRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceService {


    private InvoiceRepository invoiceRepository;


    private ContactService contactService;

    private static final int MAX_CONTACTS = 5;

    public InvoiceService(InvoiceRepository invoiceRepository, ContactService contactService) {
        this.invoiceRepository = invoiceRepository;
        this.contactService = contactService;
    }

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto) {
        Optional<InvoiceEntity> invoiceExisting = invoiceRepository.findByReferenceOne(invoiceDto.getReferenceOne());

        InvoiceEntity invoice = invoiceExisting.orElse(new InvoiceEntity());
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setDate(LocalDate.now());

        //Obtener los contatos existentes de la factura
        List<ContactEntity> existingContacts = invoice.getContacts();

        // Crear un mapa de contactos existentes por documentNumber y documentType para fácil comparación
        Map<String, ContactEntity> existingContactsMap = existingContacts.stream()
                .collect(Collectors.toMap(c -> c.getDocumentNumber() + "-" + c.getDocumentType(), c -> c));



        // Crear una lista para los contactos actualizados o nuevos
        List<ContactEntity> updatedContacts = new ArrayList<>();

        // Procesar contactos del request
        for (ContactDto contactDto : invoiceDto.getContacts()) {
            String key = contactDto.getDocumentNumber() + "-" + contactDto.getDocumentType();
            existingContactsMap.remove(key);
            ContactEntity contactEntity = contactService.updateContact(contactDto);
            updatedContacts.add(contactEntity);
        }

        // Los contactos restantes en existingContactsMap son los que no están en el request
        // Agregar los contactos restantes a la lista de contactos actualizados
        updatedContacts.addAll(existingContactsMap.values());


        // Limitar el número de contactos a MAX_CONTACTS (5)
        if (updatedContacts.size() > MAX_CONTACTS) {
            updatedContacts.sort(Comparator.comparingLong(ContactEntity::getId).reversed());
            updatedContacts = updatedContacts.subList(0, MAX_CONTACTS);
        }

        // Asignar la lista de contactos actualizados a la factura
        invoice.setContacts(updatedContacts);
        InvoiceEntity invoiceUpdated = invoiceRepository.save(invoice);

        return InvoiceMapper.mapper.toInvoiceDto(invoiceUpdated);

    }

    public List<InvoiceDto> getinvoices() {

        List<InvoiceEntity> invoices = invoiceRepository.findAll();
        return InvoiceMapper.mapper.toInvoiceDtoList(invoices);
    }
}
