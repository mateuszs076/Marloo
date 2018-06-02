package application;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {
	public static void mainWindow(Stage primaryStage, BorderPane root) {
		root.getChildren().clear();
		MenuBar menuBar = new MenuBar();		
		Menu menuFile = new Menu("Zawartoœæ");
		Menu menuEdit = new Menu("Wysy³ki");
		Menu menuView = new Menu("Produkcja");
		

		menuBar.getStyleClass().add("menu");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		menuBar.resize(1250,0);
		root.getChildren().addAll(menuBar);
	}
}
