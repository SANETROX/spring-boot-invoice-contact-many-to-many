package sanetroxdev.crud.invoice.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@Builder
@Data
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = true)
    private String referenceOne;

    public InvoiceEntity(Long id, BigDecimal amount, String referenceOne, LocalDate date, List<ContactEntity> contacts) {
        this.id = id;
        this.amount = amount;
        this.referenceOne = referenceOne;
        this.date = date;
        this.contacts = contacts;
    }

    public InvoiceEntity() {
    }

    @Column(nullable = false)
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "invoice_contact",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private List<ContactEntity> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReferenceOne() {
        return referenceOne;
    }

    public void setReferenceOne(String referenceOne) {
        this.referenceOne = referenceOne;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }
    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder estática
    public static class Builder {
        private Long id;
        private BigDecimal amount;
        private String referenceOne;
        private LocalDate date;
        private List<ContactEntity> contacts;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder referenceOne(String referenceOne) {
            this.referenceOne = referenceOne;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder contacts(List<ContactEntity> contacts) {
            this.contacts = contacts;
            return this;
        }

        public InvoiceEntity build() {
            return new InvoiceEntity(id, amount, referenceOne, date, contacts);
        }
    }

}
