package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

public class StartPage extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private  BitmapFont creatorNames;
    private int topScore;
    private BitmapFont topScorer;
    private List<String> top5Scores;


    public int getTopScore() {return topScore;}

    public int setTopScore(int finalScore) {

        if (finalScore > topScore) {
            topScore = finalScore;
        }
        return topScore;
    }




    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        creatorNames = new BitmapFont();
        topScorer = new BitmapFont();


        top5Scores = com.github.OmerEmreBozkurt.Game.getTop5Scores();

        stage= new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        topScorer.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        topScorer.getData().setScale(2.0F);

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0F);





        Texture buttonTexture = new Texture("StartGameButton.png"); // Ensure the file is in the assets folder
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);
        buttonDrawable.setMinSize(240,80);


        ImageButton startButton = new ImageButton(buttonDrawable);
        startButton.setPosition(
            Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2 -15,
            Gdx.graphics.getHeight() / 4f
        );


        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // System.out.println("clicked");
                Gdx.app.exit();
            }
        });
        stage.addActor(startButton);

    }
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        font.draw(batch, "SPACE BLASTERS", Gdx.graphics.getWidth() / 2 - 135, Gdx.graphics.getHeight() / 2f);
        creatorNames.draw(batch, " PROUDLY CODED BY: \n Doruk Esen \n Doga Yagmur Ugut \n Eda Nur Yilmaz \n Poyraz Koroglu \n Omer Emre Bozkurt ", Gdx.graphics.getWidth() -Gdx.graphics.getWidth() , Gdx.graphics.getHeight()-680);
        topScorer.draw(batch, "  TOP 5 SCORES: \n" + top5Scores,-5 , Gdx.graphics.getHeight()-30);


        batch.end();

        stage.act();
        stage.draw();

    }
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
