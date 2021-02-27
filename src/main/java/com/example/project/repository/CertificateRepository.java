package com.example.project.repository;

import com.example.project.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query("select c from Certificate c join Detail de on de.id = c.detailId where de.productId = :id")
    List<Certificate> findCertificatesByProductId(@Param("id") Long productId);
}
