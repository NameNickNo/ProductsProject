package com.example.project.detail.service;

import com.example.project.certificate.model.Certificate;
import com.example.project.detail.model.Detail;
import com.example.project.detail.repository.DetailRepository;
import com.example.project.detail.service.analityc.DetailNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DetailServiceV1 implements DetailService {

    private final DetailRepository detailRepository;

    public DetailServiceV1(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public List<Detail> getAll() {
        log.info("DetailServiceV1 getAll()");
        return detailRepository.findAll();
    }

    @Override
    public Detail getByIdElseNull(long id) {
        log.info("DetailServiceV1 getByIdElseNull() with id =  {}", id);
        return detailRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Detail detail) {
        log.info("DetailServiceV1 create(), {}", detail.toString());
        detailRepository.save(detail);
    }

    @Override
    public void delete(Detail detail) {
        log.info("DetailServiceV1 delete(), {}", detail.toString());
        detailRepository.delete(detail);
    }

    @Override
    public List<Detail> getUsedDetails(Detail detail) {

        List<Detail> details = detailRepository.findAll();
        List<Detail> usedDetails = new ArrayList<>();

        for (Detail detailInList : details) {
            if (Objects.equals(detailInList.getParentId(), detail.getId())) {
                usedDetails.add(detailInList);
                log.info("find used detail");
            }
        }
        return usedDetails;
    }

    @Override
    public List<Detail> findDetailsByProductId(Long id) {
        return detailRepository.findDetailsByProductId(id);
    }




    public Map<Detail, Integer> analytics() {
        Map<Detail, Integer> map = new HashMap<>();
        List<Detail> details = detailRepository.findAll();
        List<DetailNode> nodes = new ArrayList<>();

        for (Detail detail : details) {
            nodes.add(new DetailNode(detail.getId(), detail.getParentId()));
        }


        for (DetailNode detailNode : nodes) {

            if (detailNode.getParentId() == null) {

            }
        }


        return map;
    }

}
