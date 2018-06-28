package application;

//import database.DatabsaeMySQL;

import Communication.Data.User;
import application.connections.ServerConnector;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.database.DatabsaeMySQL;

import java.io.IOException;

public class RegisterWindow {

    private ServerConnector serverConnector;

    public RegisterWindow(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public void register(Stage primaryStage, BorderPane root) {
        root.getChildren().clear();


        Text rejestracja = new Text("Rejestracja");
        rejestracja.setLayoutX(300);
        rejestracja.setLayoutY(300);
        rejestracja.setStyle("-fx-font-size: 120pt;");
        rejestracja.setRotate(25);
        rejestracja.setFill(Color.DARKSLATEGREY);
        Text imie = new Text("Imie:");
        imie.setLayoutX(20);
        imie.setLayoutY(60);
        imie.setStyle("-fx-font-size: 20pt;");
        imie.setFill(Color.NAVY);
        Text nazwisko = new Text("Nazwisko:");
        nazwisko.setLayoutX(20);
        nazwisko.setLayoutY(130);
        nazwisko.setStyle("-fx-font-size: 20pt;");
        nazwisko.setFill(Color.NAVY);
        Text login = new Text("Login:");
        login.setLayoutX(20);
        login.setLayoutY(200);
        login.setStyle("-fx-font-size: 20pt;");
        login.setFill(Color.NAVY);
        Text haslo = new Text("Has³o:");
        haslo.setLayoutX(20);
        haslo.setLayoutY(270);
        haslo.setStyle("-fx-font-size: 20pt;");
        haslo.setFill(Color.NAVY);
        Text haslo2 = new Text("Powtórz has³o:");
        haslo2.setLayoutX(20);
        haslo2.setLayoutY(340);
        haslo2.setStyle("-fx-font-size: 20pt;");
        haslo2.setFill(Color.NAVY);


        TextField imie0 = new TextField();
        imie0.setLayoutX(20);
        imie0.setLayoutY(70);
        imie0.resize(150, 30);
        imie0.setVisible(true);
        TextField nazwisko0 = new TextField();
        nazwisko0.setLayoutX(20);
        nazwisko0.setLayoutY(140);
        nazwisko0.resize(150, 30);
        nazwisko0.setVisible(true);
        TextField login0 = new TextField();
        login0.setLayoutX(20);
        login0.setLayoutY(210);
        login0.resize(150, 30);
        login0.setVisible(true);
        PasswordField haslo0 = new PasswordField();
        haslo0.setLayoutX(20);
        haslo0.setLayoutY(280);
        haslo0.resize(150, 30);
        haslo0.setVisible(true);
        PasswordField haslo20 = new PasswordField();
        haslo20.setLayoutX(20);
        haslo20.setLayoutY(350);
        haslo20.resize(150, 30);
        haslo20.setVisible(true);

        Button about = new Button("Create");
        about.resize(200, 80);
        about.setLayoutX(20);
        about.setLayoutY(400);
        about.setVisible(true);
        about.getStyleClass().add("button");
        about.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                about.getStyleClass().add("button-entered");
            }
        });
        about.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                about.getStyleClass().clear();
                about.getStyleClass().add("button");
            }
        });
        about.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (!imie0.getText().isEmpty() &&
                        !haslo0.getText().isEmpty() &&
                        !nazwisko0.getText().isEmpty() &&
                        !haslo20.getText().isEmpty() &&
                        !login0.getText().isEmpty()) {

                    if (haslo0.getText().equals(haslo20.getText())) {
//                        DatabsaeMySQL.getInstance().addUser(imie0.getText(), nazwisko0.getText(), login0.getText(), haslo0.getText());
                        System.out.println("DodajÄ™ usera");
                        try {
                            serverConnector.sendObject(new User(login0.getText(),haslo0.getText(), imie0.getText(), nazwisko0.getText(), true, 5));

                            Platform.runLater(() -> new LoginWindow().login(primaryStage, root, serverConnector));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                new RegisterWindow(serverConnector).register(primaryStage, root);
            }
        });

        Button exit = new Button("Back");
        exit.resize(200, 80);
        exit.setLayoutX(20);
        exit.setLayoutY(490);
        exit.setVisible(true);
        exit.getStyleClass().add("button");
        exit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                exit.getStyleClass().add("button-entered");
            }
        });
        exit.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                exit.getStyleClass().clear();
                exit.getStyleClass().add("button");
            }
        });
        exit.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
//                MainMenu.menu(primaryStage.getScene());
            }
        });


        root.getChildren().addAll(rejestracja, imie, imie0, nazwisko, nazwisko0, login, login0, haslo, haslo0, haslo2, haslo20, exit, about);
    }
}
