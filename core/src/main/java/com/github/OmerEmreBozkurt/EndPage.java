package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class EndPage extends Game implements Screen  {
    private SpriteBatch batch;
    private BitmapFont font;
    private int finalScore;

    public EndPage( int finalScore) {
        this.finalScore = finalScore;  // Pass the score from the Core class to the EndPage
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();  // Use the default font for text display
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0f);  // Set text size for better visibility
    }

    @Override
    public void render() {
        // Clear the screen with a color (dark background)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Draw the "Game Over" text in the center of the screen
        font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 50);

        // Draw the score message just below the "Game Over" text
        font.draw(batch, "Your Score: " + finalScore, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 50);

        batch.end();

        // Optionally, add functionality to restart or exit the game (e.g., clicking anywhere)
        if (Gdx.input.isTouched()) {
            // You can choose to go back to the main menu or quit when the screen is touched
            // To quit the application, use Gdx.app.exit() or you can setScreen(new StartPage()) to go back to the main menu
            Gdx.app.exit();
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();  // Dispose of resources when done
        font.dispose();
    }
}
