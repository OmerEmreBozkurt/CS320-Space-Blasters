package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Sound;


public class Projectile {
    private Vector2 position;
    private Sprite sprite = new Sprite(new Texture("Ball.png"));
    private Game game;
    public float initialSpeed = -800;
    private float speedY = initialSpeed;
    private float speedX = 0;
    private Sound deathSound;




    public Projectile(Game game) {
        this.game = game;
        sprite.setScale(2);
        this.position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/7);
        deathSound = deathSound = Gdx.audio.newSound(Gdx.files.internal("BallFall.mp3"));;
    }

    public void Update(float deltaTime){
        position.y += deltaTime*speedY;
        if (position.y >= Gdx.graphics.getHeight()){this.setSpeedY(initialSpeed);} // tavana çarparsa
        position.x += speedX*deltaTime;
        if (position.x <= sprite.getWidth()/2){this.setSpeedX(-getSpeedX());} // sol duvara çarparsa
        if (position.x >= Gdx.graphics.getWidth() - sprite.getWidth() - 10){this.setSpeedX(-getSpeedX());} //Sağ duvara Çarparsa
        if (position.y <= 0){ //Top düştüğünde
            deathSound.play();
            game.decrementBallCount();
            if (game.running){
                resetBall();
            }
        }
    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void resetBall(){
        position.x = Gdx.graphics.getWidth()/2;
        position.y = Gdx.graphics.getHeight()/10;
        setSpeedY(-initialSpeed);
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
