package com.example.project.detail.repository;

import com.example.project.detail.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {

    @Query("select d from Detail d where d.productId = :id and d.detailsCount = 0")
    List<Detail> findDetailsByProductId(@Param("id")Long id);
}
