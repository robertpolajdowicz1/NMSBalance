package com.nms.nmsbalance.spaceship;

import java.util.ArrayList;

public class Room {
    private final int roomID;
    private final ArrayList<Integer> connectedRoomsList = new ArrayList<>();
    private boolean damageStatus;
    private boolean fireStatus;
    private boolean nestStatus;
    private boolean playersInside;
    private boolean alienInside;
    private int playerCounter;
    private int alienCounter;
    private int roomValue;
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

    public boolean checkDamageStatus() {
        return this.damageStatus;
    }

    public boolean checkFireStatus() {
        return this.fireStatus;
    }

    public boolean checkNestStatus() {
        return this.nestStatus;
    }

    public boolean checkPlayerInsideStatus() {
        return this.playersInside;
    }

    public boolean checkAlienInsideStatus() {
        return this.alienInside;
    }

    public void setDamageStatus() {
        this.damageStatus = true;
    }

    public void setFireStatus() {
        this.fireStatus = true;
    }

    public void setNestStatus() {
        this.nestStatus = true;
    }

    public void removeDamageStatus() {
        this.damageStatus = false;
    }

    public void removeFireStatus() {
        this.fireStatus = false;
    }

    public void removePlayerInsideStatus() {
        if (playerCounter == 0) {
            this.playersInside = false;
        }
    }

    public void setPlayerInsideStatus() {
        if (playerCounter > 0) {
            this.playersInside = true;
        }
    }

    public void removeAlienInsideStatus() {
        if (alienCounter == 0) {
            this.alienInside = false;
        }
    }

    public void setAlienInsideStatus() {
        if (alienCounter > 0) {
            this.alienInside = true;
        }
    }

    public void addPlayerCounter() {
        if (playerCounter < 5) {
            playerCounter++;
        }
    }

    public void subPlayerCounter() {
        if (playerCounter >= 1) {
            playerCounter--;
        }
    }

    public void addAlienCounter() {
        alienCounter++;
    }

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
        if (checkPlayerInsideStatus()) {
            value += 0;
        } else {
            value += 5;
            if (checkFireStatus()) {
                value += 20;
            } else {
                value += 5;
                if (checkAlienInsideStatus()) {
                    value += 5;
                } else {
                    value += 10;
                }
            }
        }
        return value;
    }

    private int normalLevel() {
        int value = 0;
        if (checkPlayerInsideStatus()) {
            value += 10;
        } else {
            value += 0;
            if (checkFireStatus()) {
                value += 25;
            } else {
                value += 5;
                if (checkAlienInsideStatus() && alienCounter <= 1) {
                    value += 5;
                    if (checkDamageStatus()) {
                        value += 5;
                    } else {
                        value += 10;
                    }
                } else {
                    value += 10;
                    if (checkDamageStatus()) {
                        value += 5;
                    } else {
                        value += 10;
                    }
                }
            }
        }
        return value;
    }

    private int hardLevel() {
        int value = 0;
        if (checkPlayerInsideStatus()) {
            value += 30 * playerCounter;
            if (checkFireStatus()) {
                value += 5;
            } else {
                value += 10;
            }
        } else {
            value += 5;
            if (checkFireStatus()) {
                value += 5;
            } else {
                value += 10;
                if (checkAlienInsideStatus()) {
                    value += 5;
                    if (checkDamageStatus()) {
                        value += 10;
                    } else {
                        value += 5;
                    }
                } else {
                    value += 10;
                    if (checkDamageStatus()) {
                        value += 10;
                    } else {
                        value += 5;
                    }
                }
            }
        }
        return value;
    }

    private int heroicLevel() {
        int value = 0;
        if (checkPlayerInsideStatus()) {
            value += 40 * playerCounter;
            if (checkFireStatus()) {
                value += 5;
            } else {
                value += 10;
            }
        } else {
            value += 0;
            if (checkFireStatus()) {
                value += 5;
                if (checkNestStatus()) {
                    value += 10;
                } else {
                    value += 5;
                    if (checkDamageStatus()) {
                        value += 10;
                    } else {
                        value += 5;
                    }
                }
            } else {
                value += 10;
                if (checkAlienInsideStatus()) {
                    value += 5;
                    if (checkDamageStatus()) {
                        value += 10;
                    } else {
                        value += 5;
                    }
                } else {
                    value += 5;
                    if (checkNestStatus()) {
                        value += 20;
                    } else {
                        value += 5;
                        if (checkDamageStatus()) {
                            value += 10;
                        } else {
                            value += 5;
                        }
                    }
                }
            }
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

    public int getPlayerCounter() {
        return playerCounter;
    }

    public int getRoomValue() {
        return roomValue;
    }


}
