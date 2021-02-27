package com.example.project.controller;

import com.example.project.model.Product;
import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestControllerV1 {

    private final ProductService productService;

    public ProductRestControllerV1(@Qualifier("productServiceV1") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        List<Product> products = productService.getAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Long productId) {
        if (productId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productService.getByIdElseNull(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.create(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        product.setId(id);
        productService.create(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.getByIdElseNull(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productService.delete(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
//    - аналитика по покрытию изделия сертификатами. Выводить списком, изделие – процент
    @GetMapping("/percentageof/certificates")
    public ResponseEntity<Map<Product, Double>> percentageOfCertificatesInProducts() {
        Map<Product, Double> productsAndPercent = productService.percentageOfCertificatesInProducts();

        if (productsAndPercent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productsAndPercent, HttpStatus.OK);
    }
}
