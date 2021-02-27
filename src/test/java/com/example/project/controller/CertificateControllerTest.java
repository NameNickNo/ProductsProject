package com.example.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
public class CertificateControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;


    @BeforeEach
    void setMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void getAllShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/certificates")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void getByIdShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/certificates/{id}", 6)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void getCertificatesByProductShouldReturnBodyAndStatus() throws Exception {
        mvc.perform(get("/api/v1/certificates/byproduct/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

//    @Test
//    void createCertificateShouldToSendBody() throws Exception {
//        mvc.perform(post("/api/v1/certificates/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \" some name\", \"code\": \"some code\", \"detail_id\": \"some detailId\"}"))
//                .andDo(print())
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void updateCertificateShouldToSendBody() throws Exception {
//        mvc.perform(put("/api/v1/certificates/update/{id}", 1000)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{ \"id\": 1000, \"name\": \" some name\", \"code\": \"some code\", \"detail_id\": \"some detailId\"}"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void deleteCertificateShould() throws Exception {
//        mvc.perform(delete("/api/v1/certificates/delete/{id}")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent())
//                .andReturn();
//    }
}
