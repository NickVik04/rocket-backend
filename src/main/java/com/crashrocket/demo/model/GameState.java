package com.crashrocket.demo.model;

public class GameState {
    private double betAmount;
    private double multiplier;
    private double crashPoint;
    private boolean running;
    private boolean cashedOut;

    public GameState(double betAmount, double crashPoint) {
        this.betAmount = betAmount;
        this.crashPoint = crashPoint;
        this.multiplier = 1.0;
        this.running = true;
        this.cashedOut = false;
    }

    public double getBetAmount() { return betAmount; }
    public double getMultiplier() { return multiplier; }
    public void setMultiplier(double multiplier) { this.multiplier = multiplier; }

    public double getCrashPoint() { return crashPoint; }
    public boolean isRunning() { return running; }
    public void setRunning(boolean running) { this.running = running; }

    public boolean isCashedOut() { return cashedOut; }
    public void setCashedOut(boolean cashedOut) { this.cashedOut = cashedOut; }
}