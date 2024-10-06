package com.pazbarda.playerapi.services;

import com.pazbarda.playerapi.model.PlayerRawData;
import org.springframework.stereotype.Service;

/*
* Decode CSV lines into a Player's raw data. Handles all the mechanics of dealing with a CSV line.
* */
@Service
public class PlayerCsvDecoder {

    /*
     * Decode CSV lines into a Player's raw data.
     * */
    public PlayerRawData decode(String csvLine) {
        String[] csvLineTokens = csvLine.split(",");
        String playerID = csvLineTokens.length > 0 ? csvLineTokens[0] : null;
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

        return new PlayerRawData(
                playerID,
                birthYear,
                birthMonth,
                birthDay,
                birthCountry,
                birthState,
                birthCity,
                deathYear,
                deathMonth,
                deathDay,
                deathCountry,
                deathState,
                deathCity,
                nameFirst,
                nameLast,
                nameGiven,
                weight,
                height,
                bats,
                shoots,
                dateOfDebutString,
                dateOfFinalGameString,
                retroID,
                bbrefID
        );
    }

    private Integer safeParseIntegerFrom(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
