package sanetroxdev.crud.invoice.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanetroxdev.crud.invoice.dtos.InvoiceDto;

import sanetroxdev.crud.invoice.services.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {


    @Autowired
    private InvoiceService invoiceService;


    @GetMapping("/listar/invoices")
    public ResponseEntity<List<InvoiceDto>> getInvoices(){
        List<InvoiceDto> invoices = invoiceService.getinvoices();
        return ResponseEntity.ok(invoices);
    }

    @PutMapping("/update")
    public ResponseEntity<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoice) {
        InvoiceDto invoiceUpdated = invoiceService.updateInvoice(invoice);
        return ResponseEntity.ok(invoiceUpdated);

    }

}
