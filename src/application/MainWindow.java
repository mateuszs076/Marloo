package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainWindow {
	public static void mainWindow(Stage primaryStage, BorderPane root) {
		root.getChildren().clear();
		
		VBox vb=new VBox();
		vb.resize(1250, 20);
		MenuBar menuBar = new MenuBar();		
		Menu menuFile = new Menu("Zawarto��");
		Menu menuEdit = new Menu("Wysy�ki");
		Menu menuView = new Menu("Produkcja");
		Menu menuWork = new Menu("Pracownicy");
		Menu menuUser = new Menu("U�ytkownik");
		Menu menuSet = new Menu("Ustawienia");
		
		
		MenuItem menuItem1 = new MenuItem("Przegl�daj zawarto��");
		MenuItem menuItem2 = new MenuItem("Dodaj do magazynu");
		MenuItem menuItem3 = new MenuItem("Wydaj na produkcj�");
		
		MenuItem menuItem4 = new MenuItem("Przygotuj wysy�k�");
		MenuItem menuItem5 = new MenuItem("Wy�lij przygotowan�");
		MenuItem menuItem6 = new MenuItem("Przegl�daj Odbiorc�w");
		MenuItem menuItem7 = new MenuItem("Dodaj/Usu� Odbiorc�w");
		
		Menu menuItem8 = new Menu("Zaplecze techniczne");
			MenuItem menuItem16 = new MenuItem("Przegl�daj maszyny");
			MenuItem menuItem17 = new MenuItem("Dodaj maszyn�");		
			MenuItem menuItem18 = new MenuItem("Edytuj parametry");
			menuItem8.getItems().add(menuItem16);
			menuItem8.getItems().add(menuItem17);
			menuItem8.getItems().add(menuItem18);
		
		MenuItem menuItem9 = new MenuItem("Zlecenia");
		
		MenuItem menuItem10 = new MenuItem("Zmie� poziom");
		MenuItem menuItem11 = new MenuItem("Wy�wietl Informcje");
		
		MenuItem menuItem12 = new MenuItem("Wy�wietl Informcje");
		MenuItem menuItem13 = new MenuItem("Wyloguj");		
		
		MenuItem menuItem14 = new MenuItem("Dodaj/ Usu� u�ytkownika");
		MenuItem menuItem15 = new MenuItem("Nadaj uprawnienia");
		
		menuFile.getItems().add(menuItem1);
		menuFile.getItems().add(menuItem2);
		menuFile.getItems().add(menuItem3);
		
		menuEdit.getItems().add(menuItem4);
		menuEdit.getItems().add(menuItem5);
		menuEdit.getItems().add(menuItem6);
		menuEdit.getItems().add(menuItem7);
		
		menuView.getItems().add(menuItem8);
		menuView.getItems().add(menuItem9);
		
		menuWork.getItems().add(menuItem10);
		menuWork.getItems().add(menuItem11);
		
		menuUser.getItems().add(menuItem12);
		menuUser.getItems().add(menuItem13);
		
		menuSet.getItems().add(menuItem14);
		menuSet.getItems().add(menuItem15);
		
		
		TableView table = new TableView();
		table.setEditable(true);
		Label label = new Label("Zawarto�� Magazynu");
        label.setFont(new Font("Arial", 20));
		 
        TableColumn index = new TableColumn("INDEX");
        TableColumn name = new TableColumn("Nazwa");
        TableColumn jm = new TableColumn("Jednostka miary");
        TableColumn ilosc = new TableColumn("Ilo��");
        
        table.getColumns().addAll(index, name, jm, ilosc);
        
        final VBox vbox = new VBox();
        vbox.setLayoutY(50);
        vbox.resize(1200, 600);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        
		menuBar.getStyleClass().add("menu");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuWork, menuUser, menuSet);
		menuBar.resize(1250,0);
		vb.getChildren().addAll(menuBar);
		root.getChildren().add(vb);
		root.getChildren().add(vbox);
	}
}
