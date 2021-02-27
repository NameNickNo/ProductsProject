package com.example.project.service;

import com.example.project.model.Detail;

import java.util.List;

public interface DetailService {

    List<Detail> getAll();

    Detail getByIdElseNull(long id);

    void create(Detail detail);

    void delete(Detail detail);

    List<Detail> getUsedDetails(Detail detail);

    List<Detail> findDetailsByProductId(Long id);
}
