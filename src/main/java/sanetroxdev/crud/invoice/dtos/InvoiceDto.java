package sanetroxdev.crud.invoice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Setter
@Getter
@Data
public class InvoiceDto {

    private String referenceOne;
    private BigDecimal amount;

    public String getReferenceOne() {
        return referenceOne;
    }

    public void setReferenceOne(String referenceOne) {
        this.referenceOne = referenceOne;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    List<ContactDto> contacts;
}
