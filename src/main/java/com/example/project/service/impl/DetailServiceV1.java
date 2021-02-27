package com.example.project.service.impl;

import com.example.project.model.Detail;
import com.example.project.repository.DetailRepository;
import com.example.project.service.DetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DetailServiceV1 implements DetailService {

    private final DetailRepository detailRepository;

    public DetailServiceV1(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Detail> getAll() {
        return detailRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Detail getByIdElseNull(long id) {
        return detailRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Detail detail) {
        detailRepository.save(detail);
    }

    @Override
    public void delete(Detail detail) {
        detailRepository.delete(detail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Detail> getUsedDetails(Detail detail) {

        List<Detail> details = detailRepository.findAll();
        List<Detail> usedDetails = new ArrayList<>();

        for (Detail detailInList : details) {
            if (Objects.equals(detailInList.getParentId(), detail.getId())) {
                usedDetails.add(detailInList);
            }
        }
        return usedDetails;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Detail> findDetailsByProductId(Long id) {
        return detailRepository.findDetailsByProductId(id);
    }

}
