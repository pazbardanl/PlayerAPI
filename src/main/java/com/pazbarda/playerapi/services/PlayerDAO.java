package com.pazbarda.playerapi.services;

import com.pazbarda.playerapi.model.PlayerDTO;
import com.pazbarda.playerapi.model.PlayerRawData;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class PlayerDAO {

    @Value("${players.csv.path}")
    private String PLAYERS_CSV_PATH;

    @Autowired
    private PlayerCsvDecoder playerCsvDecoder;

    private Map<String, PlayerDTO> playersCache;

    @PostConstruct
    public void init() throws IOException {
        this.playersCache = getPlayersCache(PLAYERS_CSV_PATH);

    }

    public PlayerDTO getPlayer(String playerID) throws NoSuchElementException {
        return Optional.ofNullable(playersCache.get(playerID))
                // TODO PB -- logging
                .orElseThrow(() -> new NoSuchElementException("player ID " + playerID + " not found"));
    }

    public Set<String> getPlayerIDs() {
        return playersCache.keySet();
    }

    // TODO PB -- clean code
    // TODO PB -- logging
    private Map<String, PlayerDTO> getPlayersCache(String csvPath) throws IOException {
        Map<String, PlayerDTO> cache = new HashMap<>();
        System.out.println("> csvPath = " + csvPath);
        Path path = Path.of(csvPath);
        if (!Files.exists(path)) {
            throw new RuntimeException("CSV file not found: " + csvPath);
        }
        int lineCounter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            String headerLine = reader.readLine();
            System.out.println("Skipped header line: " + headerLine);
            String line;
            while ((line = reader.readLine()) != null) {
                PlayerDTO playerDTO = buildPlayerDTO(line);
                cache.put(playerDTO.getPlayerID(), playerDTO);
                lineCounter++;
            }
        } catch (IOException e) {
            // TODO PB -- Handle the exception appropriately, with logging
            throw new IOException("error reading players CSV file at line " + lineCounter);
        }
        return cache;
    }

    // TODO PB -- extract this to a package helper class
    private PlayerDTO buildPlayerDTO(String csvLine) {
        PlayerRawData playerRawData = playerCsvDecoder.decode(csvLine);

        if (null == playerRawData.playerID() || playerRawData.playerID().isEmpty() || playerRawData.playerID().isBlank()) {
            throw new IllegalArgumentException("invalid playerID");
        }

        PlayerDTO.Builder playerDtoBuilder = new PlayerDTO.Builder(playerRawData.playerID());

        if (playerRawData.birthYear() != null && playerRawData.birthMonth() != null && playerRawData.birthDay() != null) {
            playerDtoBuilder.withDateOfBirth(playerRawData.birthYear(), playerRawData.birthMonth(), playerRawData.birthDay());
        }
        if (playerRawData.deathYear() != null && playerRawData.deathMonth() != null && playerRawData.deathDay() != null) {
            playerDtoBuilder.withDateOfBirth(playerRawData.deathYear(), playerRawData.deathMonth(), playerRawData.deathDay());
        }

        return playerDtoBuilder
                .withBirthCountry(playerRawData.birthCountry())
                .withBirthState(playerRawData.birthState())
                .withBirthCity(playerRawData.birthCity())
                .withDeathCountry(playerRawData.deathCountry())
                .withDeathState(playerRawData.deathState())
                .withDeathCity(playerRawData.deathCity())
                .withNameFirst(playerRawData.nameFirst())
                .withNameLast(playerRawData.nameLast())
                .withNameGiven(playerRawData.nameGiven())
                .withBats(playerRawData.bats())
                .withShoots(playerRawData.shoots())
                .withDateOfDebut(playerRawData.dateOfDebutString())
                .withDateOfFinalGame(playerRawData.dateOfFinalGameString())
                .withRetroID(playerRawData.retroID())
                .withBbrefID(playerRawData.bbrefID())
                .build();
    }


}
