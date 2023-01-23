package com.nms.nmsbalance;

import com.nms.nmsbalance.spaceship.Ship;
import com.nms.nmsbalance.tokenpool.Draw;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;

public class GameViewController {
    private Ship ship = new Ship();
    private Draw draw = new Draw();


    @FXML
    private ChoiceBox<String> numberOfPlayers;
    @FXML
    private AnchorPane gameViewAnchor;
    @FXML
    private Button setGame;
    @FXML
    private Label startHint;
    @FXML
    private ProgressBar larvaBar;
    @FXML
    private ProgressBar creeperBar;
    @FXML
    private ProgressBar adultBar;
    @FXML
    private ProgressBar breederBar;
    @FXML
    private ProgressBar queenBar;

    @FXML
    private void onSetGameButtonsClick(){
        draw.setPool( Integer.valueOf(numberOfPlayers.getValue()));
        numberOfPlayers.setDisable(true);
        setGame.setDisable(true);
        startHint.setVisible(false);
        setBars();

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








}
