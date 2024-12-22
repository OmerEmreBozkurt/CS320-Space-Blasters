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
        ball = new Projectile();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);

        int i = 0;
        Random rand = new Random();

        // Example values; replace these with actual values as needed
        int num_height_aliens = 5;
        int num_width_aliens = 6;

        aliens = new Alien[num_height_aliens * num_width_aliens];

        for (int y = 0; y < num_height_aliens; y++) { // Fixed loop to count upwards
            for (int x = 0; x < num_width_aliens; x++) { // Fixed loop to count upwards
                int randomInt = rand.nextInt(Alien.AlienType.values().length); // Random AlienType
                Alien.AlienType randomType = Alien.AlienType.values()[randomInt];
                aliens[i] = new Alien(randomType);
                aliens[i].setPosition(new Vector2(183 + 80*x, 350 + 80*y));
                i++;
            }
        }
    }



    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        platform.Draw(batch);
        ball.Draw(batch);
        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive && ball.getSprite().getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle())) {
                aliens[i].take_damage();  // Mark the alien as dead
                game.updateScore(aliens[i].getPoints());  // Update score based on alien's points
            }
        }

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                aliens[i].Draw(batch);
            }
        }

        if (platform.getSprite().getBoundingRectangle().overlaps(ball.getSprite().getBoundingRectangle())) {
            System.out.println("ÇARPTI!!!!!");
            ball.setSpeed(ball.initialSpeed);
            float testFloat = rand.nextFloat(-300f,300f);
            System.out.printf(String.valueOf(testFloat));
            ball.setSpeedY(rand.nextFloat(-300f,300f));
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
