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
    private Game game;
    public float initialSpeed = 800;
    private float speedY = -initialSpeed;
    private float speedX = 0;



    public Projectile(Game game) {
        this.game = game;
        sprite.setScale(2);
        this.position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3);
    }

    public void Update(float deltaTime){
        position.y += deltaTime*speedY;
        if (position.y >= Gdx.graphics.getHeight()){this.setSpeedY(-initialSpeed);} // tavana çarparsa
        position.x += speedX*deltaTime;
        if (position.x <= 0){this.setSpeedX(-getSpeedX());} // sol duvara çarparsa
        if (position.x >= Gdx.graphics.getWidth()){this.setSpeedX(-getSpeedX());} //Sağ duvara Çarparsa
        if (position.y <= 0){ //Top düştüğünde
            game.decrementBallCount();
            position.x = Gdx.graphics.getWidth()/3;
            position.y = Gdx.graphics.getHeight();
        }
    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedX() {
        return speedX;
    }
}
