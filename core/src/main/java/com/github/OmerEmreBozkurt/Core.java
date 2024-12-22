package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends ApplicationAdapter {
    private SpriteBatch batch;
    private Platform platform;
    private Projectile ball;
    private Game game;
    private BitmapFont font;
    Alien[] aliens;
    private Music backgroundMusic;

    Random rand = new Random();
    int minX_aliens;
    int minY_aliens;
    int maxX_aliens;
    int maxY_aliens;
    //offset to move aliens
    Vector2 offset_aliens;
    int direction_aliens=1;
    float speed_aliens=300;
    int NumWidth_aliens=11;
    int NumHeight_aliens=5;
    int spacing_aliens=40;


    @Override
    public void create() {
        game = new Game();
        batch = new SpriteBatch();
        platform = new Platform();
        ball = new Projectile(game);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);

        aliens= Alien.alienSpawner(5,6, game);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("thememusic.mp3"));
        backgroundMusic.setLooping(true); // Loop the music
        backgroundMusic.setVolume(0.5f); // Adjust volume (0.0 to 1.0)
        backgroundMusic.play(); // Start playing the music
    }



    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        platform.Draw(batch);
        ball.Draw(batch);
        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                if (ball.getSprite().getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle())) {
                    if (!aliens[i].isTouched()) {
                        aliens[i].take_damage(); // Decrement life
                        aliens[i].setTouched(true); // Mark as touched
                    }
                } else {
                    aliens[i].resetTouched(); // Reset touched when ball leaves
                }
            }
        }
        minX_aliens = 10000;
        minY_aliens = 10000;
        maxX_aliens = 0;
        maxY_aliens = 0;
        System.out.println("aa");
        for(int i=0;i<aliens.length;i++)
        {
            System.out.println("bb");
            int IndexX = i%NumWidth_aliens;
            int IndexY = i%NumHeight_aliens;
            if(IndexX>maxX_aliens)maxX_aliens = IndexX;
            if(IndexX<minX_aliens)minX_aliens = IndexX;
            if(IndexY>maxY_aliens)maxY_aliens = IndexY;
            if(IndexY<minY_aliens)minY_aliens = IndexY;

        }
        offset_aliens.x+=direction_aliens*deltaTime*speed_aliens;

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                aliens[i].position = new Vector2(aliens[i].position_initial.x+offset_aliens.x,aliens[i].position_initial.y+offset_aliens.y);
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
        font.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.dispose(); // Dispose the music to avoid memory leaks
        }
    }

    public static void main(String[] args) {
    }
}
