package com.pazbarda.playerapi.controllers;

import com.pazbarda.playerapi.model.PlayerDTO;
import com.pazbarda.playerapi.services.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
public class PlayerController {

    @Autowired
    private PlayerDAO playerDAO;

    /**
     * Get all player's IDs in a Set<String>
    */
    @GetMapping("/api/players")
    public Set<String> getAllPlayerIDs() {
        return playerDAO.getPlayerIDs();
    }

    /*
    * Get player's data given the player's ID.
    * */
    @GetMapping("/api/player/{playerID}")
    public PlayerDTO getPlayerByID(@PathVariable String playerID) {
        validatePlayerID(playerID);
        return playerDAO.getPlayer(playerID);
    }

    /*
    * exception handler for IllegalArgumentException
    * */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /*
     * exception handler for NoSuchElementException
     * */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private void validatePlayerID(String playerID) {
        if (null == playerID || playerID.isBlank() || playerID.isEmpty()) {
            throw new IllegalArgumentException("player ID cannot be null, blank or empty string");
        }
    }
}
