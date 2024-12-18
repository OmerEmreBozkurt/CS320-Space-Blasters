package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.ApplicationListener;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.*;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core  {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration() {};

        config.setWindowedMode(800, 800);

        // Directly set Game_ScreenUI as the screen to be shown
        new Lwjgl3Application(new Game_ScreenUI() {
            @Override
            public void create() {
                super.create();
            }

            @Override
            public void render() {
                super.render();
            }

            @Override
            public void dispose() {
                super.dispose();
            }
        }, config);
    }
}
