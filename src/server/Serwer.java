package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import database.DatabsaeMySQL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import server.connectors.ClientConnector;
import server.threads.ConnectionListenerThread;

public class Serwer extends Application {
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1200, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setTitle("DWAY");
            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);

        try {
            ServerSocket serverSocket = new ServerSocket(1234);

            //nasluchujemy na polaczenie klientow i tworzymy nowe watki
            while (true) {
                ClientConnector clientConnector = new ClientConnector(serverSocket);

                Executor executor = Executors.newCachedThreadPool();
                executor.execute(new ConnectionListenerThread(clientConnector));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}