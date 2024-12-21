package com.github.OmerEmreBozkurt.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.Game;

public class Main {

    public static void main(String[] args) {
        // Create the configuration for the application
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Space Blasters");
        config.setWindowedMode(800, 600);

        Game game = new Game() {
            @Override
            public void create() {
                this.setScreen(new StartPage(this));
            }

            public void endGame(int score) {
                this.setScreen(new EndPage(this, score));
            }
        };
        new Lwjgl3Application(game, config);
    }
}
