package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends ApplicationAdapter {
    private SpriteBatch batch;
    private Platform platform;
    private Projectile ball;
    private Game game;
    private BitmapFont font;
    Alien[] aliens;

    Random rand = new Random();


    @Override
    public void create() {
        game = new Game();
        batch = new SpriteBatch();
        platform = new Platform();
        ball = new Projectile(game);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);

        aliens= Alien.alienSpawner(5,6);
    }



    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        platform.Draw(batch);
        ball.Draw(batch);
        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                if (ball.getSprite().getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle())) {
                    if (!aliens[i].isTouched()) {
                        aliens[i].take_damage(); // Decrement life
                        game.updateScore(aliens[i].getPoints()); // Update score
                        aliens[i].setTouched(true); // Mark as touched
                    }
                } else {
                    aliens[i].resetTouched(); // Reset touched when ball leaves
                }
            }
        }

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                aliens[i].Draw(batch);
            }
        }

        if (platform.getSprite().getBoundingRectangle().overlaps(ball.getSprite().getBoundingRectangle())) {
            ball.setSpeedY(ball.initialSpeed);
            ball.setSpeedX(rand.nextFloat(-300f,300f));
        }


        font.draw(batch, "Score: " + game.getScore(), 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Balls: " + game.getBallCount(), 10, Gdx.graphics.getHeight() - 40);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static void main(String[] args) {
    }
}
