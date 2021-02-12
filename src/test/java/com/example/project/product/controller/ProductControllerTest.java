package com.example.project.product.controller;

import com.example.project.product.service.ProductService;
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
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void getAllShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getByIdShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/products/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void percentageOfCertificatesInProductsShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/products/percentageof/certificates", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
