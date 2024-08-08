package sanetroxdev.crud.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sanetroxdev.crud.invoice.models.ContactEntity;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {


    @Query("SELECT c FROM ContactEntity c WHERE c.documentNumber = ?1 AND c.documentType = ?2")
    Optional<ContactEntity> findByDocumentNumberAndDocumentType(String documentNumber, String documentType);
}
