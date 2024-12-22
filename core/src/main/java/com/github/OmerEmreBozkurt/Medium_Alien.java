package com.github.OmerEmreBozkurt;

import com.badlogic.gdx.graphics.Texture;

public class Medium_Alien extends Alien {
    public Medium_Alien() {
        super(AlienType.Medium);
        sprite.setTexture(texturePicker());
        life = 2;
    }

    public int death(){
        alive = false;
        return 100;
    }

    Texture texturePicker(){
        if (power_up == null ) {
            return new Texture("Tier2.png");
        } else if (power_up.getPowerUpType() == 1) {
            return new Texture("Tier2Pow1.png");
        } else if (power_up.getPowerUpType() == 2) {
            return new Texture("Tier2Pow2.png");
        } else if (power_up.getPowerUpType() == 3) {
            return new Texture("Tier2Pow3.png");
        }
        return new Texture("libgdx.png");
    }
}
