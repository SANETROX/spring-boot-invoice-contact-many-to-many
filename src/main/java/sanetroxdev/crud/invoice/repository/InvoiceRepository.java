package sanetroxdev.crud.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sanetroxdev.crud.invoice.models.InvoiceEntity;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    @Query("SELECT i FROM InvoiceEntity i WHERE i.referenceOne = ?1")
    Optional<InvoiceEntity> findByReferenceOne(String referenceOne);

}
