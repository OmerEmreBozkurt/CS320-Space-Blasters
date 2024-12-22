package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;

public class StartPage extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Sprite sprite;






    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();


        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);


        sprite = new Sprite(new Texture("Platform.png"));;



    }
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        font.draw(batch, "SPACE BLASTERS", Gdx.graphics.getWidth() / 2 -140, Gdx.graphics.getHeight() / 2 +25);
        sprite.setSize(100,20 );
        sprite.setPosition(Gdx.graphics.getWidth()/2-50 , Gdx.graphics.getHeight()/2-60);
        sprite.draw(batch);
        batch.end();


    }
    public void dispose() {
        batch.dispose();
        font.dispose();


    }
}
