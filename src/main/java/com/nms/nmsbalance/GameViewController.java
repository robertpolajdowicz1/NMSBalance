package com.nms.nmsbalance;

import com.nms.nmsbalance.spaceship.Ship;
import com.nms.nmsbalance.tokenpool.Draw;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class GameViewController {
    private final Ship ship = new Ship();
    private final Draw draw = new Draw();


    @FXML
    private Button setGameButton,removeAlienButton,playerPositionButton,
            alienMoveButton,pickCardButton,pickTokenButton,setFireButton,
            setDamageButton,removeFireButton,removeDamageButton;
    @FXML
    private ChoiceBox<String> numberOfPlayers;

    @FXML
    private Label startHint,statusHint,removeStatusHint,idL1,idL2,idL3,idL4,ruleHint,tokensLabel,larvaL,creeperL,adultL,breederL,queenL;
    @FXML
    private ProgressBar larvaBar,creeperBar,adultBar,breederBar,queenBar;
    @FXML
    private TextField idRoomToRemoveFireOrDamage,idRoomToSetFireOrDamage,idAlienToRemove,idRoomPlayerPositionChange,idPlayerPlayerPositionChange;
    @FXML
    private ListView<String> listViewEvents;
    @FXML
    private void onSetGameButtonsClick(){
        draw.setPool( Integer.valueOf(numberOfPlayers.getValue()));
        numberOfPlayers.setDisable(true);
        setGameButton.setDisable(true);
        startHint.setVisible(false);
        setBars();
        allButtonsAndHintsDisabled();
        //tymczasowo
        ship.setRooms();

    }
    private void setBars(){
        larvaBar.setProgress(draw.calculateBar(1));
        creeperBar.setProgress(draw.calculateBar(2));
        adultBar.setProgress(draw.calculateBar(3));
        breederBar.setProgress(draw.calculateBar(4));
        queenBar.setProgress(draw.calculateBar(5));
        setBarColor(larvaBar);
        setBarColor(creeperBar);
        setBarColor(adultBar);
        setBarColor(breederBar);
        setBarColor(queenBar);
    }

    private void setBarColor(ProgressBar bar){
        if (bar.getProgress()>0.5){
            bar.setStyle("-fx-accent: red;");
        } else if (bar.getProgress()<=0.5 && bar.getProgress()>0.25) {
            bar.setStyle("-fx-accent: orange;");
        }else{
            bar.setStyle("-fx-accent: green;");
        }
    }

    @FXML
    private void onSetFireButtonClick(){
        if(!idRoomToSetFireOrDamage.getText().isEmpty()) {
            ship.setFireStatus(Integer.parseInt(idRoomToSetFireOrDamage.getText()));
            addEventLog("Pozar w pomieszczeniu " + idRoomToSetFireOrDamage.getText());
            idRoomToSetFireOrDamage.clear();
        }
    }

    @FXML
    private void onSetDamageButtonClick(){
        if(!idRoomToSetFireOrDamage.getText().isEmpty()) {
            ship.setDamageStatus(Integer.parseInt(idRoomToSetFireOrDamage.getText()));
            addEventLog("Uszkodzone pomieszczenie " + idRoomToSetFireOrDamage.getText());
            idRoomToSetFireOrDamage.clear();
        }
    }

    @FXML
    private void onRemoveFireButtonClick(){

    }

    @FXML
    private void onRemoveDamageButtonClick(){

    }

    @FXML
    private void onPickCardButtonClick(){

    }
    @FXML
    private void onPickTokenButtonClick(){

    }
    @FXML
    private void onAlienMoveButtonClick(){

    }
    @FXML
    private void onPlayerPositionButtonClick(){
        
    }

    @FXML
    private void onRemoveAlienButtonClick(){

    }

    private void addEventLog(String log){
        listViewEvents.getItems().add(0,log);
        listViewEvents.refresh();
    }

    private void allButtonsAndHintsDisabled(){
        removeAlienButton.setDisable(false);
        playerPositionButton.setDisable(false);
        alienMoveButton.setDisable(false);
        pickCardButton.setDisable(false);
        pickTokenButton.setDisable(false);
        setFireButton.setDisable(false);
        setDamageButton.setDisable(false);
        removeFireButton.setDisable(false);
        removeDamageButton.setDisable(false);
        idRoomToSetFireOrDamage.setDisable(false);
        idRoomToRemoveFireOrDamage.setDisable(false);
        idAlienToRemove.setDisable(false);
        idRoomPlayerPositionChange.setDisable(false);
        idPlayerPlayerPositionChange.setDisable(false);
        idL1.setDisable(false);
        idL2.setDisable(false);
        idL3.setDisable(false);
        idL4.setDisable(false);
        removeStatusHint.setDisable(false);
        statusHint.setDisable(false);
        ruleHint.setDisable(false);
        larvaBar.setDisable(false);
        creeperBar.setDisable(false);
        adultBar.setDisable(false);
        breederBar.setDisable(false);
        queenBar.setDisable(false);
        tokensLabel.setDisable(false);
        larvaL.setDisable(false);
        creeperL.setDisable(false);
        adultL.setDisable(false);
        breederL.setDisable(false);
        queenL.setDisable(false);
    }








}
