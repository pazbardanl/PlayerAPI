package com.pazbarda.playerapi.controllers;

import com.pazbarda.playerapi.model.PlayerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class PlayerController {

    @GetMapping("/api/players")
    public Set<String> getAllPlayerIDs() {
        return new HashSet<>() {{
            add("qweqwe");
            add("zxczxc");
        }};
    }

    @GetMapping("/api/player/{playerID}")
    public PlayerDTO getPlayerByID(@PathVariable String playerID) {
        validatePlayerID(playerID);
        return new PlayerDTO.Builder("abcdef")
                .withNameFirst("John")
                .withNameLast("Doe")
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private void validatePlayerID(String playerID) {
        if (null == playerID || playerID.isBlank() || playerID.isEmpty()) {
            throw new IllegalArgumentException("player ID cannot be null, blank or empty string");
        }
    }
}
