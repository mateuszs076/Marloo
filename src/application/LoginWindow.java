package application;

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
	public static void login(Stage primaryStage, BorderPane root)
	{
		root.getChildren().clear();
		
		Text type=new Text("Type your login and password");
		type.setLayoutX(200);
		type.setLayoutY(50);
		type.setWrappingWidth(800);
		type.getStyleClass().add("font");
		type.setTextAlignment(TextAlignment.CENTER);
		
		Text log=new Text("Login:");
		log.setLayoutX(200);
		log.setLayoutY(100);
		log.setWrappingWidth(800);
		log.getStyleClass().add("font");
		log.setTextAlignment(TextAlignment.CENTER);
		
		TextField l=new TextField();
		l.setLayoutX(400);
		l.setLayoutY(120);
		l.resize(400, 40);
		l.setVisible(true);
		l.setAlignment(Pos.CENTER);
		
		Text pass=new Text("Password:");
		pass.setLayoutX(200);
		pass.setLayoutY(200);
		pass.setWrappingWidth(800);
		pass.getStyleClass().add("font");
		pass.setTextAlignment(TextAlignment.CENTER);
		
		PasswordField p=new PasswordField();
		p.setLayoutX(400);
		p.setLayoutY(220);
		p.resize(400, 40);
		p.setVisible(true);
		p.setAlignment(Pos.CENTER);
		
		Button enter=new Button("Log in");
		enter.resize(500, 80);
		enter.setLayoutX(350);
		enter.setLayoutY(310);
		enter.setVisible(true);
		enter.getStyleClass().add("button");
		enter.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	enter.getStyleClass().add("button-entered");
		    }
		});
		enter.setOnMouseExited(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	enter.getStyleClass().clear();
		    	enter.getStyleClass().add("button");
		    }
		});
		enter.setOnMousePressed(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
			      LoginWindow.login(primaryStage, root);
			    }
		});
		
		Button about=new Button("Create an acount");
		about.resize(500, 80);
		about.setLayoutX(350);
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
			      AboutWindow.about(primaryStage, root);
			    }
		});
		
		Button exit=new Button("Back");
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
		       MainMenu.menu(primaryStage.getScene());
		    }
		});
		
		
		root.getChildren().add(type);
		root.getChildren().add(pass);
		root.getChildren().add(log);
		root.getChildren().add(l);
		root.getChildren().add(p);
		root.getChildren().add(enter);
		root.getChildren().add(about);
		root.getChildren().add(exit);
		
		
		
	}
}
