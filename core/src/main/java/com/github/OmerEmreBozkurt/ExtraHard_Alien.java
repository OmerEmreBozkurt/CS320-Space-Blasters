package com.github.OmerEmreBozkurt;

public class ExtraHard_Alien extends Alien {
    public ExtraHard_Alien() {
        super(AlienType.ExtraHard);
        life = 4;
    }

    public int death(){
        alive = false;
        return 200;
    }
}
