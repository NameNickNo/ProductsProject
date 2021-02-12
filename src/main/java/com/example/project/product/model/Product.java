package com.example.project.product.model;

import com.example.project.detail.model.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Table(name = "products")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Detail> details;

    @Column(name = "details_count")
    private int detailsCount;

}
