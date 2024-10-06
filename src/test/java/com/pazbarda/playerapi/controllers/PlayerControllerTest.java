package com.pazbarda.playerapi.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.HashSet;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllPlayerIDs() throws Exception {
        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"qweqwe\", \"zxczxc\"]"));
    }

    @Test
    public void testGetPlayerByID_sanity() throws Exception {
        mockMvc.perform(get("/api/player/someplayer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPlayerByID_playerID_null() throws Exception {
        mockMvc.perform(get("/api/player/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPlayerByID_playerID_blank() throws Exception {
        mockMvc.perform(get("/api/player/ "))
                .andExpect(status().isBadRequest());
    }
}