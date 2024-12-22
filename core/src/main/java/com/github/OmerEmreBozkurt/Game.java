package com.github.OmerEmreBozkurt;


import com.badlogic.gdx.Gdx;

public class Game  {
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

//    public int getTopScore() {return topScore;}
//
//    public void setTopScore(int newScore) {
//    if (newScore > topScore) {
//        topScore = newScore;
//    }
//    }



    public void Update(float deltaTime){

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
        if (ballCount == 0) {
            endGame();
        }
    }

    public void endGame() {
        running = false;
        Gdx.app.exit();

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
