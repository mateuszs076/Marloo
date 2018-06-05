package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SecondWindow {

	public static void second()
	{
		Stage primaryStage=new Stage();
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1200,600);
			primaryStage.initStyle(StageStyle.DECORATED);
			scene.setFill(Color.BLUEVIOLET);
			primaryStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
