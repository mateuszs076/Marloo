package application;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

import Communication.Communication;
import Communication.Data.User;
import application.connections.ServerConnector;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginWindow {
//    static ServerConnector serverConnector = ServerConnector.getInstance();
//    static boolean sprawdzanie(String login, String haslo) {
//        return false;
//    }

    public void login(Stage primaryStage, BorderPane root, ServerConnector serverConnector) {
        root.getChildren().clear();

        Text type = new Text("Type your login and password");
        type.setLayoutX(200);
        type.setLayoutY(50);
        type.setWrappingWidth(800);
        type.getStyleClass().add("font");
        type.setTextAlignment(TextAlignment.CENTER);

        Text log = new Text("Login:");
        log.setLayoutX(200);
        log.setLayoutY(100);
        log.setWrappingWidth(800);
        log.getStyleClass().add("font");
        log.setTextAlignment(TextAlignment.CENTER);

        TextField l = new TextField();
        l.setLayoutX(400);
        l.setLayoutY(120);
        l.resize(400, 40);
        l.setVisible(true);
        l.setAlignment(Pos.CENTER);

        Text pass = new Text("Password:");
        pass.setLayoutX(200);
        pass.setLayoutY(200);
        pass.setWrappingWidth(800);
        pass.getStyleClass().add("font");
        pass.setTextAlignment(TextAlignment.CENTER);

        PasswordField p = new PasswordField();
        p.setLayoutX(400);
        p.setLayoutY(220);
        p.resize(400, 40);
        p.setVisible(true);
        p.setAlignment(Pos.CENTER);

        Button create_an_acount = new Button("Create an acount");
        create_an_acount.resize(500, 80);
        create_an_acount.setLayoutX(350);
        create_an_acount.setLayoutY(400);
        create_an_acount.setVisible(true);
        create_an_acount.getStyleClass().add("button");
        create_an_acount.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                create_an_acount.getStyleClass().add("button-entered");
            }
        });
        create_an_acount.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                create_an_acount.getStyleClass().clear();
                create_an_acount.getStyleClass().add("button");
            }
        });
        create_an_acount.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                AboutWindow.about(primaryStage, root);
            }
        });

        Button log_in = new Button("Log in");
        log_in.resize(500, 80);
        log_in.setLayoutX(350);
        log_in.setLayoutY(310);
        log_in.setVisible(true);
        log_in.getStyleClass().add("button");
        log_in.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                log_in.getStyleClass().add("button-entered");
            }
        });
        log_in.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                log_in.getStyleClass().clear();
                log_in.getStyleClass().add("button");
            }
        });
        log_in.setOnMousePressed(me -> {
            System.out.println("Mouse Pressed");
            AtomicBoolean isAuth = new AtomicBoolean(false);
            Object answer = Communication.UNAUTHORIZED_LOGIN;

            new Thread(() -> {
                System.out.printf("new thread");
                if (!l.getText().trim().isEmpty() && !p.getText().trim().isEmpty()) {
//                    System.out.printf("not empty fields");
                    User user = new User(l.getText(), p.getText());
//                    System.out.println("created user from fields");
//                    new Thread(() -> {
////                        try {
//                            ServerConnector serverConnector = ServerConnector.getInstance();
//                            System.out.print("try block");
//                            System.out.print("serverconnector instance");
                        try {
                            System.out.println(serverConnector.toString());
                            serverConnector.sendObject(user);

                            var obj = serverConnector.receiveObject();
                            System.out.println("received User?");
                            if (obj != Communication.UNAUTHORIZED_LOGIN) {
                                System.out.println("mainwindowattempt");
                                isAuth.set(true);
                                Platform.runLater(() -> new MainWindow().mainWindow(primaryStage, root, (User) obj));
                            } else {
                                System.out.println("cos nie tak z userem");
                                //todo okno z niepoprawnym loginem
                                isAuth.set(false);
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
////                            serverConnector = null;
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        } catch (ClassNotFoundException e) {
////                            e.printStackTrace();
////                        }
//                    }).start();

                    log_in.setDisable(true);
                    create_an_acount.setDisable(true);
                    l.clear();
                    p.clear();
                }
            }).start();


            //DatabsaeMySQL.login(l.getText(), p.getText());
            //if(getUserzy(l.getText(), p.getText()))
//                try {
//                    if (DatabsaeMySQL.login(l.getText(), p.getText()) >= 0)
//                        MainWindow.mainWindow(primaryStage, root, DatabsaeMySQL.login(l.getText(), p.getText()));
//                    else
//                        System.out.println("coŚ poszło nie tak");
//                } catch (SQLException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
        });

        Button exit = new Button("Back");
        exit.resize(500, 80);
        exit.setLayoutX(350);
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
                MainMenu.menu(primaryStage.getScene(), serverConnector);
            }
        });


        root.getChildren().add(type);
        root.getChildren().add(pass);
        root.getChildren().add(log);
        root.getChildren().add(l);
        root.getChildren().add(p);
        root.getChildren().add(log_in);
        root.getChildren().add(create_an_acount);
        root.getChildren().add(exit);


    }

//    public static boolean getUserzy(String login, String haslo) {
//        try {
//            int port = 1003;//port na sztywno
//            Socket socket = new Socket("127.0.0.1", port);
//            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            //String str = "login";//warunek na serwerze ServerThred.30
//            String str = "login," + login + "," + haslo;
//            socket.setTcpNoDelay(true);
//            out.println(str);
//            out.flush();
//            InputStream inputStream = socket.getInputStream();
//            ObjectInputStream objInputStream = null;
//            objInputStream = new ObjectInputStream(inputStream);
//            //ArrayList<User> userzy = (ArrayList<User>) objInputStream.readObject();//znowu castowanko
//            System.out.println("Pobrałem pytanka");
//            socket.close();
//            return true;
//        } catch (Exception e) {
//            System.err.println(e);
//            return false;
//        }
//    }
}
