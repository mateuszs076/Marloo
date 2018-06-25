package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AboutWindow {
	public static void about(Stage primaryStage, BorderPane root)
	{
		root.getChildren().clear();
		
		Text des=new Text("Marloo - jest aplikacja wykonana przez dwójkę studentów trzeciego "
				+ "roku informatyki jako projekt realizowany w ramach przedmiotu: "
				+ "Przetwarzanie Rozporoszone i Równoległe. Założeniem programu jest wspomaganie pracy magazynu i lini produkcyjnej."
				+ " Autorami projektu są: Stanek Mateusz oraz Wierzbic Krystian.");
		des.setTextAlignment(TextAlignment.JUSTIFY);
		des.setLayoutX(200);
		des.setLayoutY(100);
		des.setWrappingWidth(800);
		des.getStyleClass().add("font");
		
		Button exit=new Button("Back");
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
		    	primaryStage.close();
//		       MainMenu.menu(primaryStage.getScene(), );
		    }
		});
		
		root.getChildren().add(des);
		root.getChildren().add(exit);
	}
}
