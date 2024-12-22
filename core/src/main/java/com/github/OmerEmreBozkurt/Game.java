package com.github.OmerEmreBozkurt;


import com.badlogic.gdx.Gdx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game  {
    private int score, ballCount, level;
    private Power_Up activePowerUp;
    boolean running = false;

    private static final String FILE_NAME = "core/src/main/java/com/github/OmerEmreBozkurt/scores.txt";

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

    public static void saveScore(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "-" + score);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Dosyaya yazarken hata oluştu: " + e.getMessage());
        }
    }

    public static List<String> getTop5Scores() {
        List<String> scores = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            List<Map.Entry<String, Integer>> scoreEntries = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    scoreEntries.add(Map.entry(name, score));
                }
            }

            // Skorları büyükten küçüğe sırala
            scoreEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            // İlk 5 skoru al
            for (int i = 0; i < Math.min(5, scoreEntries.size()); i++) {
                Map.Entry<String, Integer> entry = scoreEntries.get(i);
                scores.add((i + 1) + ". " + entry.getKey() + " - " + entry.getValue() + "\n");
            }

        } catch (IOException e) {
            System.out.println("Dosyadan okurken hata oluştu: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Dosyada geçersiz skor formatı bulundu.");
        }

        return scores;
    }


    public void Update(float deltaTime){}

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
