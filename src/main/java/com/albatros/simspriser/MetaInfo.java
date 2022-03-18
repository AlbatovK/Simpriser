package com.albatros.simspriser;

public class MetaInfo {

    private final int plane_life;
    private final int shield_life;
    private final int pos_x;
    private final int pos_y;
    private final int plane_x;
    private final int plane_y;

    public MetaInfo(int plane_life, int shield_life, int pos_x, int pos_y, int plane_x, int plane_y) {
        this.plane_life = plane_life;
        this.shield_life = shield_life;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.plane_x = plane_x;
        this.plane_y = plane_y;
    }

    public int getPlane_life() {
        return plane_life;
    }

    public int getPlane_x() {
        return plane_x;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPlane_y() {
        return plane_y;
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getShield_life() {
        return shield_life;
    }
}
