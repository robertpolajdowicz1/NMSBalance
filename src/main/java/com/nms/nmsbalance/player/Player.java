package com.nms.nmsbalance.player;

public class Player {
    private int playerID;
    private int positionRoomID = 11;
    private int seriousWoundCounter;
    public void changePosition(int roomID){
        this.positionRoomID = roomID;
    }
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
