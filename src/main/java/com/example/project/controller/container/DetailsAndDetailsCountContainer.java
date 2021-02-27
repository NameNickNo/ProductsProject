package com.example.project.controller.container;

import com.example.project.model.Detail;
import lombok.Data;

import java.util.List;

@Data
public class DetailsAndDetailsCountContainer {
    private List<Detail> details;
    private int countDetails;
}
