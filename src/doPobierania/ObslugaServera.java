package doPobierania;

import database.DatabasePostgree;
import database.DatabsaeMySQL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ObslugaServera extends Application {

	@Override
	public void start(Stage primaryStage) {

		Group grupka = new Group();
		BorderPane root = new BorderPane();
		primaryStage.setResizable(false);
		Scene scene = new Scene(root, 150, 150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Panel kontrolny Serwera");
		scene.getStylesheets().add(getClass().getResource("server.css").toExternalForm());
		Server doPobierania = new Server(1003);
		Server doUpowania = new Server(1004);

		Button run = new Button();

		run.setText("Start");

		run.setLayoutX(10);
		run.setLayoutY(10);
		run.getStyleClass().add("dark-blue");

		Button abort = new Button();
		abort.setText("Stop");
		abort.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				run.setDisable(false);
				abort.setDisable(true);
				if (doPobierania.isAlive())
					doPobierania.close();
				if (doUpowania.isAlive())
					doUpowania.close();
			}
		});
		abort.setLayoutX(10);
		abort.setLayoutY(60);
		abort.getStyleClass().add("dark-blue");

		run.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				run.setDisable(true);
				abort.setDisable(false);
				Platform.runLater(new Runnable() {
					public void run() {
						DatabsaeMySQL.initDB();
						DatabasePostgree.maininit();
					}
				});
				doPobierania.start();
				doUpowania.start();
			}
		});

		Button stop = new Button();
		stop.setText("Wyjdz");
		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		stop.setLayoutX(10);
		stop.setLayoutY(110);
		stop.getStyleClass().add("dark-blue");

		grupka.getChildren().add(run);
		grupka.getChildren().add(abort);
		grupka.getChildren().add(stop);

		root.getChildren().add(grupka);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
