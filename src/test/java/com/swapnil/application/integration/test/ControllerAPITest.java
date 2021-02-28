package com.swapnil.application.integration.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerAPITest<E> {

    private static final String BASEPATH = "/v1/api/stack";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testStackOperationsAPI() throws Exception {
        // Initially stack is empty with capacity = 1

        //Peek Element -> Returns Forbidden error due to Empty Stack : Stack = []
        this.mockMvc.perform(get(BASEPATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        //Push Element -> Returns Success : Stack = [1]
        this.mockMvc.perform(post(BASEPATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"data\":1}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //Peek Element -> Returns Top Element 1 : Stack = [1]
        this.mockMvc.perform(get(BASEPATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("1"));

        //Push Element -> Returns Forbidden error due to Stack Overflow : Stack = [1]
        this.mockMvc.perform(post(BASEPATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"data\":2}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        //Pop Element -> Returns Success : Stack = []
        this.mockMvc.perform(delete(BASEPATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Pop Element -> Returns Forbidden error due to Empty Stack : Stack = []
        this.mockMvc.perform(delete(BASEPATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
