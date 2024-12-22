package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Alien {

    enum AlienType{Easy,Medium,Hard,ExtraHard}

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 position;
    public Sprite sprite;
    int life;

    public int getPoints() {
        return points;
    }

    int points;
    public boolean alive = true;
    AlienType type;
    Power_Up power_up;
    Texture texture;


    public Alien(AlienType type) {
        this.type = type;
        this.texture = texturePicker();
        this.sprite = new Sprite(texture);
        this.power_up = null;
        this.sprite.setScale(2.0f);
        this.points = (type.ordinal()+1)*10;
    }
    public void Draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }


    Texture texturePicker(){
        if (power_up == null ) {
            return new Texture("BaseAlien.png");
        } else if (power_up.getPowerUpType() == 1) {
            return new Texture("BaseAlien-PowerUp1.png");
        } else if (power_up.getPowerUpType() == 2) {
            return new Texture("BaseAlien-PowerUp2.png");
        } else if (power_up.getPowerUpType() == 3) {
            return new Texture("BaseAlien-PowerUp3.png");
        }
        return new Texture("Ball.png");
    }

    public void take_damage(){
        int damage = 1; // Default damage
        if (power_up != null && power_up.isActive() && power_up.getPowerUpType() == 2) {
            damage = 2; // double the damage
        }
        life -= damage;
        if (life <= 0) {
            this.death();
        }
    }

    public boolean isAlive(){return alive;}

    public int death(){
        alive = false;
        return 0;
    }
}
