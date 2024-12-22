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
    public Vector2 position_initial;
    public Sprite sprite;
    int life;
    Game game;
    private boolean touched;

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

    public Alien(AlienType type, Game game) {
        this.type = type;
        this.texture = texturePicker();
        this.sprite = new Sprite(texture);
        this.power_up = null;
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
        return new Texture("libgdx.png");
    }

    public void take_damage(){
        int damage = 1; // Default damage
        if (power_up != null && power_up.isActive() && power_up.getPowerUpType() == 2) {
            damage = 2; // double the damage
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
        game.updateScore(points); // call game class update points with this.points()
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

                // Assign random power-up with a 50% chance
                if (alien != null && rand.nextBoolean()) {
                    Power_Up powerUp = new Power_Up(rand.nextInt(Power_Up.PowerUpType.values().length) + 1);
                    alien.power_up = powerUp; // Initialize power-up
                }

                if (alien != null) {
                    alien.setPosition(new Vector2(183 + 80 * x, 350 + 80 * y));
                    aliens[i++] = alien;
                }
            }
        }
        return aliens;
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
}
