package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public  class Game_ScreenUI extends Game  {

   private Skin skin;
   private Stage stage;
   private Label scoreLabel;

    private SpriteBatch batch;

    private Image platformImage;
    private Image ballImage;



    public void create() {
        Skin skin = new Skin(Gdx.files.internal("../assets/ui/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int score= getScore();
        scoreLabel = new Label("Score: "  + score ,skin, null);

        scoreLabel.setPosition(10,Gdx.graphics.getHeight()-30);

        stage.addActor(scoreLabel);

         batch = new SpriteBatch();

        Texture platformTexture = new Texture(Gdx.files.internal("platform.png"));
        Texture ballTexture = new Texture(Gdx.files.internal("ball.png"));

        platformImage = new Image(platformTexture);
        ballImage = new Image(ballTexture);

        platformImage.setSize(100,20);
        platformImage.setPosition(800/2 - platformImage.getWidth()/2,0);

        ballImage.setPosition(Gdx.graphics.getWidth() / 2 - ballImage.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - ballImage.getHeight() / 2);

        stage.addActor(platformImage);
        stage.addActor(ballImage);

    }
    @Override
    public void render() {
        super.render();

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update logic for game (e.g., movement, collision detection, etc.)

        // Draw everything on the stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
}


