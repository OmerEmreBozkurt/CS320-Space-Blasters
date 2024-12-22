package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

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

    public Alien(){
        this.power_up = null;
    }

    public Alien(AlienType type) {
        this.type = type;
        this.texture = texturePicker();
        this.sprite = new Sprite(texture);
        this.power_up = null;
        this.sprite.setScale(3.4f);
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
        return new Texture("libgdx.png");
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

    public static Alien[] alienSpawner(int num_height_aliens, int num_width_aliens) {
        Random rand = new Random();
        int i = 0;
        Alien[] aliens = new Alien[num_height_aliens * num_width_aliens];

        for (int y = 0; y < num_height_aliens; y++) { // Fixed loop to count upwards
            for (int x = 0; x < num_width_aliens; x++) { // Fixed loop to count upwards
                int randomInt = rand.nextInt(Alien.AlienType.values().length); // Random AlienType
                Alien.AlienType randomType = Alien.AlienType.values()[randomInt];
                System.out.println(randomType);
                switch (randomType) {
                    case Easy:
                        aliens[i] = new Easy_Alien();
                        break;
                    case Medium:
                        aliens[i] = new Medium_Alien();
                        break;
                    case Hard:
                        aliens[i] = new Hard_Alien();
                        break;
                    case ExtraHard:
                        aliens[i] = new ExtraHard_Alien();
                        break;
                }
                aliens[i].setPosition(new Vector2(183 + 80*x, 350 + 80*y));
                i++;
            }
        }
        return aliens;
    }
}
