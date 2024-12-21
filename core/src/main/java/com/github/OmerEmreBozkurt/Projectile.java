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
    public float initialSpeed = 800;
    private float speed = -initialSpeed;
    private float speedY = 0;


    public Projectile() {
        sprite.setScale(2);
        this.position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
    }

    public void Update(float deltaTime){
        position.y += deltaTime*speed;
        if (position.y >= Gdx.graphics.getHeight()){this.setSpeed(-initialSpeed);} // tavana çarparsa
        position.x += speedY*deltaTime;
        if (position.x <= 0){this.setSpeedY(-getSpeedY());} // sol duvara çarparsa
        if (position.x >= Gdx.graphics.getWidth()){this.setSpeedY(-getSpeedY());} //Sağ duvara Çarparsa
        if (position.y <= 0){ //Top düştüğünde
            position.x = Gdx.graphics.getWidth()/2;
            position.y = Gdx.graphics.getHeight();
        }
    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedY() {
        return speedY;
    }
}
