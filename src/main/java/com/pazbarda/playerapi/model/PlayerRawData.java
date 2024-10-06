package com.pazbarda.playerapi.model;

/*
 * Record for raw player data, usually after being parsed from a data source (ex: CSV) and before it is built into a Player DTO
 * */
public record PlayerRawData(
        String playerID,
        Integer birthYear,
        Integer birthMonth,
        Integer birthDay,
        String birthCountry,
        String birthState,
        String birthCity,
        Integer deathYear,
        Integer deathMonth,
        Integer deathDay,
        String deathCountry,
        String deathState,
        String deathCity,
        String nameFirst,
        String nameLast,
        String nameGiven,
        Integer weight,
        Integer height,
        String bats,
        String shoots,
        String dateOfDebutString,
        String dateOfFinalGameString,
        String retroID,
        String bbrefID
) {
}
