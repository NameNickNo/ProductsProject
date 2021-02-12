package com.example.project.detail.controller;

import com.example.project.detail.controller.container.DetailsAndDetailsCountContainer;
import com.example.project.detail.model.Detail;
import com.example.project.detail.service.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/details")
public class DetailRestControllerV1 {

    private final DetailService detailService;

    public DetailRestControllerV1(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping
    public ResponseEntity<List<Detail>> getAllDetails() {

        List<Detail> details = detailService.getAll();
        if (details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        details.forEach(detail -> detail.setDetails(detailService.getUsedDetails(detail)));

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail> getById(@PathVariable("id") Long detailId) {
        if (detailId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Detail detail = detailService.getByIdElseNull(detailId);
        if (detail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        detail.setDetails(detailService.getUsedDetails(detail));


        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Detail> createDetail(@RequestBody Detail detail) {

        if (detail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        detailService.create(detail);
        
        return new ResponseEntity<>(detail, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Detail> updateDetail(@RequestBody Detail detail, @PathVariable("id") Long id) {

        if (detail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        detail.setId(id);
        detailService.create(detail);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Detail> deleteDetail(@PathVariable("id") Long id) {
        Detail detail = detailService.getByIdElseNull(id);

        if (detail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        detailService.delete(detail);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // получение списка атомарных деталей по изделию (детали которые использовались
    // при изготовлении изделия или детали и при этом не имеют вложенных деталей, на пример шуруп, болт итд)
    // вместе с их количеством
    @GetMapping("/byproduct/{id}")
    public ResponseEntity<DetailsAndDetailsCountContainer> getDetailsByProduct(@PathVariable("id") Long productId) {
        if (productId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Detail> details = detailService.findDetailsByProductId(productId);

        if (details.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int countDetails = details.size();

        DetailsAndDetailsCountContainer container = new DetailsAndDetailsCountContainer();
        container.setDetails(details);
        container.setCountDetails(countDetails);

        return new ResponseEntity<>(container, HttpStatus.OK);
    }
}
