package com.example.project.service;

import com.example.project.model.Certificate;
import com.example.project.repository.CertificateRepository;
import com.example.project.service.CertificateService;
import com.example.project.service.impl.CertificateServiceV1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CertificateServiceV1Test {

    private final CertificateRepository certificateRepository = mock(CertificateRepository.class);

    private final CertificateService certificateService = new CertificateServiceV1(certificateRepository);


    private final Certificate testingCertificate = mock(Certificate.class);
    private final List<Certificate> testingCertificates = List.of(testingCertificate);
    private final Long id = 100L;

    @Test
    void getByIdElseNullShouldCallFindById() {
        when(certificateRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(testingCertificate));

        Certificate certificate = certificateService.getByIdElseNull(id);

        assertEquals(testingCertificate, certificate);
        verify(certificateRepository, times(1)).findById(id);
    }

    @Test
    void getAllShouldCallFindAll() {

        when(certificateRepository.findAll()).thenReturn(testingCertificates);

        List<Certificate> all = certificateService.getAll();

        assertEquals(all, testingCertificates);
        verify(certificateRepository,times(1)).findAll();
    }

    @Test
    void createShouldToCallSave() {
        when(certificateRepository.save(testingCertificate)).thenReturn(testingCertificate);
        certificateService.create(testingCertificate);

        verify(certificateRepository, times(1)).save(testingCertificate);
    }

    @Test
    void deleteShouldToWork() {
        doNothing().when(certificateRepository).delete(testingCertificate);

        certificateService.delete(testingCertificate);
        verify(certificateRepository, times(1)).delete(testingCertificate);
    }

    @Test
    void findCertificatesByProductIdShouldToCallRepositoryMethod() {
        when(certificateRepository.findCertificatesByProductId(id)).thenReturn(testingCertificates);

        List<Certificate> certificates = certificateService.findCertificatesByProductId(id);

        assertEquals(testingCertificates, certificates);
        verify(certificateRepository, times(1)).findCertificatesByProductId(id);
    }
}