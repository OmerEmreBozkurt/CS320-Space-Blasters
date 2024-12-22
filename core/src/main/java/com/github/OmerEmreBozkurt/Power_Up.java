package com.github.OmerEmreBozkurt;

public class Power_Up {
    enum PowerUpType {LiveGiver,AmplifyDamage,ScoreMultiplyier}

    public void setPower_up_type(int power_up_type) {
        this.power_up_type = power_up_type;
    }

    private int power_up_type; // 1 = live giver, 2 = amplify damage, 3 = score multiplier
    private boolean is_active;

    public Power_Up(int power_up_type) {
        this.power_up_type = power_up_type;
        this.is_active = false;
    }

    public int getPowerUpType() {
        return power_up_type;
    }
    public void activate() {
        is_active = true;
    }

    public void deactivate() {
        is_active = false;
    }

    public boolean isActive() {
        return is_active;
    }


}
