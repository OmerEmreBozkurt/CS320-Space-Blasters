package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndPage extends ScreenAdapter {
    private com.badlogic.gdx.Game game;
    private Stage stage;
    private SpriteBatch batch;
    private int score; // Score to display

    public EndPage(Game game, int score) {
        this.game = game;
        this.score = score;  // Set the score passed from StartPage or game logic
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("/Users/dorukesen/Desktop/SB/CS320-Space-Blasters/assets/ui/uiskin.json"));
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Display the score
        Label scoreLabel = new Label("Your Score: " + score, skin);
        scoreLabel.setFontScale(1.5f);

        // Button to go back to StartPage
        TextButton restartButton = new TextButton("Play Again", skin);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StartPage(game));  // Return to StartPage when clicked
            }
        });

        // If the score is in the top 3, ask for the player's name
        if (isTopScore(score)) {
            TextButton enterNameButton = new TextButton("Enter Name", skin);
            enterNameButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Logic for name entry goes here
                    // Show a text input dialog or similar functionality
                }
            });
            table.add(scoreLabel).padBottom(20).row();
            table.add(enterNameButton).padBottom(20).row();
        } else {
            table.add(scoreLabel).padBottom(20).row();
        }

        table.add(restartButton).padBottom(10);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    private boolean isTopScore(int score) {
        // Logic to check if the score is in the top 3
        return score >= 100;  // Placeholder logic, you should compare it with an actual score list
    }
}
