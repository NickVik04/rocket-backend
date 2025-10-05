//package com.crashrocket.demo.service;
//import com.crashrocket.demo.model.GameState;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//@Service
//public class GameService {
//    private GameState gameState;
//    private Random random = new Random();
//
//    // Start a new game
//    public GameState startGame(double bet) {
//        double crashPoint = generateCrashPoint(0.8); // exponential distribution
//        gameState = new GameState(bet, crashPoint);
//        return gameState;
//    }
//
//    // Update multiplier each tick (like rocket flying)
//    public GameState tick() {
//        if (gameState == null || !gameState.isRunning()) return gameState;
//
//        double newMultiplier = gameState.getMultiplier() + 0.2;
//        gameState.setMultiplier(newMultiplier);
//
//        if (newMultiplier >= gameState.getCrashPoint()) {
//            gameState.setRunning(false);
//        }
//        return gameState;
//    }
//
//    // Cash out if still running
//    public String cashOut() {
//        if (gameState == null) return "No active game!";
//        if (!gameState.isRunning()) return "💥 Rocket already crashed!";
//        if (gameState.getMultiplier() < 1.01) return "⚠️ Cash out only after 1.01x";
//
//        gameState.setCashedOut(true);
//        gameState.setRunning(false);
//        double winnings = gameState.getBetAmount() * gameState.getMultiplier();
//        return "✅ Cashed out at " + String.format("%.2fx", gameState.getMultiplier()) +
//                " → Won: " + String.format("%.2f", winnings);
//    }
//
//    public GameState getStatus() {
//        return gameState;
//    }
//
//    // Exponential distribution for crash point
//    private double generateCrashPoint(double lambda) {
//        double u = random.nextDouble();
//        double value = -Math.log(1 - u) / lambda;
//        return Math.max(0.1, value);
//    }
//}

package com.crashrocket.demo.service;

import com.crashrocket.demo.model.GameState;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
    private GameState gameState;
    private Random random = new Random();

    private double houseEdge = 0.05; // 5% house edge
    private double lambda = 0.8; // controls crash distribution
    private double maxCrash = 10.0; // optional maximum crash limit

    // Start a new game
    public GameState startGame(double bet) {
        double crashPoint = generateCrashPoint(lambda);
        gameState = new GameState(bet, crashPoint);
        return gameState;
    }

    // Update multiplier each tick (like rocket flying)
    public GameState tick() {
        if (gameState == null || !gameState.isRunning()) return gameState;

        // exponential growth per tick
        double newMultiplier = gameState.getMultiplier() * 1.05; // 5% growth per tick
        gameState.setMultiplier(newMultiplier);

        // crash if multiplier exceeds crashPoint
        if (newMultiplier >= gameState.getCrashPoint()) {
            gameState.setRunning(false);
        }

        return gameState;
    }

    // Cash out if still running
    public String cashOut() {
        if (gameState == null) return "No active game!";
        if (!gameState.isRunning()) return "💥 Rocket already crashed!";
        if (gameState.getMultiplier() < 1.01) return "⚠️ Cash out only after 1.01x";

        gameState.setCashedOut(true);
        gameState.setRunning(false);
        double winnings = gameState.getBetAmount() * gameState.getMultiplier();
        return "✅ Cashed out at " + String.format("%.2fx", gameState.getMultiplier()) +
                " → Won: " + String.format("%.2f", winnings);
    }

    public GameState getStatus() {
        return gameState;
    }

    // Generate crash point with house edge and max crash limit
    private double generateCrashPoint(double lambda) {
        double u = random.nextDouble();
        double value = -Math.log(1 - u) / lambda;
        double crash = 1.0 + value;            // ensure minimum 1x
        crash = crash * (1 - houseEdge);       // apply house edge
        crash = Math.min(crash, maxCrash);     // apply maximum crash limit
        return crash;
    }
}

