package sanetroxdev.crud.invoice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.junit.jupiter.MockitoExtension;
import sanetroxdev.crud.invoice.dtos.ContactDto;
import sanetroxdev.crud.invoice.dtos.InvoiceDto;
import sanetroxdev.crud.invoice.dtos.InvoiceMapper;
import sanetroxdev.crud.invoice.models.ContactEntity;
import sanetroxdev.crud.invoice.models.InvoiceEntity;
import sanetroxdev.crud.invoice.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class InvoiceServicesTests {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private ContactService contactService;


    private final InvoiceMapper mapper = InvoiceMapper.mapper;

    private InvoiceEntity invoice;

    @Mock
    private InvoiceDto invoiceDto;


    @BeforeEach
    void setup(){
        ContactEntity contact = ContactEntity.builder()
                .id(1L)
                .name("John Doe")
                .documentNumber("123456789")
                .documentType("CC")
                .build();
        invoice = InvoiceEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(100))
                .referenceOne("123")
                .date(LocalDate.now())
                .contacts(List.of(contact))
                .build();

        // Configura el comportamiento del repositorio para devolver la entidad existente
        given(invoiceRepository.findByReferenceOne("123")).willReturn(Optional.of(invoice));
        // Configura el comportamiento del repositorio para guardar la entidad
        given(invoiceRepository.save(invoice)).willReturn(invoice);

        // Configura el comportamiento del servicio de contacto
        given(contactService.updateContact(any(ContactDto.class))).willReturn(contact);
    }

    @DisplayName("Test para Listar Invoice")
    @Test
    void testparaListarInvoice(){


        //Given
        InvoiceEntity invoice2 = InvoiceEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(100))
                .referenceOne("ref-2")
                .date(LocalDate.now())
                .build();

        given(invoiceRepository.findAll()).willReturn(List.of(invoice, invoice2));

        //When
        List<InvoiceDto> invoices = invoiceService.getinvoices();

        //Then
        assertThat(invoices).isNotNull().hasSize(2);

    }

    @DisplayName("Test para Actualizar Invoice")
    @Test
    void testparaActualizarInvoice(){

        //Given
        given(invoiceRepository.save(invoice)).willReturn(invoice);
        BigDecimal newAmount = BigDecimal.valueOf(200);
        invoice.setAmount(newAmount);

        InvoiceDto invoiceDtoUpdated = mapper.toInvoiceDto(invoice);
        System.out.println(invoiceDtoUpdated);

        //When
        InvoiceDto invoiceUpdated = invoiceService.updateInvoice(invoiceDtoUpdated);

        //Then
        assertThat(invoiceUpdated.getAmount()).isEqualTo(newAmount);
    }
}
