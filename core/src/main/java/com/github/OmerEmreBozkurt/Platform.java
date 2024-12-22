package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Platform {
    private Vector2 position;
    private Sprite sprite = new Sprite(new Texture("Platform.png"));
    private float speed = 500;


    public Platform(){
        sprite.setScale(4);
        position = new Vector2(Gdx.graphics.getWidth()/2, sprite.getScaleY()*sprite.getHeight()/2 + 5);
    }

    public void Update(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) position.x -= deltaTime*speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) position.x += deltaTime*speed;

        if (position.x - (sprite.getWidth()*2 - 20) <= 0) position.x = (sprite.getWidth()*2 - 20);
        if (position.x + (sprite.getWidth()*2) >= Gdx.graphics.getWidth() - 10) position.x = Gdx.graphics.getWidth() - sprite.getWidth()*2 - 10;
    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public double getX(){
        return position.x;
    }

    public void setX(float x) {
        this.position.x = x; //Setting the new coordinates of the platform
    }

    public void go_left() {
        this.position.x -= -1.5f; // Moving the coordinates of the platform by 1.5 units to the left
    }

    public void go_right() {
        this.position.x += -1.5f; // Moving the coordinates of the platform by 1.5 units to the right
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
