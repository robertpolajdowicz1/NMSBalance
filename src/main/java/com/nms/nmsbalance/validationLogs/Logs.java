package com.nms.nmsbalance.validationLogs;

import com.nms.nmsbalance.alien.Alien;
import com.nms.nmsbalance.spaceship.Ship;
import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.List;

public class Logs {

    public static void addPlayerLog(ListView<String> playersListView, Ship ship) {
        playersListView.getItems().clear();
        for (int i = 1; i < ship.numberOfPlayers() + 1; i++) {
            playersListView.getItems().add("Gracz " + i + " pomieszczenie nr " + ship.getPlayerPositionID(i));
            playersListView.refresh();
        }
    }

    public static void addEventLog(ListView<String> eventListView, String log) {
        eventListView.getItems().add(0, log);
        eventListView.refresh();
    }

    public static void addRoomLog(ListView<String> roomsListView, Ship ship) {
        roomsListView.getItems().clear();
        StringBuilder info = new StringBuilder();
        for (int i = 1; i < 22; i++) {
            info.append(ship.getDescription(i));
            info.append(" ");
            info.append(i);
            if (ship.checkFireStatus(i)) {
                info.append(" PODPALONE");
            }
            if (ship.checkDamageStatus(i)) {
                info.append(" USZKODZONE");
            }
            System.out.println(ship.getAlienCounter(i));
            if (ship.checkAlienInsideStatus(i)) {
                info.append(" OBCY");
            }
            if (ship.checkPlayerInsideStatus(i)) {
                info.append(" GRACZ");
            }
            roomsListView.getItems().add(info.toString());
            info = new StringBuilder();
        }
        roomsListView.refresh();
    }

    public static void addAlienLog(ListView<String> aliensListView, Ship ship) {
        aliensListView.getItems().clear();
        int i = 0;
        HashMap<Integer, Alien> aliensToAdd = ship.getAliens();
        List<Integer> aliensIDs = ship.getAliensID();
        StringBuilder info = new StringBuilder();
        for (Alien a : aliensToAdd.values()) {
            info.append(a.getType()).append(" ").append(aliensIDs.get(i)).append(" w pomieszczeniu ").append(a.getPositionRoomID());
            i++;
            aliensListView.getItems().add(0, info.toString());
            info = new StringBuilder();

            ship.setAlienInsideStatus(a.getPositionRoomID());
        }
        aliensListView.refresh();
    }
}
