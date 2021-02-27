package com.example.project.service;

import com.example.project.model.Certificate;

import java.util.List;

public interface CertificateService {

    List<Certificate> getAll();

    Certificate getByIdElseNull(Long id);

    void create(Certificate certificate);

    void delete(Certificate certificate);

    List<Certificate> findCertificatesByProductId(Long productId);
}
