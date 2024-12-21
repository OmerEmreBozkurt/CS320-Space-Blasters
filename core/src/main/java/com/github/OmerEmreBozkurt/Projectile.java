package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Sprite sprite = new Sprite(new Texture("Ball.png"));
    private float speed = 100;


    public Projectile() {
        sprite.setScale(4);
        this.position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
    }

    public void Update(float deltaTime){
        position.y -= deltaTime*speed;

    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
