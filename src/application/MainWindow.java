package application;

import java.io.Serializable;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import Communication.Data.PopUps;
import Communication.Data.Tabele;
import Communication.Data.User;
import application.connections.ServerConnector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow {

	private ServerConnector serverConnector;

	public MainWindow(ServerConnector serverConnector) {
		this.serverConnector = serverConnector;
	}

	public void mainWindow(Stage primaryStage, BorderPane root, User user) {
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
        vbox.getChildren().addAll(Tabele.zawartosc(serverConnector));
        
		
		MenuItem menuItem1 = new MenuItem("Przegl¹daj zawartoœæ");
			menuItem1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	vbox.getChildren().clear();
	            	vbox.getChildren().add(Tabele.zawartosc(serverConnector));
	            }
	        });
		MenuItem menuItem2 = new MenuItem("Dodaj do magazynu");
			menuItem2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	PopUps.dodajDoMag(primaryStage);
	            }
	        });
		MenuItem menuItem3 = new MenuItem("Wydaj na produkcjê");
			menuItem3.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	PopUps.wydajNaProdukcje(primaryStage);
	            }
	        });
		MenuItem menuItem4 = new MenuItem("Przygotuj wysy³kê");
		MenuItem menuItem5 = new MenuItem("Wyœlij przygotowan¹");
		MenuItem menuItem6 = new MenuItem("Przegl¹daj Odbiorców");
			menuItem6.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	vbox.getChildren().clear();
	            	vbox.getChildren().add(Tabele.odbiorcy());
	            }
	        });
		MenuItem menuItem7 = new MenuItem("Dodaj/Usuñ Odbiorców");
		
		Menu menuItem8 = new Menu("Zaplecze techniczne");
			MenuItem menuItem16 = new MenuItem("Przegl¹daj maszyny");
				menuItem16.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	vbox.getChildren().clear();
		            	vbox.getChildren().add(Tabele.maszyny());
		            }
		        });
			MenuItem menuItem17 = new MenuItem("Dodaj maszynê");
				menuItem17.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	PopUps.dodajMaszyne(primaryStage);
		            }
		        });
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
	            	vbox.getChildren().add(Tabele.pracownicy(serverConnector));
	            }
	        });
		MenuItem menuItem11 = new MenuItem("Wyœwietl Informcje");
		
		MenuItem menuItem12 = new MenuItem("Wyœwietl Informcje");
		MenuItem menuItem13 = new MenuItem("Wyloguj");	
		menuItem13.setOnAction(e -> {
			new LoginWindow().login(primaryStage, root, serverConnector);
		});
		
		MenuItem menuItem14 = new MenuItem("Dodaj/ Usuñ u¿ytkownika");
		MenuItem menuItem15 = new MenuItem("Nadaj uprawnienia");
		menuItem15.setOnAction(e -> {
			PopUps.nadajuprawnienia(primaryStage, serverConnector);
		});
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
		Text oo;
		switch(user.getUprawnienia())
		{
		case 0:
			oo=new Text("Zalogowano jako ADMINISTATOR: "+user.getImie()+" "+user.getNazwisko());
		break;
		case 1:
			oo=new Text("Zalogowano jako: KIEROWNIK: "+user.getImie()+" "+user.getNazwisko());
		break;	
		case 2:oo=new Text("Zalogowano jako: PRACOWNIK MAGAZYNU: "+user.getImie()+" "+user.getNazwisko());
			menuItem17.setDisable(true);
			menuItem18.setDisable(true);
			menuItem14.setDisable(true);
			menuItem15.setDisable(true);
		break;
		case 3:oo=new Text("Zalogowano jako: STARSZY PRACOWNIK: "+user.getImie()+" "+user.getNazwisko());
		menuItem2.setDisable(true);
		menuItem3.setDisable(true);
		menuItem7.setDisable(true);
		menuItem14.setDisable(true);
		menuItem15.setDisable(true);
		break;
		case 4:oo=new Text("Zalogowano jako: M£ODSZY PRACOWNIK: "+user.getImie()+" "+user.getNazwisko());
		menuItem17.setDisable(true);
		menuItem14.setDisable(true);
		menuItem15.setDisable(true);
		menuItem2.setDisable(true);
		menuItem3.setDisable(true);
		menuItem7.setDisable(true);
		menuItem4.setDisable(true);
		menuItem5.setDisable(true);
		menuItem9.setDisable(true);		
		break;
		case 5:oo=new Text("Zalogowano jako: STA¯YSTA: "+user.getImie()+" "+user.getNazwisko());
		menuItem17.setDisable(true);
		menuItem18.setDisable(true);
		menuItem14.setDisable(true);
		menuItem15.setDisable(true);
		menuItem2.setDisable(true);
		menuItem3.setDisable(true);
		menuItem7.setDisable(true);
		menuItem4.setDisable(true);
		menuItem5.setDisable(true);
		menuItem9.setDisable(true);		
		break;
		default:oo=new Text("Zalogowano jako: UNDEFINED: "+user.getImie()+" "+user.getNazwisko());
		}
		oo.setStyle("-fx-font-size: 15pt;");
		oo.setFill(javafx.scene.paint.Color.NAVY);
		vb.getChildren().addAll(oo);
		root.getChildren().add(vb);
		root.getChildren().add(vbox);
	}
	
}
