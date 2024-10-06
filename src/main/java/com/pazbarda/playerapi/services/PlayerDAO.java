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

/*
 * Data Access Object for player data. Provides abstraction over the data source (CSV file)
 * Yes, there must be a better name than DAO out there, I just couldn't find it at the time of implementing this class4
 *
 * This class initiates a cache at startup (as opposed to on-the-go), due to the nature of CSV format (no effective way of reading a specific line or querying it).
 * */
@Service
public class PlayerDAO {

    /*
     * path to CSV file, taken from .properties file
     * */
    @Value("${players.csv.path}")
    private String PLAYERS_CSV_PATH;

    /*
     * player CSV decoder, used for getting player raw data from a CSV line
     * */
    @Autowired
    private PlayerCsvDecoder playerCsvDecoder;

    private Map<String, PlayerDTO> playersCache;

    @PostConstruct
    public void init() throws IOException {
        this.playersCache = getPlayersCache(PLAYERS_CSV_PATH);

    }

    /*
     * Get a PlayerDTO based on the player's ID.
     * Throws NoSuchElementException if no player with this ID
     * */
    public PlayerDTO getPlayer(String playerID) throws NoSuchElementException {
        return Optional.ofNullable(playersCache.get(playerID))
                // TODO PB -- logging
                .orElseThrow(() -> new NoSuchElementException("player ID " + playerID + " not found"));
    }

    /*
     * Get all existing player IDs
     * */
    public Set<String> getPlayerIDs() {
        return playersCache.keySet();
    }

    // TODO PB -- logging
    private Map<String, PlayerDTO> getPlayersCache(String csvPath) throws IOException {
        validateCsvPath(csvPath);
        return getPlayerDTOsFromCsv(csvPath);
    }

    private void validateCsvPath(String csvPath) {
        Path path = Path.of(csvPath);
        if (!Files.exists(path)) {
            throw new RuntimeException("CSV file not found: " + csvPath);
        }
    }

    private Map<String, PlayerDTO> getPlayerDTOsFromCsv(String csvPath) throws IOException {
        Map<String, PlayerDTO> cache = new HashMap<>();
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

    private PlayerDTO buildPlayerDTO(String csvLine) {
        PlayerRawData playerRawData = playerCsvDecoder.decode(csvLine);
        validatePlayerID(playerRawData.playerID());
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

    private void validatePlayerID(String playerID) {
        if (null == playerID || playerID.isEmpty() || playerID.isBlank()) {
            throw new IllegalArgumentException("invalid playerID");
        }
    }

}
