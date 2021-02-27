package com.example.project.service.impl;

import com.example.project.model.Certificate;
import com.example.project.repository.CertificateRepository;
import com.example.project.service.CertificateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificateServiceV1 implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceV1(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Certificate getByIdElseNull(Long id) {
        return certificateRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Certificate certificate) {
        certificateRepository.save(certificate);
    }


    @Override
    public void delete(Certificate certificate) {
        certificateRepository.delete(certificate);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Certificate> findCertificatesByProductId(Long productId){
        return certificateRepository.findCertificatesByProductId(productId);
    }
}
