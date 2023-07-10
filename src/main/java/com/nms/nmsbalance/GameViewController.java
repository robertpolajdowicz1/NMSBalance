package com.nms.nmsbalance;

import com.nms.nmsbalance.services.Services;
import com.nms.nmsbalance.spaceship.Ship;
import com.nms.nmsbalance.tokenpool.Pool;
import com.nms.nmsbalance.validationLogs.Logs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import java.io.IOException;
import java.nio.file.Paths;


public class GameViewController {
    String mediaUrl = "C:\\Users\\rober\\IdeaProjects\\NMSBalance\\src\\main\\resources\\com\\nms\\nmsbalance\\m1.mp3";
    private Media media = new Media(Paths.get(mediaUrl).toUri().toString());
    private MediaPlayer player = new MediaPlayer(media);

    boolean showMapClicked = false;
    private Stage mapStage;

    private final String emptyTextField = "Puste pole ID";

    private Services services = new Services(new Pool(), new Ship());

    @FXML
    private Button showMapButton,changeDifficultyButton,lockDifficultyButton,musicButton, setGameButton, removeAlienButton, playerPositionButton, addTokenButton, alienMoveButton, pickTokenButton, setFireButton, setDamageButton, removeFireButton, removeDamageButton, alienEncounterButton, setNestButton;
    @FXML
    private ChoiceBox<String> numberOfPlayers,difficulty;

    @FXML
    private Label startHint, setStatusHint, removeStatusHint, alienEncounterHint, removeAlienHint, IDL1, IDL2, IDL3, ruleHint, eventLabel, tokensLabel, larvaL, creeperL, adultL, breederL, queenL, historyL, aliensL, playersL, addTokenHint, roomsL, nestHint;
    @FXML
    private ProgressBar larvaBar, creeperBar, adultBar, breederBar, queenBar;
    @FXML
    private TextField IDRoomToRemoveFireOrDamage, IDRoomToSetFireOrDamage, IDAlienToRemove, IDRoomPlayerPositionChange, IDPlayerPlayerPositionChange, IDPlayerAlienEncounter, IDTokenTier, idNest;
    @FXML
    private ListView<String> eventListView, aliensListView, playersListView, roomsListView;
    @FXML private Button debugButton;
    @FXML private ListView<String > debugList;
    private int counterDebug = 0;

