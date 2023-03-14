package com.nms.nmsbalance;

import com.nms.nmsbalance.alien.Alien;
import com.nms.nmsbalance.spaceship.Ship;
import com.nms.nmsbalance.tokenpool.Pool;
import com.nms.nmsbalance.tokenpool.Token;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.List;


public class GameViewController {
    private final Ship ship = new Ship();
    private final Pool pool = new Pool();

    @FXML
    private Button setGameButton, removeAlienButton, playerPositionButton, addTokenButton, alienMoveButton, pickCardButton, pickTokenButton, setFireButton, setDamageButton, removeFireButton, removeDamageButton, alienEncounterButton, setNestButton;
    @FXML
    private ChoiceBox<String> numberOfPlayers;

    @FXML
    private Label startHint, setStatusHint, removeStatusHint, alienEncounterHint, removeAlienHint, IDL1, IDL2, IDL3, ruleHint, eventLabel, tokensLabel, larvaL, creeperL, adultL, breederL, queenL, historyL, aliensL, playersL, addTokenHint, roomsL, nestHint;
    @FXML
    private ProgressBar larvaBar, creeperBar, adultBar, breederBar, queenBar;
    @FXML
    private TextField IDRoomToRemoveFireOrDamage, IDRoomToSetFireOrDamage, IDAlienToRemove, IDRoomPlayerPositionChange, IDPlayerPlayerPositionChange, IDPlayerAlienEncounter, IDTokenTier, idNest;
    @FXML
    private ListView<String> eventListView, aliensListView, playersListView, roomsListView;


    @FXML
    private void onPlayerPositionButtonClick() {
        if (checkPlayerAndRoomID()) {
            int playerID = Integer.parseInt(IDPlayerPlayerPositionChange.getText());
            int roomID = Integer.parseInt(IDRoomPlayerPositionChange.getText());
            ship.changePlayerPosition(playerID, roomID);
            addEventLog("Gracz " + playerID + " wszedl do pomieszczenia " + roomID);
            IDRoomPlayerPositionChange.clear();
            IDPlayerPlayerPositionChange.clear();
            addPlayerLog();
            addRoomLog();
        } else {
            addEventLog("Bledne ID lub puste pole");
        }
    }

    @FXML
    private void onAlienMoveButtonClick() {

    }

    @FXML
    private void onPickCardButtonClick() {

    }

    @FXML
    private void onPickTokenButtonClick() {

        int tokenTierID = pool.pickToken(ship.checkIfNestFounded(), ship.checkAlienInsideStatus(ship.getNestRoomID()),ship.checkPlayerInsideStatus(ship.getNestRoomID()));
        if (tokenTierID == 0){
            addEventLog("Wylosowano pusty, do puli dodano Dorosly");
            setBars();
        }
        if (tokenTierID == 1){
            addEventLog("Wylosowano larwe, do puli dodano Dorolosly");
            setBars();
        }
        if (tokenTierID == 2){
            addEventLog("Wylosowano pelzacza, do puli dodano Reproduktora");
            setBars();
        }
        if (tokenTierID == 3){
            addEventLog("Wylosowano dorosly, rzucacie na szmery");
        }
        if (tokenTierID == 4){
            addEventLog("Wylosowano reproduktor, rzucacie na szmery");
        }
        if (tokenTierID == 5){
            addEventLog("Krolowa pojawia sie w gniezdzie");
            Token queen = new Token("Krolowa",5);
            ship.addAlienToBoard(queen,ship.getNestRoomID());
            addAlienLog();
            addRoomLog();
            setBars();
        }
        if (tokenTierID == 6){
            addEventLog("Wylosowano Krolowa ale nikogo nie ma w gniezdzie");
        }

    }

    @FXML
    private void onAddTokenButtonClick() {
        if (!IDTokenTier.getText().isEmpty() && isNumeric(IDTokenTier.getText())) {
            if (validationTokenTierID(IDTokenTier.getText())) {
                pool.addToken(Integer.parseInt(IDTokenTier.getText()));
                addEventLog("Dodano token " + tokenToString(Integer.parseInt(IDTokenTier.getText())));
                IDTokenTier.clear();
                setBars();
            } else {
                addEventLog("Grr! Pomieszczenia maja ID 1-21");
            }
        }
    }

