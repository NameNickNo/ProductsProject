package com.example.project.service;

import com.example.project.model.Detail;
import com.example.project.repository.DetailRepository;
import com.example.project.service.DetailService;
import com.example.project.service.impl.DetailServiceV1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class DetailServiceV1Test {

    private final DetailRepository detailRepository = mock(DetailRepository.class);

    private final DetailService detailService = new DetailServiceV1(detailRepository);

    private final Detail testingDetail = mock(Detail.class);
    private final List<Detail> testingDetails = List.of(testingDetail);
    private final Long id = 100L;

    @Test
    void getByIdElseNullShouldCallFindById() {
        when(detailRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(testingDetail));

        Detail Detail = detailService.getByIdElseNull(id);

        assertEquals(testingDetail, Detail);
        verify(detailRepository, times(1)).findById(id);
    }

    @Test
    void getAllShouldCallFindAll() {

        when(detailRepository.findAll()).thenReturn(testingDetails);

        List<Detail> all = detailService.getAll();

        assertEquals(all, testingDetails);
        verify(detailRepository,times(1)).findAll();
    }

    @Test
    void createShouldToCallSave() {
        when(detailRepository.save(testingDetail)).thenReturn(testingDetail);
        detailService.create(testingDetail);

        verify(detailRepository, times(1)).save(testingDetail);
    }

    @Test
    void deleteShouldToWork() {
        doNothing().when(detailRepository).delete(testingDetail);

        detailService.delete(testingDetail);
        verify(detailRepository, times(1)).delete(testingDetail);
    }

    @Test
    void getUsedDetailsShouldToReturnTrueChildren() {
        Detail children = new Detail(1L, "name", "code", new HashSet<>(),
                2L, 0, 1L, new ArrayList<>());
        Detail parent = new Detail(2L, "name", "code", new HashSet<>(),
                null, 1, 2L, new ArrayList<>());

        List<Detail> details = List.of(children, parent);

        when(detailRepository.findAll()).thenReturn(details);

        List<Detail> childrenDetails = detailService.getUsedDetails(parent);
        Detail test = childrenDetails.get(0);

        verify(detailRepository).findAll();
        assertEquals(children, test);
    }

    @Test
    void getUsedDetailsShouldToReturnEmptyList() {
        Detail children = new Detail(1L, "name", "code", new HashSet<>(),
                2L, 0, 1L, new ArrayList<>());
        Detail notParent = new Detail(3L, "name", "code", new HashSet<>(),
                null, 1, 2L, new ArrayList<>());

        List<Detail> details = List.of(children, notParent);

        when(detailRepository.findAll()).thenReturn(details);

        List<Detail> childrenDetails = detailService.getUsedDetails(notParent);

        verify(detailRepository).findAll();
        assertTrue(childrenDetails.isEmpty());
    }

    @Test
    void findDetailsByProductIdShouldToCallThisRepositoryMethod() {
        when(detailRepository.findDetailsByProductId(id)).thenReturn(testingDetails);

        List<Detail> detailsByProductId = detailService.findDetailsByProductId(id);

        assertEquals(testingDetails, detailsByProductId);
        verify(detailRepository, times(1)).findDetailsByProductId(id);
    }
}