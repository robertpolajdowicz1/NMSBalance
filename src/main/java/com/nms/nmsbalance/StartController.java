package com.nms.nmsbalance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class StartController {
    @FXML
    private Button startButton ;

    @FXML
    protected void onStartButtonClick() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(new Scene(root,1200,700));
        System.out.printf("klik");
    }
}