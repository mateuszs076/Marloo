package server;

import application.AboutWindow;
import application.LoginWindow;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StronaSerwera {

	public static void okno(Stage primaryStage, BorderPane root)
	{
		Text des=new Text("Przed uruchomieniem serwera prosz� uruchomi� program XAMPP");
		des.setTextAlignment(TextAlignment.JUSTIFY);
		des.setLayoutX(200);
		des.setLayoutY(100);
		des.setWrappingWidth(800);
		des.getStyleClass().add("font");
		
		Button enter=new Button("Initialize Database");
		enter.resize(500, 100);
		enter.setLayoutX(350);
		enter.setLayoutY(200);
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
			    }
		});
		Button about=new Button("Run Server");
		about.resize(500, 100);
		about.setLayoutX(350);
		about.setLayoutY(320);
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
			    }
		});
		
		Button exit=new Button("Stop Server");
		exit.resize(500, 100);
		exit.setLayoutX(350);
		exit.setLayoutY(440);
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
		    }
		});
		
		
		root.getChildren().add(des);
		root.getChildren().add(enter);
		root.getChildren().add(exit);
		root.getChildren().add(about);
	}
}
