package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) {
		/*(new Serwer(752)).start();
		(new Serwer(753)).start();
		(new Serwer(754)).start();
		(new Serwer(755)).start();
		(new Serwer(756)).start();
		(new Serwer(757)).start();
		(new Serwer(758)).start();
		(new Serwer(759)).start();
		(new Serwer(759)).start();*/
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1200, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Programowanie");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setResizable(false);
		StronaSerwera.okno(primaryStage, root);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
