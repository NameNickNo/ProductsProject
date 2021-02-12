package com.example.project.product.service;

import com.example.project.product.model.Product;
import com.example.project.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceV1 implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceV1(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        log.info("ProductServiceV1 getAll()");
        return productRepository.findAll();
    }

    @Override
    public Product getByIdElseNull(long id) {
        log.info("ProductServiceV1 getByIdElseNull(), id = {}", id);
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Product product) {
        log.info("ProductServiceV1 create(), {}", product.toString());
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        log.info("ProductServiceV1 delete(), {}", product.toString());
        productRepository.delete(product);
    }

    @Override
    public Map<Product, Double> percentageOfCertificatesInProducts() {
        List<Double> valueCertificatesByProductId = productRepository.percent();
        log.info("ProductServiceV1 percentageOfCertificates(), {}", valueCertificatesByProductId.toString());

        List<Double> percentCertificates = valueCertificatesByProductId.stream().map(value -> value * 100).collect(Collectors.toList());

        List<Product> allProducts = getAll();

        Map<Product, Double> productsAndPercentages = new HashMap<>();

        for (int i = 0; i < percentCertificates.size(); i++) {
            productsAndPercentages.put(allProducts.get(i), percentCertificates.get(i));
        }

        log.info("ProductServiceV1 percentageOfCertificates(), {}",productsAndPercentages.toString());

        return productsAndPercentages;
    }
    
}
