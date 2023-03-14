package com.nms.nmsbalance.spaceship;

public interface IRoom {
    boolean checkDamageStatus();
    boolean checkFireStatus();
    boolean checkNestStatus();
    boolean checkPlayerInsideStatus();
    boolean checkAlienInsideStatus();
    void setDamageStatus();
    void setFireStatus();
    void setNestStatus();
    void setPlayerInsideStatus();
    void setAlienInsideStatus();
    void removeDamageStatus();
    void removeFireStatus();
    void removePlayerInsideStatus();
    void removeAlienInsideStatus();
    void addPlayerCounter();
    void addAlienCounter();
    void subPlayerCounter();
    void subAlienCounter();


}
