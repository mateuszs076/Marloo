package application;

import java.io.IOException;

import application.connections.ServerConnector;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import server.Server;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            ServerConnector serverConnector = new ServerConnector();
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1200, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            new WelcomeClass(primaryStage, serverConnector).goodMorning(primaryStage);
//            WelcomeClass.goodMorning(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
