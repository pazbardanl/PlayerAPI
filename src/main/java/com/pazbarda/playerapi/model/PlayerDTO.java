package com.pazbarda.playerapi.model;

import java.time.LocalDate;
import java.util.Objects;

/*
* Data Transfer Object for a player's data
* */
public class PlayerDTO {
    private final String playerID;
    private LocalDate dateOfBirth;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private LocalDate dateOfDeath;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private String bats;
    private String shoots;
    private String dateOfDebutString;
    private String dateOfFinalGameString;
    private String retroID;
    private String bbrefID;

    private PlayerDTO(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public String getBirthState() {
        return birthState;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public String getDeathCountry() {
        return deathCountry;
    }

    public String getDeathState() {
        return deathState;
    }

    public String getDeathCity() {
        return deathCity;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getNameGiven() {
        return nameGiven;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public String getBats() {
        return bats;
    }

    public String getShoots() {
        return shoots;
    }

    public String getDateOfDebutString() {
        return dateOfDebutString;
    }

    public String getDateOfFinalGameString() {
        return dateOfFinalGameString;
    }

    public String getRetroID() {
        return retroID;
    }

    public String getBbrefID() {
        return bbrefID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return weight == playerDTO.weight && height == playerDTO.height && Objects.equals(playerID, playerDTO.playerID) && Objects.equals(dateOfBirth, playerDTO.dateOfBirth) && Objects.equals(birthCountry, playerDTO.birthCountry) && Objects.equals(birthState, playerDTO.birthState) && Objects.equals(birthCity, playerDTO.birthCity) && Objects.equals(dateOfDeath, playerDTO.dateOfDeath) && Objects.equals(deathCountry, playerDTO.deathCountry) && Objects.equals(deathState, playerDTO.deathState) && Objects.equals(deathCity, playerDTO.deathCity) && Objects.equals(nameFirst, playerDTO.nameFirst) && Objects.equals(nameLast, playerDTO.nameLast) && Objects.equals(nameGiven, playerDTO.nameGiven) && Objects.equals(bats, playerDTO.bats) && Objects.equals(shoots, playerDTO.shoots) && Objects.equals(dateOfDebutString, playerDTO.dateOfDebutString) && Objects.equals(dateOfFinalGameString, playerDTO.dateOfFinalGameString) && Objects.equals(retroID, playerDTO.retroID) && Objects.equals(bbrefID, playerDTO.bbrefID);
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "playerID='" + playerID + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", birthCountry='" + birthCountry + '\'' +
                ", birthState='" + birthState + '\'' +
                ", birthCity='" + birthCity + '\'' +
                ", dateOfDeath=" + dateOfDeath +
                ", deathCountry='" + deathCountry + '\'' +
                ", deathState='" + deathState + '\'' +
                ", deathCity='" + deathCity + '\'' +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", nameGiven='" + nameGiven + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", bats='" + bats + '\'' +
                ", shoots='" + shoots + '\'' +
                ", dateOfDebut=" + dateOfDebutString +
                ", dateOfFinalGame=" + dateOfFinalGameString +
                ", retroID='" + retroID + '\'' +
                ", bbrefID='" + bbrefID + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID, dateOfBirth, birthCountry, birthState, birthCity, dateOfDeath, deathCountry, deathState, deathCity, nameFirst, nameLast, nameGiven, weight, height, bats, shoots, dateOfDebutString, dateOfFinalGameString, retroID, bbrefID);
    }

    public static class Builder {
        private PlayerDTO playerDTO;

        public Builder(String playerID) {
            playerDTO = new PlayerDTO(playerID);
        }

        public Builder withDateOfBirth(int year, int month, int dayOfMonth) {
            playerDTO.dateOfBirth = LocalDate.of(year, month, dayOfMonth);
            return this;
        }

        public Builder withBirthCountry(String birthCountry) {
            playerDTO.birthCountry = birthCountry;
            return this;
        }

        public Builder withBirthState(String birthState) {
            playerDTO.birthState = birthState;
            return this;
        }

        public Builder withBirthCity(String birthCity) {
            playerDTO.birthCity = birthCity;
            return this;
        }

        public Builder withDateOfDeath(int year, int month, int dayOfMonth) {
            playerDTO.dateOfDeath = LocalDate.of(year, month, dayOfMonth);
            return this;
        }

        public Builder withDeathCountry(String deathCountry) {
            playerDTO.deathCountry = deathCountry;
            return this;
        }

        public Builder withDeathState(String deathState) {
            playerDTO.deathState = deathState;
            return this;
        }

        public Builder withDeathCity(String deathCity) {
            playerDTO.deathCity = deathCity;
            return this;
        }

        public Builder withNameFirst(String nameFirst) {
            playerDTO.nameFirst = nameFirst;
            return this;
        }

        public Builder withNameLast(String nameLast) {
            playerDTO.nameLast = nameLast;
            return this;
        }

        public Builder withNameGiven(String nameGiven) {
            playerDTO.nameGiven = nameGiven;
            return this;
        }

        public Builder withWeigh(int weight) {
            playerDTO.weight = weight;
            return this;
        }

        public Builder withHeight(int height) {
            playerDTO.height = height;
            return this;
        }

        public Builder withBats(String bats) {
            playerDTO.bats = bats;
            return this;
        }

        public Builder withShoots(String shoots) {
            playerDTO.shoots = shoots;
            return this;
        }

        public Builder withDateOfDebut(String debutDateString) {
            playerDTO.dateOfDebutString = debutDateString;
            return this;
        }

        public Builder withDateOfFinalGame(String finalGameDateString) {
            playerDTO.dateOfFinalGameString = finalGameDateString;
            return this;
        }

        public Builder withRetroID(String retroID) {
            playerDTO.retroID = retroID;
            return this;
        }

        public Builder withBbrefID(String bbrefID) {
            playerDTO.bbrefID = bbrefID;
            return this;
        }

        public PlayerDTO build() {
            return playerDTO;
        }

    }
}
