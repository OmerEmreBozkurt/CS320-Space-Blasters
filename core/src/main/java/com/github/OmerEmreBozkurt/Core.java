package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
public class Core extends ApplicationAdapter implements Screen {
    private SpriteBatch batch;
    private Platform platform;
    private Projectile ball;
    private static Game game;
    private BitmapFont font;
    Alien[] aliens;
    private Music backgroundMusic;

    private Music thudSound;

    private Music deathSound;

    Random rand = new Random();
    int direction_aliens=1;
    float speed_aliens=0.5f;

    @Override
    public void create() {

        game = new Game();
        batch = new SpriteBatch();
        platform = new Platform();
        ball = new Projectile(game);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);

        resetAliens();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("thememusic.mp3"));
        backgroundMusic.setLooping(true); // Loop the music
        backgroundMusic.setVolume(0.5f); // Adjust volume (0.0 to 1.0)
        backgroundMusic.play(); // Start playing the music

        thudSound = Gdx.audio.newMusic(Gdx.files.internal("thudsound.mp3"));
        thudSound.setVolume(0.1F);

        deathSound = Gdx.audio.newMusic(Gdx.files.internal("alienDeath.mp3"));
        deathSound.setVolume(0.1F);
    }



    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        platform.Draw(batch);
        ball.Draw(batch);
        game.Update(deltaTime);
        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                if (ball.getSprite().getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle())) {//TOP UZAYLIYA ÇARPARSA
                    if (!aliens[i].isTouched()) {
                        deathSound.play();
                        aliens[i].take_damage(); // Decrement life
                        aliens[i].setTouched(true); // Mark as touched
                    }
                } else {
                    aliens[i].resetTouched(); // Reset touched when ball leaves
                }

                if ((aliens[i].alive && aliens[i].position.y <= 0) || aliens[i].alive && aliens[i].sprite.getBoundingRectangle().overlaps(platform.getSprite().getBoundingRectangle())) { //uzaylılar yere ya da platforma değdiğinde
                    game.endGame();
                }
            }
        }

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i] != null && aliens[i].alive) {
                aliens[i].position.x += speed_aliens*direction_aliens;
                aliens[i].position.y -= 0.03f;
                aliens[i].Draw(batch);
            }
            if(aliens[i].position.x >= Gdx.graphics.getWidth() - aliens[i].sprite.getWidth()/2 - 100) {
                direction_aliens = -1;
            }
            if(aliens[i].position.x <= -(aliens[i].sprite.getWidth()/2) + 100) {
                direction_aliens = 1;
            }
        }

        if (!aliveAlienChecker(aliens)){
            game.levelUp();
            resetAliens();
        }


        if (platform.getSprite().getBoundingRectangle().overlaps(ball.getSprite().getBoundingRectangle())) { //TOP PLAYFORMA ÇARPARSA
            ball.setSpeedY(-(ball.initialSpeed));
            //ball.setSpeedX(rand.nextFloat(-300f,300f));
            if (platform.getX() - ball.getX() > 0 ) {
                ball.setSpeedX((float) ((platform.getX() - ball.getX()) * rand.nextFloat(0f, 5f)));
            } else {
                ball.setSpeedX((float) ((platform.getX() - ball.getX()) * rand.nextFloat(-5f, 0f)));
            } //sadece sağa sekiyo çünkü plat sprite 0dan başlıyo
            thudSound.stop();
            thudSound.play();
        }


        font.draw(batch, "Score: " + game.getScore(), 10, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Balls: " + game.getBallCount(), 10, Gdx.graphics.getHeight() - 40);
        font.draw(batch, "Level: " + game.getLevel(), 10, Gdx.graphics.getHeight() - 70);
        batch.end();

    }

    public boolean aliveAlienChecker(Alien[] aliens) {
        boolean anyAlive = false;
        for (Alien alien : aliens) {
            if (alien != null && alien.alive) {
                anyAlive = true;
                break;
            }
        }
        return anyAlive;
    }

    public void resetAliens() {
        aliens= Alien.alienSpawner(5,6, game);
    }

    public static Game getGame() {
        return game;
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
        batch.dispose();
        font.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.dispose(); // Dispose the music to avoid memory leaks
        }
    }

    public static void main(String[] args) {
    }
}
