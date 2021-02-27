package com.example.project.service;

import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import com.example.project.service.impl.ProductServiceV1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceV1Test {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final ProductService productService = new ProductServiceV1(productRepository);

    private final Product testingProduct = mock(Product.class);
    private final List<Product> testingProducts = List.of(testingProduct);
    private final Long id = 100L;

    @Test
    void getByIdElseNullShouldCallFindById() {
        when(productRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(testingProduct));

        Product Product = productService.getByIdElseNull(id);

        assertEquals(testingProduct, Product);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void getAllShouldCallFindAll() {

        when(productRepository.findAll()).thenReturn(testingProducts);

        List<Product> all = productService.getAll();

        assertEquals(all, testingProducts);
        verify(productRepository).findAll();
    }

    @Test
    void createShouldToCallSave() {
        when(productRepository.save(testingProduct)).thenReturn(testingProduct);
        productService.create(testingProduct);

        verify(productRepository, times(1)).save(testingProduct);
    }

    @Test
    void deleteShouldToWork() {
        doNothing().when(productRepository).delete(testingProduct);

        productService.delete(testingProduct);
        verify(productRepository, times(1)).delete(testingProduct);
    }

    @Test
    void percentageOfCertificatesInProductsShouldToCallPercent() {
        Double percent = 1.0;
        List<Double> doubleList = List.of(percent);
        Map<Product, Double> testMap = new HashMap<>(Map.of(testingProduct, percent));

        when(productRepository.percent()).thenReturn(doubleList);
        when(productRepository.findAll()).thenReturn(testingProducts);

        Map<Product, Double> percentageOfCertificatesInProducts = productService.percentageOfCertificatesInProducts();
        testMap.put(testingProduct, percent * 100.0);

        verify(productRepository).findAll();
        verify(productRepository, times(1)).percent();

        assertEquals(testMap, percentageOfCertificatesInProducts);

    }
}