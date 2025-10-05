package com.crashrocket.demo.controller;

import com.crashrocket.demo.model.GameState;
import com.crashrocket.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public GameState startGame(@RequestParam double bet) {
        return gameService.startGame(bet);
    }

    @PostMapping("/tick")
    public GameState tick() {
        return gameService.tick();
    }

    @PostMapping("/cashout")
    public String cashOut() {
        return gameService.cashOut();
    }

    @GetMapping("/status")
    public GameState status() {
        return gameService.getStatus();
    }
}