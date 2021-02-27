package com.example.project.repository;

import com.example.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "with detail_count_certificates_product as " +
            "(select d.id, count(c.id) as certificate, product_id" +
            " from products" +
            " join details d" +
            " on products.id = d.product_id" +
            " left join certificates c" +
            " on d.id = c.detail_id" +
            " group by d.id,  product_id" +
            ") select count(nullif(certificate, 0)) * 1.0/ count(*)  as procent" +
            " from detail_count_certificates_product" +
            " group by product_id" +
            " order by product_id",
            nativeQuery = true)
    List<Double> percent();
}
