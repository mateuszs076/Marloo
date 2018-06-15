package application;

import java.io.IOException;

import application.connections.ServerConnector;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                ServerConnector serverConnector;
                //tworzenie klasy pomocniczej - zawiera objectOutputStream i objectInputStream i Socket
                serverConnector = ServerConnector.getInstance();
                serverConnector.init();
                System.out.println("Creating serverConnector");
            }
        }.start();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1200, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            WelcomeClass.goodMorning(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
