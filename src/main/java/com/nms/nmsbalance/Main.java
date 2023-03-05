package com.nms.nmsbalance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 630);
        stage.setTitle("NMS Balance");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        launch();
    }
}