package com.example.project.detail.controller.container;

import com.example.project.detail.model.Detail;
import lombok.Data;

import java.util.List;

@Data
public class DetailsAndDetailsCountContainer {
    private List<Detail> details;
    private int countDetails;
}
