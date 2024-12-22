package com.github.OmerEmreBozkurt;

public class Game {
    private int score, ballCount, level;
    private Power_Up activePowerUp;

    public Game(){
        this.score = 0;
        this.ballCount = 3;
        this.activePowerUp = null;
        this.level = 1;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        if(activePowerUp != null && activePowerUp.isActive() && activePowerUp.getPowerUpType() == 3){
            this.score = score + (2*points);
        } else {
            this.score += points;
        }
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
