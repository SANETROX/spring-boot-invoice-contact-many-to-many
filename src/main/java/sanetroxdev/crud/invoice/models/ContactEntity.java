package sanetroxdev.crud.invoice.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@Entity
@Table(name = "contact")
public class ContactEntity {

    public ContactEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ContactEntity(Long id, String name, String documentType, String documentNumber) {
        this.id = id;
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String documentNumber;


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public List<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }

    @Column(nullable = false)
    private String documentType;

    @ManyToMany(mappedBy = "contacts")
    private List<InvoiceEntity> invoices;

    public static class Builder {
        private Long id;
        private String name;
        private String documentNumber;
        private String documentType;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder documentType(String documentType) {
            this.documentType = documentType;
            return this;
        }



        public ContactEntity build() {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setId(this.id);
            contactEntity.setName(this.name);
            contactEntity.setDocumentNumber(this.documentNumber);
            contactEntity.setDocumentType(this.documentType);
            return contactEntity;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
