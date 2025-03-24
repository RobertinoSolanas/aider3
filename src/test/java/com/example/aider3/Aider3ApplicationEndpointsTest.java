package com.example.aider3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Aider3ApplicationEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/hello2"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello from Aider3!"));
    }

    @Test
    public void testEchoEndpoint() throws Exception {
        String testValue = "test123";
        mockMvc.perform(get("/echo").param("value", testValue))
               .andExpect(status().isOk())
               .andExpect(content().string(testValue));
    }
}
