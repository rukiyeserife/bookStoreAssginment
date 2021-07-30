package com.readingisgood;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IntegrationTests extends ReadingIsGoodTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCustomer() throws Exception {
        mockMvc.perform(get("/customers/1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.firstName").value("Lokesh"))
                .andExpect(jsonPath("$.lastName").value("Gupta"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.adress").value("address1")).
                andExpect(jsonPath("$.phone").value("05550253636"));

    }

    @Test
    public void createCustomer() throws Exception {

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\": \"Rukiye\",\n" +
                        "    \"lastName\": \"Bastug\",\n" +
                        "    \"adress\": \"xxx\",\n" +
                        "    \"phone\": \"5xxxxxxx\",\n" +
                        "    \"email\": \"xyz@email.com\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstName").value("Rukiye"))
                .andExpect(jsonPath("$.lastName").value("Bastug"));

    }


    @Test
    public void createBook() throws Exception {

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"bookName\": \"Microservice\",\n" +
                        "    \"bookVendor\": \"BastDengeug\",\n" +
                        "    \"bookDescription\": \"Technical\",\n" +
                        "    \"quantityInStock\": 50,\n" +
                        "    \"buyPrice\": 200\n" +
                        "}\n")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.bookName").value("Microservice"))
                .andExpect(jsonPath("$.bookVendor").value("BastDengeug"));

    }



}
