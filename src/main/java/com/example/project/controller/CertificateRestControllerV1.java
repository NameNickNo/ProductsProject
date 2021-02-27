package com.example.project.controller;

import com.example.project.model.Certificate;
import com.example.project.service.CertificateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/certificates")
public class CertificateRestControllerV1 {

    private final CertificateService certificateService;

    public CertificateRestControllerV1(@Qualifier("certificateServiceV1") CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAll() {

        List<Certificate> certificates = certificateService.getAll();
        if (certificates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getById(@PathVariable("id") Long certificateId) {
        if (certificateId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Certificate certificate = certificateService.getByIdElseNull(certificateId);
        if (certificate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate) {

        if (certificate == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        certificateService.create(certificate);
        return new ResponseEntity<>(certificate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Certificate> updateCertificate(@RequestBody Certificate certificate,@PathVariable("id") Long id) {

        if (certificate == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        certificate.setId(id);
        certificateService.create(certificate);

        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Certificate> deleteCertificate(@PathVariable("id") Long id) {
        Certificate certificate = certificateService.getByIdElseNull(id);

        if (certificate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        certificateService.delete(certificate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //получение всех уникальных сертификатов по изделию (с учетом вложенности деталей)
    @GetMapping("/byproduct/{id}")
    public ResponseEntity<List<Certificate>> getCertificatesByProduct(@PathVariable("id") Long productId) {

        if (productId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Certificate> certificates = certificateService.findCertificatesByProductId(productId);

        if (certificates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }
}
