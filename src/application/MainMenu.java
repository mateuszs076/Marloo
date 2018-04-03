package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainMenu {

	public static void menu(Scene scene)
	{
		BorderPane root=(BorderPane) scene.getRoot();
		root.getChildren().clear();
		root.getStyleClass().add("h");
		Stage primaryStage=new Stage();
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setTitle("Marloo");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		ImageView logo=new ImageView(new Image("title.png"));
		logo.setFitWidth(700);
		logo.setFitHeight(130);
		logo.setLayoutX(250);
		logo.setLayoutY(235);
		logo.setOpacity(1);
		
		ImageView solutions=new ImageView(new Image("solutions.png"));
		solutions.setFitWidth(400);
		solutions.setFitHeight(50);
		solutions.setLayoutX(250);
		solutions.setLayoutY(200);
		solutions.setOpacity(1);
		
		ImageView andyou=new ImageView(new Image("andyou.png"));
		andyou.setFitWidth(350);
		andyou.setFitHeight(50);
		andyou.setLayoutX(600);
		andyou.setLayoutY(350);
		andyou.setOpacity(1);
		
		root.getChildren().add(logo);
		root.getChildren().add(solutions);
		root.getChildren().add(andyou);
		
		
		
		Button enter=new Button("Enter");
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
		
		Button about=new Button("About application");
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
			      AboutWindow.about(primaryStage, root);
			    }
		});
		
		Button exit=new Button("Exit");
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
		    }
		});
		
		FadeTransition ft = new FadeTransition(Duration.millis(500), andyou);
	    ft.setFromValue(1);
	    ft.setToValue(0);
	    ft.play();
	    ft.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
            	FadeTransition ft = new FadeTransition(Duration.millis(500), solutions);
        	    ft.setFromValue(1);
        	    ft.setToValue(0);
        	    ft.play();
        	    ft.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent arg0) {
                    	  TranslateTransition tt = new TranslateTransition(Duration.millis(1000), logo);
                    	   tt.setByY(-220f);
                    	   tt.play();
                    	   tt.setOnFinished(new EventHandler<ActionEvent>(){

                               @Override
                               public void handle(ActionEvent arg0) {
                            	   root.getChildren().add(exit);
                            	   root.getChildren().add(about);
                            	   root.getChildren().add(enter);
                               }
                           });
                    }
                });
            }
        });
	    
	    
	}
}
