package application;

import application.connections.ServerConnector;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.Server;

public class WelcomeClass {

	private ServerConnector serverConnector;
	private Stage primaryStage;

	public WelcomeClass(Stage primaryStage, ServerConnector serverConnector) {
		this.primaryStage = primaryStage;
		this.serverConnector = serverConnector;
	}

	public void goodMorning(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		ImageView logo=new ImageView(new Image("img/title.png"));
		//ImageView logo=new ImageView(new Image("logo.png"));
		logo.setFitWidth(700);
		logo.setFitHeight(130);
		logo.setLayoutX(250);
		logo.setLayoutY(235);
		logo.setOpacity(0);
		
		ImageView solutions=new ImageView(new Image("img/solutions.png"));
		//ImageView solutions=new ImageView(new Image("logo.png"));
		solutions.setFitWidth(400);
		solutions.setFitHeight(50);
		solutions.setLayoutX(250);
		solutions.setLayoutY(200);
		solutions.setOpacity(0);
		
		ImageView andyou=new ImageView(new Image("img/andyou.png"));
		//ImageView andyou=new ImageView(new Image("logo.png"));
		andyou.setFitWidth(350);
		andyou.setFitHeight(50);
		andyou.setLayoutX(600);
		andyou.setLayoutY(350);
		andyou.setOpacity(0);
		
		FadeTransition ft = new FadeTransition(Duration.millis(500), logo);
	    ft.setFromValue(0);
	    ft.setToValue(1);
	    ft.play();
	    ft.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
            	FadeTransition ft = new FadeTransition(Duration.millis(1000), solutions);
        	    ft.setFromValue(0);
        	    ft.setToValue(1);
        	    ft.play();
        	    ft.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent arg0) {
                    	FadeTransition ft = new FadeTransition(Duration.millis(1000), andyou);
                	    ft.setFromValue(0);
                	    ft.setToValue(1);
                	    ft.play();
                	    ft.setOnFinished(new EventHandler<ActionEvent>(){

                            @Override
                            public void handle(ActionEvent arg0) {
                            	primaryStage.close();
                            	root.getChildren().clear();
                            	MainMenu.menu(primaryStage.getScene(), serverConnector);
                            }
                        });
                    }
                });
            }
        });
	    
		
		primaryStage.getScene().setRoot(root);
		root.getChildren().add(logo);
		root.getChildren().add(solutions);
		root.getChildren().add(andyou);
		
		primaryStage.show();
	}
}
