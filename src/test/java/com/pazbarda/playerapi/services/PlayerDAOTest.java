package com.pazbarda.playerapi.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class PlayerDAOTest {

    @MockBean
    private PlayerCsvDecoder playerCsvDecoderMock;

    @Test
    public void testGetPlayerIDs_sanity() {

    }

    @Test
    public void testGetPlayerIDs_noPlayers() {

    }

    @Test
    public void testGetPlayer_sanity() {

    }

    @Test
    public void testGetPlayer_nonExistingPlayer() {

    }

    @Test
    public void testGetPlayer_noPlayers() {

    }
}
