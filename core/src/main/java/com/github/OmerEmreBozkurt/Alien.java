package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Alien {

    enum AlienType{Easy,Medium,Hard,ExtraHard}

    public Vector2 position;

    public Vector2 position_initial;
    public Sprite sprite;
    int life;
    Game game;
    private boolean touched;
    int points;
    public boolean alive = true;
    AlienType type;
    Power_Up power_up;
    Texture texture;
    public Alien(AlienType type, Game game) {
        this.type = type;
        this.texture = texturePicker();
        this.sprite = new Sprite(texture);
        this.power_up = Alien_Power_Up();
        this.sprite.setScale(3.4f);
        this.points = (type.ordinal()+1)*10;
        this.touched = false;
        this.game = game;
        this.position_initial = position;
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
        return new Texture("BaseAlien.png");
    }

    public void take_damage(){
        int damage = 1; // Default damage
        if (power_up != null && power_up.isActive() && power_up.getPowerUpType() == 2) {
            damage = 2; // double the damage
            System.out.println("You took a head-shot! Took 2 lives");
            power_up.deactivate();
        }
        life -= damage;
        sprite.setTexture(texturePicker());
        if (life <= 0) {
            this.death();
        }
    }

    public boolean isAlive(){return alive;}

    public int death(){
        alive = false;
        if(power_up != null && power_up.isActive() && power_up.getPowerUpType() == 3){
            game.updateScore(points*2); // Score Multiplier Power-Up
            power_up.deactivate();
            System.out.println("You are doubling your score!!");
        } else{
            game.updateScore(points);
        }

        if(power_up != null && power_up.isActive() && power_up.getPowerUpType() == 1){
            game.incrementBallCount(); // Life Giver Power_Up
            power_up.deactivate();
            System.out.println("Lucky you!! You have an extra ball!");
        }
        return 0;
    }

    public static Alien[] alienSpawner(int num_height_aliens, int num_width_aliens, Game game) {
        Random rand = new Random();
        int i = 0;
        Alien[] aliens = new Alien[num_height_aliens * num_width_aliens];

        for (int y = 0; y < num_height_aliens; y++) {
            for (int x = 0; x < num_width_aliens; x++) {
                int randomInt = rand.nextInt(Alien.AlienType.values().length);
                Alien.AlienType randomType = Alien.AlienType.values()[randomInt];
                Alien alien = null;

                // Instantiate aliens
                switch (randomType) {
                    case Easy:
                        alien = new Easy_Alien(game);
                        break;
                    case Medium:
                        alien = new Medium_Alien(game);
                        break;
                    case Hard:
                        alien = new Hard_Alien(game);
                        break;
                    case ExtraHard:
                        alien = new ExtraHard_Alien(game);
                        break;
                }

                // Assign random power-up with a chance


                if (alien != null) {
                    alien.setPosition(new Vector2(183 + 80 * x, 350 + 80 * y));
                    aliens[i++] = alien;
                }
            }
        }
        return aliens;
    }

    public Power_Up Alien_Power_Up() {
        Random rand = new Random();
        if (rand.nextDouble() < 0.25) { // 25% chance to have a Power-Up
            int randomPowerUpType = rand.nextInt(3) + 1; // Power-Up type 1, 2, or 3
            Power_Up powerUp = new Power_Up(randomPowerUpType);
            powerUp.activate();
            return powerUp;
        } else {
            return null; // No power-up
        }
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void resetTouched() {
        this.touched = false;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }
}