    @FXML
    private void onPlayerPositionButtonClick() {
        if (!IDPlayerPlayerPositionChange.getText().isEmpty() && !IDRoomPlayerPositionChange.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.changePlayerPosition(IDRoomPlayerPositionChange.getText(),
                    IDPlayerPlayerPositionChange.getText()));
            IDRoomPlayerPositionChange.clear();
            IDPlayerPlayerPositionChange.clear();
            Logs.addPlayerLog(playersListView, services.getShip());
            Logs.addRoomLog(roomsListView, services.getShip());
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onAlienMoveButtonClick() {

        Logs.addEventLog(eventListView, services.alienMove());
        Logs.addDebugLog(debugList,services.getShip());
        Logs.addAlienLog(aliensListView, services.getShip());
        Logs.addRoomLog(roomsListView, services.getShip());
    }

    @FXML
    private void onPickTokenButtonClick() {
        Logs.addEventLog(eventListView, services.pickToken());
        Logs.addAlienLog(aliensListView, services.getShip());
        setBars();
    }

    @FXML
    private void onAddTokenButtonClick() {
        if (!IDTokenTier.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.addToken(IDTokenTier.getText()));
            IDTokenTier.clear();
            setBars();
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onSetFireButtonClick() {
        if (!IDRoomToSetFireOrDamage.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.setFire(IDRoomToSetFireOrDamage.getText()));
            IDRoomToSetFireOrDamage.clear();
            Logs.addRoomLog(roomsListView, services.getShip());
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onSetDamageButtonClick() {
        if (!IDRoomToSetFireOrDamage.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.setDamage(IDRoomToSetFireOrDamage.getText()));
            IDRoomToSetFireOrDamage.clear();
            Logs.addRoomLog(roomsListView, services.getShip());
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onRemoveFireButtonClick() {
        if (!IDRoomToRemoveFireOrDamage.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.removeFire(IDRoomToRemoveFireOrDamage.getText()));
            IDRoomToRemoveFireOrDamage.clear();
            Logs.addRoomLog(roomsListView, services.getShip());
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onRemoveDamageButtonClick() {
        if (!IDRoomToRemoveFireOrDamage.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.removeDamage(IDRoomToRemoveFireOrDamage.getText()));
            IDRoomToRemoveFireOrDamage.clear();
            Logs.addRoomLog(roomsListView, services.getShip());
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    public void onAlienEncounterButtonClick() {

        if (!IDPlayerAlienEncounter.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.alienEncounter(IDPlayerAlienEncounter.getText()));
            Logs.addAlienLog(aliensListView, services.getShip());
            Logs.addRoomLog(roomsListView, services.getShip());
            IDPlayerAlienEncounter.clear();
            setBars();
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onRemoveAlienButtonClick() {
        if (!IDAlienToRemove.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.removeAlien(IDAlienToRemove.getText()));
            Logs.addAlienLog(aliensListView, services.getShip());
            Logs.addRoomLog(roomsListView, services.getShip());
            IDAlienToRemove.clear();
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onSetNestButtonClick() {
        if (!idNest.getText().isEmpty()) {
            Logs.addEventLog(eventListView, services.setNest(idNest.getText()));
            if (services.getShip().checkIfNestFounded()) {
                idNest.setVisible(false);
                nestHint.setVisible(false);
                setNestButton.setVisible(false);
                Logs.addRoomLog(roomsListView, services.getShip());
            }
            idNest.clear();
        } else {
            Logs.addEventLog(eventListView, emptyTextField);
        }
    }

    @FXML
    private void onSetGameButtonClick() {
        services.setGame(numberOfPlayers.getValue());
        startHint.setVisible(false);
        numberOfPlayers.setVisible(false);
        setGameButton.setVisible(false);
        setBars();
        enableGUI();
        Logs.addPlayerLog(playersListView, services.getShip());
        Logs.addRoomLog(roomsListView, services.getShip());
    }



    private void setBars() {
        larvaBar.setProgress(services.calculateBar(1));
        creeperBar.setProgress(services.calculateBar(2));
        adultBar.setProgress(services.calculateBar(3));
        breederBar.setProgress(services.calculateBar(4));
        queenBar.setProgress(services.calculateBar(5));
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
        pickTokenButton.setDisable(false);
        setFireButton.setDisable(false);
        setDamageButton.setDisable(false);
        removeFireButton.setDisable(false);
        removeDamageButton.setDisable(false);
        alienEncounterButton.setDisable(false);
        addTokenButton.setDisable(false);
        setNestButton.setDisable(false);
        changeDifficultyButton.setDisable(false);
        showMapButton.setDisable(false);
        difficulty.setDisable(false);
        lockDifficultyButton.setDisable(false);
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
    @FXML
    public void handlePlayButton() {
        if (player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.pause();
            musicButton.setText("Odtwarzanie");
        } else {
            player.play();
            musicButton.setText("Pauza");
        }
    }

    @FXML
    public void onLockDifficultyButtonClick() {
        Logs.addEventLog(eventListView,"Zablokowano poziom trudnosci na " + difficulty.getValue());
        changeDifficultyButton.setDisable(true);
        changeDifficultyButton.setVisible(false);
        difficulty.setDisable(true);
        difficulty.setVisible(false);
        lockDifficultyButton.setDisable(true);
    }
    @FXML
    public void onChangeDifficultyButtonClick() {
        Logs.addEventLog(eventListView,services.changeDifficulty(difficulty.getValue()));
    }

    @FXML

    public void onDebugButtonClick() {
        if(counterDebug >= 5){
            debugList.setVisible(true);
        }else{
            counterDebug++;
        }
    }

    public void onShowMapButtonClick() {
        if (!showMapClicked) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("map-view.fxml"));
                Parent root = loader.load();
                mapStage = new Stage();
                MapViewController mapController = loader.getController();
                mapController.setServices(services);
                mapController.onRefreshButtonClicked();
                mapStage.setScene(new Scene(root));
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double windowHeight = screenBounds.getHeight() * 0.69;
                double windowY = screenBounds.getHeight() - windowHeight;
               // mapStage.setY(windowY);
                mapStage.setY(windowY);
               // mapStage.setHeight(windowHeight);
                mapStage.setHeight(740);
                mapStage.setWidth(1068.0);
                mapStage.show();
                showMapClicked = true;
                mapStage.setTitle("Mapa");
                mapStage.setResizable(false);
               // mapStage.setAlwaysOnTop(true);
                showMapButton.setText("Mapa OFF");
                Logs.addEventLog(eventListView, "Mapa otwarta");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mapStage.close();
            showMapClicked = false;
            showMapButton.setText("Mapa ON");
            Logs.addEventLog(eventListView, "Mapa zamknieta");
        }
    }
}