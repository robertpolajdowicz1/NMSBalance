package com.nms.nmsbalance.services;

import com.nms.nmsbalance.spaceship.Ship;
import com.nms.nmsbalance.tokenpool.Pool;
import com.nms.nmsbalance.tokenpool.Token;
import com.nms.nmsbalance.validationLogs.Validation;

public class Services {
    private Pool pool;
    private Ship ship;

    private String invalidID = "Bledne pole ID";

    public Services(Pool pool, Ship ship) {
        this.pool = pool;
        this.ship = ship;
    }

    public String setFire(String roomID) {
        if (Validation.validationRoom(roomID)) {
            int ID = Integer.parseInt(roomID);
            if (!ship.checkFireStatus(ID)) {
                ship.setFireStatus(ID);
                return ("Pozar w pomieszczeniu " + roomID);
            } else {
                return "Ten pokoj jest juz podpalony";
            }
        } else {
            return invalidID;
        }
    }

    public String setDamage(String roomID) {
        if (Validation.validationRoom(roomID)) {
            int ID = Integer.parseInt(roomID);
            if (!ship.checkDamageStatus(ID)) {
                ship.setDamageStatus(ID);
                return ("Uszkodzone pomieszczenie " + roomID);
            } else {
                return "Ten pokoj jest juz uszkodzony";
            }
        } else {
            return invalidID;
        }
    }

    public String removeFire(String roomID) {
        if (Validation.validationRoom(roomID)) {
            int ID = Integer.parseInt(roomID);
            if (ship.checkFireStatus(ID)) {
                ship.removeFireStatus(ID);
                return ("Ugaszono pozar pomieszczeniu " + roomID);
            } else {
                return "Ten pokoj nie jest podpalony";
            }
        } else {
            return invalidID;
        }
    }

    public String removeDamage(String roomID) {
        if (Validation.validationRoom(roomID)) {
            int ID = Integer.parseInt(roomID);
            if (ship.checkDamageStatus(ID)) {
                ship.removeDamageStatus(ID);
                return ("Naprawiono pomieszczenie " + roomID);
            } else {
                return "Ten pokoj nie jest uszkodzony";
            }
        } else {
            return invalidID;
        }
    }

    public String changePlayerPosition(String roomID, String playerID) {
        if (Validation.validationRoom(roomID) && Validation.validationPlayer(playerID, ship.numberOfPlayers())) {
            int playerIDInt = Integer.parseInt(playerID);
            int roomIDInt = Integer.parseInt(roomID);
            ship.changePlayerPosition(playerIDInt, roomIDInt);
            return "Gracz " + playerID + " wszedl do pomieszczenia " + roomID;
        } else {
            return invalidID;
        }
    }

    public String alienEncounter(String playerID) {
        if (Validation.validationPlayer(playerID, ship.numberOfPlayers())) {
            int playerIDInt = Integer.parseInt(playerID);
            int roomIDInt = ship.getPlayerPositionID(playerIDInt);
            Token token = pool.drawToken();
            if (token.getIntType() == 0) {
                return "Pusty token, nic sie nie dzieje";
            } else {
                ship.addAlienToBoard(token, roomIDInt);
                return "Pojawia sie Obcy " + token.getType();
            }
        } else {
            return invalidID;
        }
    }

    public String removeAlien(String alienID) {
        if (Validation.validationAlien(alienID)) {
            if (ship.removeAlien(Integer.parseInt(alienID))) {
                return "Usunieto z planszy Obcy " + alienID;
            } else {
                return "Brak Obcego z takim ID";
            }
        } else {
            return invalidID;
        }
    }

    public String setNest(String roomID){
        if(Validation.validationRoom(roomID)){
            ship.setNestStatus(Integer.parseInt(roomID));
            return "Gniazdo odnalezione w pomieszczeniu " + roomID;
        }else{
            return invalidID;
        }
    }

    public String pickToken() {
        int tokenTierID;
        if (ship.checkIfNestFounded()) {
            tokenTierID = pool.pickTokenWhenNestFounded(ship.checkIfNestFounded(), ship.checkAlienInsideStatus(ship.getNestRoomID()), ship.checkPlayerInsideStatus(ship.getNestRoomID()));
        } else {
            tokenTierID = pool.pickToken();
        }
        if (tokenTierID == 0) {
            return "Wylosowano Pusty, do puli dodano Dorosly";
        }
        if (tokenTierID == 1) {
            return "Wylosowano Larwa, do puli dodano Dorosly";
        }
        if (tokenTierID == 2) {
            return "Wylosowano Pelzacz, do puli dodano Reproduktor";
        }
        if (tokenTierID == 3) {
            return "Wylosowano Dorosly, rzucacie na szmery";
        }
        if (tokenTierID == 4) {
            return "Wylosowano Reproduktor, rzucacie na szmery";
        }
        if (tokenTierID == 5) {

            Token queen = new Token("Krolowa", 5);
            ship.addAlienToBoard(queen, ship.getNestRoomID());
            return "Krolowa pojawia sie w gniezdzie";
        }
        if (tokenTierID == 6) {
            return "Wylosowano Krolowa ale nikogo nie ma w gniezdzie";
        }
        return "";
    }

    public String addToken(String tokenID) {
        if (Validation.validationToken(tokenID)) {
            pool.addToken(Integer.parseInt(tokenID));
            return "Dodano token " + tokenToString(Integer.parseInt(tokenID));
        } else {
            return invalidID;
        }
    }

    public void setGame(String numberOfPlayers){
        pool.setTokenPool(Integer.parseInt(numberOfPlayers));
        ship.setRooms();
        ship.addPlayers(Integer.parseInt(numberOfPlayers));
    }
    public double calculateBar(int id) {
        return pool.calculateBar(id);
    }

    private String tokenToString(int tokenID) {
        if (tokenID == 1) {
            return "Larwa";
        }
        if (tokenID == 2) {
            return "Pelzacz";
        }
        if (tokenID == 3) {
            return "Dorosly";
        }
        if (tokenID == 4) {
            return "Reproduktor";
        }
        if (tokenID == 5) {
            return "Krolowa";
        } else {
            return "";
        }
    }

    public void alienMove(){
        ship.alienMove();
    }
    public Pool getPool() {
        return pool;
    }

    public Ship getShip() {
        return ship;
    }
}








