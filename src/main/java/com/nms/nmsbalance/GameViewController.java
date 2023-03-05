package com.nms.nmsbalance;

import com.nms.nmsbalance.alien.Alien;
import com.nms.nmsbalance.spaceship.Ship;
import com.nms.nmsbalance.tokenpool.Pool;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.List;


public class GameViewController {
    private final Ship ship = new Ship();
    private Pool pool = new Pool();

    @FXML
    private Button setGameButton, removeAlienButton, playerPositionButton,
            alienMoveButton, pickCardButton, pickTokenButton, setFireButton,
            setDamageButton, removeFireButton, removeDamageButton, alienEncounterButton;
    @FXML
    private ChoiceBox<String> numberOfPlayers;

    @FXML
    private Label startHint, setStatusHint, removeStatusHint, alienEncounterHint, idL1, idL2, idL3, idL4, idL5, ruleHint, eventLabel,
            tokensLabel, larvaL, creeperL, adultL, breederL, queenL, historyL, aliensL, playersL, roomsL;
    @FXML
    private ProgressBar larvaBar, creeperBar, adultBar, breederBar, queenBar;
    @FXML
    private TextField idRoomToRemoveFireOrDamage, idRoomToSetFireOrDamage, idAlienToRemove, idRoomPlayerPositionChange,
            idPlayerPlayerPositionChange, idPlayerAlienEncounter;
    @FXML
    private ListView<String> eventListView, aliensListView, playersListView, roomsListView;


    @FXML
    private void onSetFireButtonClick() {
        if (!idRoomToSetFireOrDamage.getText().isEmpty() && checkRoomID(idRoomToSetFireOrDamage.getText())) {
            if (!ship.checkFireStatus(Integer.parseInt(idRoomToSetFireOrDamage.getText()))) {
                ship.setFireStatus(Integer.parseInt(idRoomToSetFireOrDamage.getText()));
                addEventLog("Pozar w pomieszczeniu " + idRoomToSetFireOrDamage.getText());
                idRoomToSetFireOrDamage.clear();
                roomsToListView();
            }
        }
    }

    @FXML
    private void onSetDamageButtonClick() {
        if (!idRoomToSetFireOrDamage.getText().isEmpty() && checkRoomID(idRoomToSetFireOrDamage.getText())) {
            if (!ship.checkDamageStatus(Integer.parseInt(idRoomToSetFireOrDamage.getText()))) {
                ship.setDamageStatus(Integer.parseInt(idRoomToSetFireOrDamage.getText()));
                addEventLog("Uszkodzone pomieszczenie " + idRoomToSetFireOrDamage.getText());
                idRoomToSetFireOrDamage.clear();
                roomsToListView();
            }
        }
    }

    @FXML
    private void onRemoveFireButtonClick() {
        if (!idRoomToRemoveFireOrDamage.getText().isEmpty() && checkRoomID(idRoomToRemoveFireOrDamage.getText())) {
            if (ship.checkFireStatus(Integer.parseInt(idRoomToRemoveFireOrDamage.getText()))) {
                ship.removeFireStatus(Integer.parseInt(idRoomToRemoveFireOrDamage.getText()));
                addEventLog("Ugaszono pozar w pomieszczeniu " + idRoomToRemoveFireOrDamage.getText());
                idRoomToRemoveFireOrDamage.clear();
                roomsToListView();
            }
        }
    }

    @FXML
    private void onRemoveDamageButtonClick() {
        if (!idRoomToRemoveFireOrDamage.getText().isEmpty() && checkRoomID(idRoomToRemoveFireOrDamage.getText())) {
            if (ship.checkDamageStatus(Integer.parseInt(idRoomToRemoveFireOrDamage.getText()))) {
                ship.removeDamageStatus(Integer.parseInt(idRoomToRemoveFireOrDamage.getText()));
                addEventLog("Naprawiono pomieszczenie " + idRoomToRemoveFireOrDamage.getText());
                idRoomToRemoveFireOrDamage.clear();
                roomsToListView();
            }
        }
    }

    @FXML
    private void onPickCardButtonClick() {

    }

    @FXML
    private void onPickTokenButtonClick() {

    }

    @FXML
    private void onAlienMoveButtonClick() {

    }

    @FXML
    private void onPlayerPositionButtonClick() {
        if (checkPlayerAndRoomID()) {
            int playerID = Integer.parseInt(idPlayerPlayerPositionChange.getText());
            int roomID = Integer.parseInt(idRoomPlayerPositionChange.getText());
            ship.changePlayerPosition(playerID, roomID);
            addEventLog("Gracz " + playerID + " wszedl do pomieszczenia " + roomID);
            idRoomPlayerPositionChange.clear();
            idPlayerPlayerPositionChange.clear();
            playerToListView();
            roomsToListView();
        }
    }

    @FXML
    private void onRemoveAlienButtonClick() {

    }

    public void onAlienEncounterButtonClick() {
        if (checkPlayerID()) {
            int playerID = Integer.parseInt(idPlayerAlienEncounter.getText());
            int roomID = ship.getPlayerPositionID(playerID);
            if (pool.getNumberOfTokens() > 0) {
                ship.addAlienToBoard(pool.drawToken(), roomID);
                System.out.println(ship.getAliens().size());
            }
            aliensToListView();
            roomsToListView();
            idPlayerAlienEncounter.clear();
            setBars();
        }
    }

