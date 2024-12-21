package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends ApplicationAdapter {
    private SpriteBatch batch;
    private Platform platform;
    private Projectile ball;
    private int score;
    private BitmapFont font;

    @Override
    public void create() {
        score = 0;
        batch = new SpriteBatch();
        platform = new Platform();
        ball = new Projectile();

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        platform.Draw(batch);
        ball.Draw(batch);
        font.draw(batch, "Score: " + score, 10, Gdx.graphics.getHeight() - 10);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static void main(String[] args) {
    }
}
