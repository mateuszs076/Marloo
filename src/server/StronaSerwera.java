package server;

import server.database.DatabasePostgree;
import server.database.DatabsaeMySQL;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StronaSerwera {

    public StronaSerwera() {
    }

    public static void okno(Stage primaryStage, BorderPane root) {
    	Text des = new Text("Przed uruchomieniem serwera proszę uruchomić program XAMPP");
        des.setTextAlignment(TextAlignment.JUSTIFY);
        des.setLayoutX(200);
        des.setLayoutY(100);
        des.setWrappingWidth(800);
        des.getStyleClass().add("font");

        Button stop_server = new Button("Stop Server");
        stop_server.resize(500, 100);
        stop_server.setLayoutX(350);
        stop_server.setLayoutY(440);
        stop_server.setVisible(true);
        stop_server.setDisable(true);
        stop_server.getStyleClass().add("button");

        //onClicks
        stop_server.setOnMouseEntered(me -> stop_server.getStyleClass().add("button-entered"));
        stop_server.setOnMouseExited(me -> {
            stop_server.getStyleClass().clear();
            stop_server.getStyleClass().add("button");
        });

        stop_server.setOnMousePressed(me -> System.exit(1));

        Button initialize_database = new Button("Initialize Database");
        initialize_database.setDisable(true);
        initialize_database.resize(500, 100);
        initialize_database.setLayoutX(350);
        initialize_database.setLayoutY(200);
        initialize_database.setVisible(true);
        initialize_database.getStyleClass().add("button");

        initialize_database.setOnMouseEntered(me -> initialize_database.getStyleClass().add("button-entered"));
        initialize_database.setOnMouseExited(me -> {
            initialize_database.getStyleClass().clear();
            initialize_database.getStyleClass().add("button");
        });

        initialize_database.setOnMousePressed(me -> {
            initialize_database.setDisable(true);
           DatabasePostgree.getInstance().maininit();
           DatabsaeMySQL.getInstance().initDB();
        });

        Button run_server = new Button("Run Server");
        run_server.resize(500, 100);
        run_server.setLayoutX(350);
        run_server.setLayoutY(320);
        run_server.setVisible(true);
        run_server.getStyleClass().add("button");

        //onClicks
        run_server.setOnMouseEntered(me -> run_server.getStyleClass().add("button-entered"));
        run_server.setOnMouseExited(me -> {
            run_server.getStyleClass().clear();
            run_server.getStyleClass().add("button");
        });

        run_server.setOnMousePressed(me -> {
            run_server.setDisable(true);
            initialize_database.setDisable(false);
            stop_server.setDisable(false);

            new Thread(Server::new).start();
        });

        root.getChildren().add(des);
        root.getChildren().add(initialize_database);
        root.getChildren().add(stop_server);
        root.getChildren().add(run_server);
    }
}
