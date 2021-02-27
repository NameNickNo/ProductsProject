package com.example.project.config;

import com.example.project.repository.CertificateRepository;
import com.example.project.service.CertificateService;
import com.example.project.service.impl.CertificateServiceV1;
import com.example.project.repository.DetailRepository;
import com.example.project.service.DetailService;
import com.example.project.service.impl.DetailServiceV1;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import com.example.project.service.impl.ProductServiceV1;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {

    @Bean
    public CertificateRepository certificateRepository() {
        return mock(CertificateRepository.class);
    }

    @Bean
    public CertificateService certificateService() {
        return new CertificateServiceV1(certificateRepository());
    }

    @Bean
    public DetailRepository detailRepository() {
        return mock(DetailRepository.class);
    }

    @Bean
    public DetailService detailService() {
        return new DetailServiceV1(detailRepository());
    }

    @Bean
    public ProductRepository productRepository() {
        return mock(ProductRepository.class);
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceV1(productRepository());
    }
}
