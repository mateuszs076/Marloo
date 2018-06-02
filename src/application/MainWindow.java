package application;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
	public static void mainWindow(Stage primaryStage, BorderPane root) {
		root.getChildren().clear();
		
		VBox vb=new VBox();
		vb.resize(1250, 20);
		MenuBar menuBar = new MenuBar();		
		Menu menuFile = new Menu("Zawartoœæ");
		Menu menuEdit = new Menu("Wysy³ki");
		Menu menuView = new Menu("Produkcja");
		
		
		MenuItem menuItem1 = new MenuItem("Przegl¹daj zawartoœæ");
		MenuItem menuItem2 = new MenuItem("Dodaj do magazynu");
		MenuItem menuItem3 = new MenuItem("Wydaj na produkcjê");
		
		menuFile.getItems().add(menuItem1);
		menuFile.getItems().add(menuItem2);
		menuFile.getItems().add(menuItem3);

		menuBar.getStyleClass().add("menu");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		menuBar.resize(1250,0);
		vb.getChildren().addAll(menuBar);
		root.getChildren().add(vb);
	}
}
