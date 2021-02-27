package com.example.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "details")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private Set<Certificate> certificates;

    @Column(name = "details_parent_id")
    private Long parentId;

    @Column(name = "details_count")
    private int detailsCount;

    @Column(name = "product_id")
    private Long productId;

    private transient List<Detail> details;
}
