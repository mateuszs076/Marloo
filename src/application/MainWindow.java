package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindow {
	
	private static TableView zawartosc()
	{
		TableView zawartosc = new TableView();
		zawartosc.setEditable(true);
		
		TableColumn nazwa = new TableColumn("ZAWARTOŒÆ");
        nazwa.setPrefWidth(1200); 
        TableColumn index = new TableColumn("INDEX");
        index.setPrefWidth(300);
        TableColumn name = new TableColumn("Nazwa");
        name.setPrefWidth(300);
        TableColumn jm = new TableColumn("Jednostka miary");
        jm.setPrefWidth(300);
        TableColumn ilosc = new TableColumn("Iloœæ");
        ilosc.setPrefWidth(300);
        
        nazwa.getColumns().addAll(index, name, jm, ilosc);
        zawartosc.getColumns().addAll(nazwa);
        return zawartosc;
	}
	
	private static TableView pracownicy()
	{
		TableView pracownicy = new TableView();
		pracownicy.setEditable(true);
		
		TableColumn nazwa = new TableColumn("PRACOWNICY");
        nazwa.setPrefWidth(1200);  
        TableColumn imie = new TableColumn("Imiê");
        imie.setPrefWidth(300);
        TableColumn nazwisko = new TableColumn("Nazwisko");
        nazwisko.setPrefWidth(300);
        TableColumn login = new TableColumn("Nazwa u¿ytkownika w systemie");
        login.setPrefWidth(300);
        TableColumn uprawnienia = new TableColumn("Uprawnienia");
        uprawnienia.setPrefWidth(300);
        
        nazwa.getColumns().addAll(imie, nazwisko, login, uprawnienia);
        pracownicy.getColumns().addAll(nazwa);
        return pracownicy;
	}
	
	private static TableView odbiorcy()
	{
		TableView odbiorcy = new TableView();
		odbiorcy.setEditable(true);
		
		TableColumn nazwa = new TableColumn("ODBIORCY");
        nazwa.setPrefWidth(1200);  
        TableColumn imie = new TableColumn("Nazwa");
        imie.setPrefWidth(300);
        TableColumn nazwisko = new TableColumn("Kraj");
        nazwisko.setPrefWidth(300);
        TableColumn login = new TableColumn("Miasto");
        login.setPrefWidth(300);
        TableColumn uprawnienia = new TableColumn("Adres");
        uprawnienia.setPrefWidth(300);
        
        nazwa.getColumns().addAll(imie, nazwisko, login, uprawnienia);
        odbiorcy.getColumns().addAll(nazwa);
        return odbiorcy;
	}
	
	private static TableView maszyny()
	{
		TableView maszyny = new TableView();
		maszyny.setEditable(true);
		
		TableColumn nazwa = new TableColumn("MASZYNY");
        nazwa.setPrefWidth(1200);  
        TableColumn imie = new TableColumn("Nazwa");
        imie.setPrefWidth(400);
        TableColumn nazwisko = new TableColumn("Czas procesu");
        nazwisko.setPrefWidth(400);
        TableColumn login = new TableColumn("Produkowane");
        login.setPrefWidth(400);
        
        nazwa.getColumns().addAll(imie, nazwisko, login);
        maszyny.getColumns().addAll(nazwa);
        return maszyny;
	}
	
	private static void dodajDoMag(Stage primaryStage)
	{
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Dodawanie do Magazynu:"));
        Scene dialogScene = new Scene(dialogVbox, 700, 700);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	public static void mainWindow(Stage primaryStage, BorderPane root) {
		root.getChildren().clear();
		
		VBox vb=new VBox();
		vb.resize(1250, 20);
		MenuBar menuBar = new MenuBar();		
		Menu menuFile = new Menu("Zawartoœæ");
		Menu menuEdit = new Menu("Wysy³ki");
		Menu menuView = new Menu("Produkcja");
		Menu menuWork = new Menu("Pracownicy");
		Menu menuUser = new Menu("U¿ytkownik");
		Menu menuSet = new Menu("Ustawienia");
		
		final VBox vbox = new VBox();
        vbox.setLayoutY(50);
        vbox.resize(1200, 600);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(zawartosc());
        
		
		MenuItem menuItem1 = new MenuItem("Przegl¹daj zawartoœæ");
			menuItem1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	vbox.getChildren().clear();
	            	vbox.getChildren().add(zawartosc());
	            }
	        });
		MenuItem menuItem2 = new MenuItem("Dodaj do magazynu");
			menuItem2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	dodajDoMag(primaryStage);
	            }
	        });
		MenuItem menuItem3 = new MenuItem("Wydaj na produkcjê");
		
		MenuItem menuItem4 = new MenuItem("Przygotuj wysy³kê");
		MenuItem menuItem5 = new MenuItem("Wyœlij przygotowan¹");
		MenuItem menuItem6 = new MenuItem("Przegl¹daj Odbiorców");
			menuItem6.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	vbox.getChildren().clear();
	            	vbox.getChildren().add(odbiorcy());
	            }
	        });
		MenuItem menuItem7 = new MenuItem("Dodaj/Usuñ Odbiorców");
		
		Menu menuItem8 = new Menu("Zaplecze techniczne");
			MenuItem menuItem16 = new MenuItem("Przegl¹daj maszyny");
				menuItem16.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	vbox.getChildren().clear();
		            	vbox.getChildren().add(maszyny());
		            }
		        });
			MenuItem menuItem17 = new MenuItem("Dodaj maszynê");		
			MenuItem menuItem18 = new MenuItem("Edytuj parametry");
			menuItem8.getItems().add(menuItem16);
			menuItem8.getItems().add(menuItem17);
			menuItem8.getItems().add(menuItem18);
		
		MenuItem menuItem9 = new MenuItem("Zlecenia");
			menuItem9.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	vbox.getChildren().clear();
	            }
	        });
		
		MenuItem menuItem10 = new MenuItem("Wyœwietl listê pracowników");
			menuItem10.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	vbox.getChildren().clear();
	            	vbox.getChildren().add(pracownicy());
	            }
	        });
		MenuItem menuItem11 = new MenuItem("Wyœwietl Informcje");
		
		MenuItem menuItem12 = new MenuItem("Wyœwietl Informcje");
		MenuItem menuItem13 = new MenuItem("Wyloguj");		
		
		MenuItem menuItem14 = new MenuItem("Dodaj/ Usuñ u¿ytkownika");
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
		
		
		
        
        
		menuBar.getStyleClass().add("menu");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuWork, menuUser, menuSet);
		menuBar.resize(1250,0);
		vb.getChildren().addAll(menuBar);
		root.getChildren().add(vb);
		root.getChildren().add(vbox);
	}
}
