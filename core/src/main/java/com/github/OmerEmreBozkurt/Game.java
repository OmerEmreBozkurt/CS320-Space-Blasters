package com.github.OmerEmreBozkurt;

public class Game {
    private int score, ballCount, level;
    private Power_Up activePowerUp;
    boolean running = false;

    public Game(){
        this.score = 0;
        this.ballCount = 3;
        this.activePowerUp = null;
        this.level = 1;
        this.running = true;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        this.score += points;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void decrementBallCount() {
        ballCount--;
        if (this.activePowerUp != null) {
            this.activePowerUp.deactivate();
            this.activePowerUp = null;
        }
        if (ballCount < 0) {
            endGame();
        }
    }

    public void endGame() {
        running = false;
        //END GAME
    }

    public void incrementBallCount() {
        ballCount++;
    }


    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level++;
    }

    public Power_Up getActivePowerUp() {
        return activePowerUp;
    }

    public void setActive_power_up(Power_Up power_up) {
        if (this.activePowerUp != null) {
            this.activePowerUp.deactivate(); // Deactivate previous power-up
        }
        this.activePowerUp = power_up;
        if (this.activePowerUp != null) {
            this.activePowerUp.activate();
        }
    }
}
