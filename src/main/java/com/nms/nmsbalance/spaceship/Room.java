package com.nms.nmsbalance.spaceship;

import com.nms.nmsbalance.alien.Alien;

import java.util.ArrayList;

public class Room implements IRoom {
    private boolean damageStatus;
    private boolean fireStatus;
    private boolean nestStatus;
    private boolean playersInside;
    private boolean alienInside;
    private int playerCounter;
    private int alienCounter;
    private int roomValue;
    private int roomID;
    private ArrayList<Integer> connectedRoomsList = new ArrayList<>();
    private ArrayList<Alien> aliensInRoom = new ArrayList<>();
    private String description = "Pomieszczenie";

    public Room(boolean damageStatus, boolean fireStatus, boolean nestStatus, boolean playersInside, boolean alienInside
            , int numberOfPlayers, int numberOfAliens, int roomID) {
        this.damageStatus = damageStatus;
        this.fireStatus = fireStatus;
        this.nestStatus = nestStatus;
        this.playersInside = playersInside;
        this.alienInside = alienInside;
        this.playerCounter = numberOfPlayers;
        this.alienCounter = numberOfAliens;
        this.roomID = roomID;
    }

    public void addConnectedRooms(int... roomsToConnectID) {
        for (int roomToConnectID : roomsToConnectID) {
            connectedRoomsList.add(roomToConnectID);
        }
    }

    public ArrayList<Integer> getConnectedRoomsList() {
        return connectedRoomsList;
    }

    @Override
    public boolean checkDamageStatus() {
        return this.damageStatus;
    }

    @Override
    public boolean checkFireStatus() {
        return this.fireStatus;
    }

    @Override
    public boolean checkNestStatus() {
        return this.nestStatus;
    }

    @Override
    public boolean checkPlayerInsideStatus() {
        return this.playersInside;
    }

    @Override
    public boolean checkAlienInsideStatus() {
        return this.alienInside;
    }

    @Override
    public void setDamageStatus() {
        this.damageStatus = true;
    }

    @Override
    public void setFireStatus() {
        this.fireStatus = true;
    }

    @Override
    public void setNestStatus() {
        this.nestStatus = true;
    }

    @Override
    public void removeDamageStatus() {
        this.damageStatus = false;
    }

    @Override
    public void removeFireStatus() {
        this.fireStatus = false;
    }

    @Override
    public void removePlayerInsideStatus() {
        if (playerCounter == 0) {
            this.playersInside = false;
        }
    }

    @Override
    public void setPlayerInsideStatus() {
        if (playerCounter > 0) {
            this.playersInside = true;
        }
    }

    @Override
    public void removeAlienInsideStatus() {
        if (alienCounter == 0) {
            this.alienInside = false;
        }
    }

    @Override
    public void setAlienInsideStatus() {
        if (alienCounter > 0) {
            this.alienInside = true;
        }
    }

    @Override
    public void addPlayerCounter() {
        if (playerCounter < 5) {
            playerCounter++;
        }
    }

    @Override
    public void subPlayerCounter() {
        if (playerCounter >= 1) {
            playerCounter--;
        }
    }

    @Override
    public void addAlienCounter() {
        alienCounter++;
    }

    @Override
    public void subAlienCounter() {
        if (alienCounter >= 1) {
            alienCounter--;
        }
    }

    public void calculateRoomValue(int difficulty) {

        switch (difficulty) {
            case 1 -> this.roomValue = easyLevel();
            case 2 -> this.roomValue = normalLevel();
            case 3 -> this.roomValue = hardLevel();
            case 4 -> this.roomValue = heroicLevel();
            default -> this.roomValue = easyLevel();
        }
    }

    private int easyLevel() {
        int value = 0;
        if (damageStatus) {
            value -= 10;
        }

        if (fireStatus) {
            value -= 30;
        }

        if (nestStatus) {
            value += 10;
        }

        if (playersInside) {
            value += playerCounter * 20;
        }

        if (alienInside) {
            value += alienCounter * 15;
        }

        return value;
    }

    private int normalLevel() {
        int value = 0;
        if (damageStatus) {
            value -= 5;
        }

        if (fireStatus) {
            value -= 10;
        }

        if (nestStatus) {
            value += 5;
        }

        if (playersInside) {
            value += playerCounter * 5;
        }

        if (alienInside) {
            value += alienCounter * 5;
        }

        return value;
    }

    private int hardLevel() {
        int value = 0;
        if (damageStatus) {
            value -= 5;
        }

        if (fireStatus) {
            value += 10;
        }

        if (nestStatus) {
            value -= 5;
        }

        if (playersInside) {
            value -= playerCounter * 5;
        }

        if (alienInside) {
            value -= alienCounter * 5;
        }

        return value;
    }

    private int heroicLevel() {
        int value = 0;
        if (damageStatus) {
            value += 5;
        }

        if (fireStatus) {
            value += 30;
        }

        if (nestStatus) {
            value -= 5;
        }

        if (playersInside) {
            value -= playerCounter * 15;
        }

        if (alienInside) {
            value -= alienCounter * 5;
        }

        return value;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAlienCounter() {
        return alienCounter;
    }

    public int getRoomValue() {
        return roomValue;
    }

}
