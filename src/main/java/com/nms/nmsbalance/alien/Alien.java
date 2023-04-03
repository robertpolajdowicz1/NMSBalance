package com.nms.nmsbalance.alien;

public class Alien {
    private final String alienType;
    private final int alienIntType;
    private int positionRoomID;
    public Alien(String alienType, int alienIntType,int positionRoomID ) {
        this.alienType = alienType;
        this.alienIntType = alienIntType;
        this.positionRoomID = positionRoomID;
    }

    public String getType() {
        return alienType;
    }

    public void setPositionRoomID(int id) {
        positionRoomID = id;
    }

    public int getPositionRoomID() {
        return positionRoomID;
    }
}
