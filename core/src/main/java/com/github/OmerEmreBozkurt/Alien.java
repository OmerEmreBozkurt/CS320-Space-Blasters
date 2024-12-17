package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Alien {

    enum AlienType{Easy,Medium,Hard,ExtraHard}

    public Vector2 position;
    public Sprite sprite;
    int life;
    AlienType type;
    Power_Up power_up;
    Texture texture;

    public Alien(AlienType type) {
        this.type = type;

        this.texture = texturePicker();
        this.sprite = new Sprite();
    }

    Texture texturePicker(){
        if (power_up.getPowerUpType() == 1) {
            return new Texture("BaseAlien-PowerUp1.png");
        } else if (power_up.getPowerUpType() == 2) {
            return new Texture("BaseAlien-PowerUp2.png");
        } else if (power_up.getPowerUpType() == 3) {
            return new Texture("BaseAlien-PowerUp3.png");
        } else {
            return new Texture("BaseAlien.png");
        }
    }

    public void take_damage(){
        life--;
        if (life == 0) {
            this.death();
        }
    }

    public boolean isAlive(){return life > 0;}

    public int death(){
        return 0;
    }
}