    private boolean checkPlayerAndRoomID() {
        if (!idPlayerPlayerPositionChange.getText().isEmpty() &&
                !idRoomPlayerPositionChange.getText().isEmpty() &&
                Integer.parseInt(idPlayerPlayerPositionChange.getText()) < ship.numberOfPlayers() + 1 &&
                Integer.parseInt(idPlayerPlayerPositionChange.getText()) > 0 &&
                Integer.parseInt(idRoomPlayerPositionChange.getText()) > 0 &&
                Integer.parseInt(idRoomPlayerPositionChange.getText()) < 22) {
            return true;
        }
        return false;
    }

    private void playerToListView() {
        playersListView.getItems().clear();
        for (int i = 1; i < ship.numberOfPlayers() + 1; i++) {
            playersListView.getItems().add("Gracz " + i + " pomieszczenie nr " + ship.getPlayerPositionID(i));
            playersListView.refresh();
        }
    }

    private void roomsToListView() {
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
            if (ship.checkAlienInsideStatus(i)){
                info.append(" OBCY");
            }
            if (ship.checkPlayerInsideStatus(i))
            {
                info.append(" GRACZ");
            }
            roomsListView.getItems().add(info.toString());
            info = new StringBuilder();
        }
        roomsListView.refresh();
    }

    private void aliensToListView() {
        aliensListView.getItems().clear();
        int i = 0;
        HashMap<Integer,Alien>  aliensToAdd = ship.getAliens();
        List<Integer> aliensIDs= ship.getAliensID();
        StringBuilder info = new StringBuilder();
        for (Alien a : aliensToAdd.values()) {
            info.append(a.getType()).append(" ").append(aliensIDs.get(i)).append(" w pomieszczeniu ").append(a.getPositionRoomID());
            i++;
            aliensListView.getItems().add(info.toString());
            info = new StringBuilder();
            addEventLog("Pojawia sie obcy w pomieszczeniu " + a.getPositionRoomID());
            ship.setAlienInsideStatus(a.getPositionRoomID());
        }
        aliensListView.refresh();
    }

    private void setEnableAll() {
        removeAlienButton.setDisable(false);
        playerPositionButton.setDisable(false);
        alienMoveButton.setDisable(false);
        pickCardButton.setDisable(false);
        pickTokenButton.setDisable(false);
        setFireButton.setDisable(false);
        setDamageButton.setDisable(false);
        removeFireButton.setDisable(false);
        removeDamageButton.setDisable(false);
        alienEncounterButton.setDisable(false);
        idRoomToSetFireOrDamage.setDisable(false);
        idRoomToRemoveFireOrDamage.setDisable(false);
        idAlienToRemove.setDisable(false);
        idRoomPlayerPositionChange.setDisable(false);
        idPlayerPlayerPositionChange.setDisable(false);
        idPlayerAlienEncounter.setDisable(false);
        idL1.setDisable(false);
        idL2.setDisable(false);
        idL3.setDisable(false);
        idL4.setDisable(false);
        idL5.setDisable(false);
        eventListView.setDisable(false);
        playersListView.setDisable(false);
        aliensListView.setDisable(false);
        roomsListView.setDisable(false);
        alienEncounterHint.setDisable(false);
        removeStatusHint.setDisable(false);
        setStatusHint.setDisable(false);
        ruleHint.setDisable(false);
        larvaBar.setDisable(false);
        creeperBar.setDisable(false);
        adultBar.setDisable(false);
        breederBar.setDisable(false);
        queenBar.setDisable(false);
        tokensLabel.setDisable(false);
        eventLabel.setDisable(false);
        larvaL.setDisable(false);
        creeperL.setDisable(false);
        adultL.setDisable(false);
        breederL.setDisable(false);
        queenL.setDisable(false);
        roomsL.setDisable(false);
        aliensL.setDisable(false);
        historyL.setDisable(false);
        playersL.setDisable(false);
    }

    private boolean checkPlayerID() {
        if (!idPlayerAlienEncounter.getText().isEmpty() && Integer.parseInt(idPlayerAlienEncounter.getText()) < ship.numberOfPlayers() + 1) {
            return true;
        }
        return false;
    }

    private boolean checkRoomID(String id) {
        if (Integer.parseInt(id) < 22 && Integer.parseInt(id) > 0) {
            return true;
        }
        return false;
    }

    private void addEventLog(String log) {
        eventListView.getItems().add(0, log);
        eventListView.refresh();
    }

    private void setBars() {
        larvaBar.setProgress(pool.calculateBar(1));
        creeperBar.setProgress(pool.calculateBar(2));
        adultBar.setProgress(pool.calculateBar(3));
        breederBar.setProgress(pool.calculateBar(4));
        queenBar.setProgress(pool.calculateBar(5));
        setBarColor(larvaBar);
        setBarColor(creeperBar);
        setBarColor(adultBar);
        setBarColor(breederBar);
        setBarColor(queenBar);
    }

    private void setBarColor(ProgressBar bar) {
        if (bar.getProgress() > 0.5) {
            bar.setStyle("-fx-accent: red;");
        } else if (bar.getProgress() <= 0.5 && bar.getProgress() > 0.25) {
            bar.setStyle("-fx-accent: orange;");
        } else {
            bar.setStyle("-fx-accent: green;");
        }
    }

    @FXML
    private void onSetGameButtonClick() {
        pool.setTokenPool(Integer.valueOf(numberOfPlayers.getValue()));
        ship.setRooms();
        ship.addPlayers(Integer.valueOf(numberOfPlayers.getValue()));
        ship.setPlayersInsideStatus(11);
        setBars();
        numberOfPlayers.setDisable(true);
        setGameButton.setDisable(true);
        setEnableAll();
        startHint.setVisible(false);
        numberOfPlayers.setVisible(false);
        setGameButton.setVisible(false);
        playerToListView();
        roomsToListView();
    }
}
