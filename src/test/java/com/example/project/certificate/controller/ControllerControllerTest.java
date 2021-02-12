package com.example.project.certificate.controller;

import com.example.project.certificate.service.CertificateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
public class ControllerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Mock
    private CertificateService certificateService;

    @BeforeEach
    void setMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void getAllShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/certificates").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void getByIdShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/certificates/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void getCertificatesByProductShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/certificates/byproduct/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }
}
