package com.github.OmerEmreBozkurt;



import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class StartPage extends ScreenAdapter {
    private com.badlogic.gdx.Game game;
    private Stage stage;
    private SpriteBatch batch;

    public StartPage(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("../assets/ui/uiskin.json"));
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("Space Blasters", skin);
        title.setFontScale(2);

        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new EndPage(game, 100));
            }
        });

        TextButton exitButton = new TextButton("Exit Game", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(title).padBottom(20).row();
        table.add(startButton).padBottom(10).row();
        table.add(exitButton);
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
}

