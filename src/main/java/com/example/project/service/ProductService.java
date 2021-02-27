package com.example.project.service;

import com.example.project.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getAll();

    Product getByIdElseNull(long id);

    void create(Product product);

    void delete(Product product);

    Map<Product, Double> percentageOfCertificatesInProducts();
}
