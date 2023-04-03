package com.nms.nmsbalance.player;

public class Player {
    private int playerID;
    private int positionRoomID = 11;

    public int getPositionRoomID() {
        return positionRoomID;
    }

    public void setPosition(int roomID){
        this.positionRoomID = roomID;
    }
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
