package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ExtraHard_Alien extends Alien {
    public ExtraHard_Alien() {
        super();
        this.type = AlienType.ExtraHard;
        this.sprite = new Sprite(texturePicker());
        life = 4;
    }

    public int death(){
        alive = false;
        return 200;
    }

    Texture texturePicker(){
        if (power_up == null ) {
            return new Texture("Tier4.png");
        } else if (power_up.getPowerUpType() == 1) {
            return new Texture("Tier4Pow1.png");
        } else if (power_up.getPowerUpType() == 2) {
            return new Texture("Tier4Pow2.png");
        } else if (power_up.getPowerUpType() == 3) {
            return new Texture("Tier4Pow3.png");
        }
        return new Texture("libgdx.png");
    }
}
