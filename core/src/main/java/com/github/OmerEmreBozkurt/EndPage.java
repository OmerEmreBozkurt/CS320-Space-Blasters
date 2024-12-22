package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EndPage extends Game implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private int finalScore;

    private Stage stage;
    private Skin skin;

    private String selectedChars = "";
    private String[] availableChars;

    private Label selectionLabel;

    public EndPage(int finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2.0f);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("assets/ui/uiskin.json")); // UI skin dosyası gerekli

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Başlık ve skor
        Label titleLabel = new Label("GAME OVER", skin);
        titleLabel.setFontScale(2);
        table.add(titleLabel).colspan(7).padBottom(20).row();

        Label scoreLabel = new Label("Your Score: " + finalScore, skin);
        scoreLabel.setFontScale(1.5f);
        table.add(scoreLabel).colspan(7).padBottom(20).row();

        // Alfabe Seçenekleri (A-Z)
        availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int columnCounter = 0;

        for (String character : availableChars) {
            TextButton charButton = new TextButton(character, skin);
            table.add(charButton).pad(5);

            charButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (selectedChars.length() < 3) {
                        selectedChars += character;
                        selectionLabel.setText("Selected: " + selectedChars);
                    }
                }
            });

            columnCounter++;
            if (columnCounter % 7 == 0) { // Her 7 butonda bir satır atla
                table.row();
            }
        }

        table.row();

        // Seçim Gösterimi
        selectionLabel = new Label("Selected: ", skin);
        selectionLabel.setFontScale(1.5f);
        table.add(selectionLabel).colspan(7).padTop(20).row();

        // Onay Butonu
        TextButton confirmButton = new TextButton("Confirm", skin);
        table.add(confirmButton).colspan(7).padTop(20);
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedChars.length() == 3) {
                    System.out.println("Selected Characters: " + selectedChars);
                    com.github.OmerEmreBozkurt.Game.saveScore(selectedChars, finalScore);
                    Gdx.app.exit(); // Programı kapat ya da başka bir sayfaya geç
                }
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void render(float delta) {
        render();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
        skin.dispose();
    }
}
