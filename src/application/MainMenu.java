package application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenu {

	public static void menu()
	{
		try {
			Stage primaryStage=new Stage();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1200,600);
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
