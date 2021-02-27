package com.example.project.service.impl;

import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceV1 implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceV1(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByIdElseNull(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Product, Double> percentageOfCertificatesInProducts() {
        List<Double> valueCertificatesByProductId = productRepository.percent();

        List<Double> percentCertificates = valueCertificatesByProductId.stream().map(value -> value * 100).collect(Collectors.toList());

        List<Product> allProducts = getAll();

        Map<Product, Double> productsAndPercentages = new HashMap<>();

        for (int i = 0; i < percentCertificates.size(); i++) {
            productsAndPercentages.put(allProducts.get(i), percentCertificates.get(i));
        }
        return productsAndPercentages;
    }
    
}
