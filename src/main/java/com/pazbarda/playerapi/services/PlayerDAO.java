package com.pazbarda.playerapi.services;

import com.pazbarda.playerapi.model.PlayerDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
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

    // TODO PB -- make its own service?
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
        String[] csvLineTokens = csvLine.split(",");
        String playerID = csvLineTokens[0];
        if (null == playerID || playerID.isEmpty() || playerID.isBlank()) {
            throw new IllegalArgumentException("invalid playerID");
        }
        Integer birthYear = csvLineTokens.length > 1 ? safeParseIntegerFrom(csvLineTokens[1]) : null;
        Integer birthMonth = csvLineTokens.length > 2 ? safeParseIntegerFrom(csvLineTokens[2]) : null;
        Integer birthDay = csvLineTokens.length > 3 ? safeParseIntegerFrom(csvLineTokens[3]) : null;
        String birthCountry = csvLineTokens.length > 4 ? csvLineTokens[4] : null;
        String birthState = csvLineTokens.length > 5 ? csvLineTokens[5] : null;
        String birthCity = csvLineTokens.length > 6 ? csvLineTokens[6] : null;
        Integer deathYear = csvLineTokens.length > 7 ? safeParseIntegerFrom(csvLineTokens[7]) : null;
        Integer deathMonth = csvLineTokens.length > 8 ? safeParseIntegerFrom(csvLineTokens[8]) : null;
        Integer deathDay = csvLineTokens.length > 9 ? safeParseIntegerFrom(csvLineTokens[9]) : null;
        String deathCountry = csvLineTokens.length > 10 ? csvLineTokens[10] : null;
        String deathState = csvLineTokens.length > 11 ? csvLineTokens[11] : null;
        String deathCity = csvLineTokens.length > 12 ? csvLineTokens[12] : null;
        String nameFirst = csvLineTokens.length > 13 ? csvLineTokens[13] : null;
        String nameLast = csvLineTokens.length > 14 ? csvLineTokens[14] : null;
        String nameGiven = csvLineTokens.length > 15 ? csvLineTokens[15] : null;
        Integer weight = csvLineTokens.length > 16 ? safeParseIntegerFrom(csvLineTokens[16]) : null;
        Integer height = csvLineTokens.length > 17 ? safeParseIntegerFrom(csvLineTokens[17]) : null;
        String bats = csvLineTokens.length > 18 ? csvLineTokens[18] : null;
        String shoots = csvLineTokens.length > 19 ? csvLineTokens[19] : null;
        String dateOfDebutString = csvLineTokens.length > 20 ? csvLineTokens[20] : null;
        String dateOfFinalGameString = csvLineTokens.length > 21 ? csvLineTokens[21] : null;
        String retroID = csvLineTokens.length > 22 ? csvLineTokens[22] : null;
        String bbrefID = csvLineTokens.length > 23 ? csvLineTokens[23] : null;

        PlayerDTO.Builder playerDtoBuilder = new PlayerDTO.Builder(playerID);

        if (birthYear != null && birthMonth != null && birthDay != null) {
            playerDtoBuilder.withDateOfBirth(birthYear, birthMonth, birthDay);
        }
        if (deathYear != null && deathMonth != null && deathDay != null) {
            playerDtoBuilder.withDateOfBirth(deathYear, deathMonth, deathDay);
        }

        return playerDtoBuilder
                .withBirthCountry(birthCountry)
                .withBirthState(birthState)
                .withBirthCity(birthCity)
                .withDeathCountry(deathCountry)
                .withDeathState(deathState)
                .withDeathCity(deathCity)
                .withNameFirst(nameFirst)
                .withNameLast(nameLast)
                .withNameGiven(nameGiven)
                .withBats(bats)
                .withShoots(shoots)
                .withDateOfDebut(dateOfDebutString)
                .withDateOfFinalGame(dateOfFinalGameString)
                .withRetroID(retroID)
                .withBbrefID(bbrefID)
                .build();
    }

    private Integer safeParseIntegerFrom(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }


}
