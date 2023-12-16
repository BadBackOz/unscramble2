package com.ohz.unscramble.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UnscrambleWordsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenValidRequest_ThenReturnResponse() throws Exception {
        this.mockMvc.perform(get("/getWords/pots")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Stop")));
    }

    @Test
    void givenInvalidCharacter_WhenRequestReceived_ThenReturnBadResponse() throws Exception {
        this.mockMvc.perform(get("/getWords/po5ts")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("{\"message\":\"Invalid Request: Only Alphabetic characters and '*' allowed.\"}")));
    }
}