    @FXML
    private void onSetFireButtonClick() {
        if (!IDRoomToSetFireOrDamage.getText().isEmpty() && validationRoomID(IDRoomToSetFireOrDamage.getText())) {
            if (!ship.checkFireStatus(Integer.parseInt(IDRoomToSetFireOrDamage.getText()))) {
                ship.setFireStatus(Integer.parseInt(IDRoomToSetFireOrDamage.getText()));
                addEventLog("Pozar w pomieszczeniu " + IDRoomToSetFireOrDamage.getText());
                IDRoomToSetFireOrDamage.clear();
                addRoomLog();
            } else {
                addEventLog("Ten pokoj jest juz podpalony");
            }
        } else {
            addEventLog("Grr! Pomieszczenia maja ID 1-21");
        }
    }

    @FXML
    private void onSetDamageButtonClick() {
        if (!IDRoomToSetFireOrDamage.getText().isEmpty() && validationRoomID(IDRoomToSetFireOrDamage.getText())) {
            if (!ship.checkDamageStatus(Integer.parseInt(IDRoomToSetFireOrDamage.getText()))) {
                ship.setDamageStatus(Integer.parseInt(IDRoomToSetFireOrDamage.getText()));
                addEventLog("Uszkodzone pomieszczenie " + IDRoomToSetFireOrDamage.getText());
                IDRoomToSetFireOrDamage.clear();
                addRoomLog();
            } else {
                addEventLog("Ten pokoj jest juz uszkodzony");
            }
        } else {
            addEventLog("Grr! Pomieszczenia maja ID 1-21");
        }
    }

    @FXML
    private void onRemoveFireButtonClick() {
        if (!IDRoomToRemoveFireOrDamage.getText().isEmpty() && validationRoomID(IDRoomToRemoveFireOrDamage.getText())) {
            if (ship.checkFireStatus(Integer.parseInt(IDRoomToRemoveFireOrDamage.getText()))) {
                ship.removeFireStatus(Integer.parseInt(IDRoomToRemoveFireOrDamage.getText()));
                addEventLog("Ugaszono pozar w pomieszczeniu " + IDRoomToRemoveFireOrDamage.getText());
                IDRoomToRemoveFireOrDamage.clear();
                addRoomLog();
            } else {
                addEventLog("Ten pokoj nie jest podpalony");
            }
        } else {
            addEventLog("Grr! Pomieszczenia maja ID 1-21");
        }
    }

    @FXML
    private void onRemoveDamageButtonClick() {
        if (!IDRoomToRemoveFireOrDamage.getText().isEmpty() && validationRoomID(IDRoomToRemoveFireOrDamage.getText())) {
            if (ship.checkDamageStatus(Integer.parseInt(IDRoomToRemoveFireOrDamage.getText()))) {
                ship.removeDamageStatus(Integer.parseInt(IDRoomToRemoveFireOrDamage.getText()));
                addEventLog("Naprawiono pomieszczenie " + IDRoomToRemoveFireOrDamage.getText());
                IDRoomToRemoveFireOrDamage.clear();
                addRoomLog();
            } else {
                addEventLog("Ten pokoj nie jest uszkodzony");
            }
        } else {
            addEventLog("Grr! ID pomieszczenie 1-21");
        }
    }

    @FXML
    public void onAlienEncounterButtonClick() {
        if (validationPlayerID() && isNumeric(IDPlayerAlienEncounter.getText())) {
            int playerID = Integer.parseInt(IDPlayerAlienEncounter.getText());
            int roomID = ship.getPlayerPositionID(playerID);
            if (pool.getNumberOfTokens() > 0) {
                Token token = pool.drawToken();
                if (token.getIntType() == 0) {
                    addEventLog("Pusty token, nic sie nie dzieje");
                } else {
                    addEventLog("Pojawia sie obcy " + token.getType());
                    ship.addAlienToBoard(token, roomID);
                    addAlienLog();
                    addRoomLog();
                }
            }
            IDPlayerAlienEncounter.clear();
            setBars();
        } else {
            addEventLog("Brak lub bledne ID gracza");
        }
    }

    @FXML
    private void onRemoveAlienButtonClick() {

        if (!IDAlienToRemove.getText().isEmpty() && validationAlienID(IDAlienToRemove.getText())) {
            if (ship.removeAlien(Integer.parseInt(IDAlienToRemove.getText()))) {
                addEventLog("Usunieto z planszy Obcy " + IDAlienToRemove.getText());
                IDAlienToRemove.clear();
                addAlienLog();
                addRoomLog();
            } else {
                addEventLog("Bledne ID Obcego");
            }
        } else {
            addEventLog("Brak ID Obcego");
        }
    }

