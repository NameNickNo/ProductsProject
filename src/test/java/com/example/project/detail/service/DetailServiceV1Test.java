package com.example.project.detail.service;

import com.example.project.detail.model.Detail;
import com.example.project.detail.repository.DetailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class DetailServiceV1Test {

    @Mock
    private DetailRepository detailRepository;

    @InjectMocks
    private DetailService detailService;

    @Test
    void getUsedDetails() {
        Detail detail = new Detail(101L, "children", "code", new HashSet<>(), 100L, 0, 100L, new ArrayList<>());
        Detail parent = new Detail(100L, "parent", "code", new HashSet<>(), null, 1, 100L, new ArrayList<>());

        List<Detail> detailList = List.of(parent, detail);

        when(detailRepository.findAll()).thenReturn(detailList);

        assertEquals(detail.getParentId(), parent.getId());

        List<Detail> usedDetails = detailService.getUsedDetails(detail);
        System.out.println(usedDetails.toString());
//        Detail test = usedDetails.get(0);
//        assertEquals(test, parent);
    }
}