package com.example.project.certificate.service;

import com.example.project.certificate.model.Certificate;
import com.example.project.certificate.repository.CertificateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CertificateServiceV1 implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceV1(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<Certificate> getAll() {
        log.info("CertificateServiceV1 getAll()");
        return certificateRepository.findAll();
    }

    @Override
    public Certificate getByIdElseNull(Long id) {
        log.info("CertificateServiceV1 getByIdElseNull(), {}", id);
        return certificateRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Certificate certificate) {
        log.info("CertificateServiceV1 create(), {}", certificate.toString());
        certificateRepository.save(certificate);
    }


    @Override
    public void delete(Certificate certificate) {
        log.info("CertificateServiceV1 delete(), {}", certificate.toString());
        certificateRepository.delete(certificate);
    }

    @Override
    public List<Certificate> findCertificatesByProductId(Long productId){
        log.info("CertificateServiceV1 findCertificatesByProductId(), id = {}", productId);
        return certificateRepository.findCertificatesByProductId(productId);
    }
}