    @FXML
    private void onSetNestButtonClick() {
        if (!idNest.getText().isEmpty() && validationRoomID(idNest.getText())) {
            ship.setNestStatus(Integer.parseInt(idNest.getText()));
            idNest.setVisible(false);
            nestHint.setVisible(false);
            setNestButton.setVisible(false);
            addRoomLog();
            addEventLog("Gniazdo odnalezione w pomieszczeniu " + idNest.getText());
            idNest.clear();
        }
    }

    @FXML
    private void onSetGameButtonClick() {
        pool.setTokenPool(Integer.parseInt(numberOfPlayers.getValue()));
        ship.setRooms();
        ship.addPlayers(Integer.parseInt(numberOfPlayers.getValue()));
        ship.setPlayersInsideStatus(11);
        setBars();
        enableGUI();
        startHint.setVisible(false);
        numberOfPlayers.setVisible(false);
        setGameButton.setVisible(false);
        addPlayerLog();
        addRoomLog();
    }

    private void addPlayerLog() {
        playersListView.getItems().clear();
        for (int i = 1; i < ship.numberOfPlayers() + 1; i++) {
            playersListView.getItems().add("Gracz " + i + " pomieszczenie nr " + ship.getPlayerPositionID(i));
            playersListView.refresh();
        }
    }

    private void addRoomLog() {
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

    private void addAlienLog() {
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

    private void addEventLog(String log) {
        eventListView.getItems().add(0, log);
        eventListView.refresh();
    }

    private boolean validationAlienID(String id) {
        return Integer.parseInt(id) < 100 && Integer.parseInt(id) > 0;
    }

    private boolean validationPlayerID() {
        return !IDPlayerAlienEncounter.getText().isEmpty() && Integer.parseInt(IDPlayerAlienEncounter.getText()) < ship.numberOfPlayers() + 1;
    }

    private boolean validationRoomID(String id) {
        if (isNumeric(id)) {
            return Integer.parseInt(id) < 22 && Integer.parseInt(id) > 0;
        }
        return false;
    }

    private boolean checkPlayerAndRoomID() {
        if (isNumeric(IDPlayerPlayerPositionChange.getText()) && isNumeric(IDRoomPlayerPositionChange.getText())) {
            return !IDPlayerPlayerPositionChange.getText().isEmpty() && !IDRoomPlayerPositionChange.getText().isEmpty() && Integer.parseInt(IDPlayerPlayerPositionChange.getText()) < ship.numberOfPlayers() + 1 && Integer.parseInt(IDPlayerPlayerPositionChange.getText()) > 0 && Integer.parseInt(IDRoomPlayerPositionChange.getText()) > 0 && Integer.parseInt(IDRoomPlayerPositionChange.getText()) < 22;
        }
        return false;
    }

    private boolean validationTokenTierID(String tierID) {
        if (isNumeric(tierID)) {
            if (Integer.parseInt(tierID) > 0 && Integer.parseInt(tierID) < 6) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumeric(String ID) {
        try {
            Integer.parseInt(ID);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

    private void enableGUI() {
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
        addTokenButton.setDisable(false);
        setNestButton.setDisable(false);
        IDRoomToSetFireOrDamage.setDisable(false);
        IDRoomToRemoveFireOrDamage.setDisable(false);
        IDAlienToRemove.setDisable(false);
        IDRoomPlayerPositionChange.setDisable(false);
        IDPlayerPlayerPositionChange.setDisable(false);
        IDPlayerAlienEncounter.setDisable(false);
        IDTokenTier.setDisable(false);
        IDL1.setDisable(false);
        IDL2.setDisable(false);
        IDL3.setDisable(false);
        idNest.setDisable(false);
        eventListView.setDisable(false);
        playersListView.setDisable(false);
        aliensListView.setDisable(false);
        roomsListView.setDisable(false);
        alienEncounterHint.setDisable(false);
        removeStatusHint.setDisable(false);
        setStatusHint.setDisable(false);
        ruleHint.setDisable(false);
        removeAlienHint.setDisable(false);
        addTokenHint.setDisable(false);
        nestHint.setDisable(false);
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

}
