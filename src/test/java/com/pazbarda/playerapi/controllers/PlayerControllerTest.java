package com.pazbarda.playerapi.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.pazbarda.playerapi.model.PlayerDTO;
import com.pazbarda.playerapi.services.PlayerDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

@WebMvcTest(PlayerController.class)
@ActiveProfiles("test")
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerDAO playerDAOMock;


    @Test
    public void testGetAllPlayerIDs() throws Exception {
        when(playerDAOMock.getPlayerIDs()).thenReturn(Set.of("aardsda01x", "aaronha01", "aaronto01"));
        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"aardsda01x\", \"aaronha01\", \"aaronto01\"]"));
    }

    @Test
    public void testGetPlayerByID_sanity() throws Exception {
        when(playerDAOMock.getPlayer("someplayer")).thenReturn(
                new PlayerDTO.Builder("someplayer")
                        .withNameFirst("John")
                        .withNameLast("Dow")
                        .build()
        );
        mockMvc.perform(get("/api/player/someplayer"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"playerID\":\"someplayer\", \"nameFirst\":\"John\", \"nameLast\":\"Dow\"}"));
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