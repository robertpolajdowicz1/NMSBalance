package com.nms.nmsbalance.spaceship;

import com.nms.nmsbalance.alien.Alien;
import com.nms.nmsbalance.player.Player;
import com.nms.nmsbalance.tokenpool.Token;
import java.util.HashMap;
import java.util.List;

public class Ship {
    private int damageCounter = 0;
    private int fireCounter = 0;
    private int alienIDCounter = 1;
    private boolean nestFounded = false;
    private int nestRoomID;
    private HashMap<Integer,Room> board = new HashMap<>();
    private HashMap<Integer, Player> players = new HashMap<>();
    private HashMap<Integer, Alien> aliens = new HashMap<>();
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
    public void setDamageStatus(int id){
        board.get(id).setDamageStatus();
        damageCounter++;
    }
    public void setFireStatus(int id){
        board.get(id).setFireStatus();
        fireCounter++;
    }
    public void setNestStatus(int id){
        nestFounded = true;
        nestRoomID = id;
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
    public void removeAlienInsideStatus(int id){
        board.get(id).removeAlienInsideStatus();
    }

    public boolean checkIfNestFounded(){
        return nestFounded;
    }
    public boolean checkDamageStatus(int id){
        return board.get(id).checkDamageStatus();
    }
    public boolean checkFireStatus(int id){
        return board.get(id).checkFireStatus();
    }
    public boolean checkPlayerInsideStatus(int id){
        return board.get(id).checkPlayerInsideStatus();
    }
    public boolean checkAlienInsideStatus(int id){
        return board.get(id).checkAlienInsideStatus();

    }

    public void addPlayers(int numberOfPlayers){
        for (int i = 1; i <= numberOfPlayers;i++) {
            Player playerToAdd = new Player();
            playerToAdd.setPosition(11);
            playerToAdd.setPlayerID(i);
            players.put(i, playerToAdd);
            board.get(11).addPlayerCounter();
            board.get(11).setPlayerInsideStatus();
        }
    }

    public void changePlayerPosition(int idPlayer,int newIDRoom){
        int oldIDRoom = players.get(idPlayer).getPositionRoomID();
        board.get(oldIDRoom).subPlayerCounter();
        board.get(oldIDRoom).removePlayerInsideStatus();
        players.get(idPlayer).setPosition(newIDRoom);
        board.get(newIDRoom).addPlayerCounter();
        board.get(newIDRoom).setPlayerInsideStatus();
    }

    public int getNestRoomID(){
        return nestRoomID;
    }
    public int getPlayerPositionID(int id){
        return players.get(id).getPositionRoomID();
    }

    public int numberOfPlayers(){
        return players.size();
    }

    public void addAlienToBoard(Token token,int roomID) {
        if (token.getIntType() > 0) {
            aliens.put(alienIDCounter, new Alien(token.getType(), token.getIntType(), roomID));
            alienIDCounter++;
        }
    }
    public  HashMap<Integer,Alien> getAliens()
    {
        return aliens;
    }

    public boolean removeAlien(int alienID)
    {
        if(aliens.containsKey(alienID)){
            int roomID = aliens.get(alienID).getPositionRoomID();
            System.out.println(aliens.size());
            aliens.remove(alienID);
            removeAlienInsideStatus(roomID);

            return true;
        }
        return false;
    }
    public List<Integer> getAliensID(){
        return aliens.keySet().stream().toList();
    }
    public String getDescription(int id){
       return board.get(id).getDescription();
    }

}
