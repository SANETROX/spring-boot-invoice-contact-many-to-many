package sanetroxdev.crud.invoice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanetroxdev.crud.invoice.dtos.InvoiceDto;
import sanetroxdev.crud.invoice.dtos.InvoiceMapper;
import sanetroxdev.crud.invoice.dtos.InvoiceMapperImpl;
import sanetroxdev.crud.invoice.models.InvoiceEntity;
import sanetroxdev.crud.invoice.repository.InvoiceRepository;
import sanetroxdev.crud.invoice.services.InvoiceService;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @PutMapping("/update")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoice) {
        InvoiceDto invoiceUpdated = invoiceService.updateInvoice(invoice);
        return ResponseEntity.ok(invoiceUpdated);

    }

}
