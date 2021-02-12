package com.example.project.detail.service;

import com.example.project.detail.model.Detail;

import java.util.List;
import java.util.Map;

public interface DetailService {

    List<Detail> getAll();

    Detail getByIdElseNull(long id);

    void create(Detail detail);

    void delete(Detail detail);

    List<Detail> getUsedDetails(Detail detail);

    List<Detail> findDetailsByProductId(Long id);

    Map<Detail, Integer> analytics();
}
