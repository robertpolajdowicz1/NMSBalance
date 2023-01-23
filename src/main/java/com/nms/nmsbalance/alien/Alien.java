package com.nms.nmsbalance.alien;

public class Alien implements IAlien{
    private String alienType;
    private int alienIntType;
    private int alienID;
    private int positionRoomID;
    public Alien(String alienType, int alienIntType) {
        this.alienType = alienType;
        this.alienIntType = alienIntType;
    }

    @Override
    public String getType() {
        return alienType;
    }

    @Override
    public int getIntType() {
        return alienIntType;
    }

    @Override
    public void setId(int id) {
        alienID = id;
    }

    @Override
    public void setPositionRoomID(int id) {
        positionRoomID = id;
    }

}
