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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Set;

@RestController
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerDAO playerDAO;

    /**
     * Get all player's IDs in a Set<String>
     */
    @GetMapping("/api/players")
    public Set<String> getAllPlayerIDs() {
        logger.info("Received request for all player IDs");
        return playerDAO.getPlayerIDs();
    }

    /*
     * Get player's data given the player's ID.
     * */
    @GetMapping("/api/player/{playerID}")
    public PlayerDTO getPlayerByID(@PathVariable String playerID) {
        validatePlayerID(playerID);
        logger.info("Getting data for player ID = " + playerID);
        return playerDAO.getPlayer(playerID);
    }

    /*
     * exception handler for IllegalArgumentException
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /*
     * exception handler for NoSuchElementException
     * */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private void validatePlayerID(String playerID) throws IllegalArgumentException {
        if (null == playerID || playerID.isBlank() || playerID.isEmpty()) {
            final String errorMessage = "player ID cannot be null, blank or empty string";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
