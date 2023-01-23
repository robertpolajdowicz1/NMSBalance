package com.nms.nmsbalance.spaceship;

import java.util.HashMap;
import java.util.Hashtable;

public class Ship {
    private int damageCounter = 0;
    private int fireCounter = 0;
    private final HashMap<Integer,Room> board = new HashMap<>();
    public void addRoom(int id,Room room){
        board.put(id,room);
    }
    public void setRooms (){

        for (int i = 1; i < 22; i++)
        {
            addRoom(i,new Room(false,false,false,false,false,0,0,i));
        }
        board.get(1).setDescription("Kokpit");
        board.get(11).setDescription("Hibernatorium");
        board.get(19).setDescription("Silnik 1");
        board.get(20).setDescription("Silnik 2");
        board.get(21).setDescription("Silnik 3");
        setConnectionsInRooms();
    }

    private void setConnectionsInRooms(){
        board.get(1).addConnectedRooms(2,3,4);
        board.get(2).addConnectedRooms(1,6);
        board.get(3).addConnectedRooms(1,7);
        board.get(4).addConnectedRooms(1,8);
        board.get(5).addConnectedRooms(6,10);
        board.get(6).addConnectedRooms(2,5,7,11);
        board.get(7).addConnectedRooms(3,6,8);
        board.get(8).addConnectedRooms(4,7,9,11);
        board.get(9).addConnectedRooms(8,12);
        board.get(10).addConnectedRooms(5,13);
        board.get(11).addConnectedRooms(6,8,14,15);
        board.get(12).addConnectedRooms(9,16);
        board.get(13).addConnectedRooms(10,14,19);
        board.get(14).addConnectedRooms(11,13,17);
        board.get(15).addConnectedRooms(11,16,18);
        board.get(16).addConnectedRooms(12,15,21);
        board.get(17).addConnectedRooms(14,19,20);
        board.get(18).addConnectedRooms(15,20,21);
        board.get(19).addConnectedRooms(13,17);
        board.get(20).addConnectedRooms(17,18);
        board.get(21).addConnectedRooms(16,18);
    }
    public void TEST_consolePrinter(){

    }
    public void setDamageStatus(int id){
        board.get(id).setDamageStatus();
        damageCounter++;
    }
    public void setFireStatus(int id){
        board.get(id).setFireStatus();
        fireCounter++;
    }
    public void setNestStatus(int id){
        board.get(id).setNestStatus();
        board.get(id).setDescription("Gniazdo");
    }
    public void setPlayersInsideStatus(int id){
        board.get(id).setPlayerInsideStatus();
    }
    public void setAlienInsideStatus(int id){
        board.get(id).setAlienInsideStatus();
    }
    public void removeDamageStatus(int id) {

        board.get(id).removeDamageStatus();
        damageCounter--;
    }
    public void removeFireStatus(int id) {

        board.get(id).removeFireStatus();
        fireCounter--;
    }
    public void removeNestStatus(int id) {
        board.get(id).removeNestStatus();
    }
    public void removePlayerInsideStatus(int id) {
        board.get(id).removePlayerInsideStatus();
    }
    public void removeAlienInsideStatus(int id) {
        board.get(id).removeAlienInsideStatus();
    }
    public void checkDamageStatus(int id){
        board.get(id).checkDamageStatus();}
    public void checkFireStatus(int id){
        board.get(id).checkFireStatus();
    }
    public void checkNestStatus(int id){
        board.get(id).checkNestStatus();
    }
    public void checkPlayerInsideStatus(int id){
        board.get(id).checkPlayerInsideStatus();
    }
    public void checkAlienInsideStatus(int id){
        board.get(id).checkAlienInsideStatus();
    }
}
