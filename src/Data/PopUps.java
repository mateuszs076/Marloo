package Data;

import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class PopUps {
	static Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

	static UnaryOperator<TextFormatter.Change> filter = c -> {
	    String text = c.getControlNewText();
	    if (validEditingState.matcher(text).matches()) {
	        return c ;
	    } else {
	        return null ;
	    }
	};

	static StringConverter<Double> converter = new StringConverter<Double>() {

	    @Override
	    public Double fromString(String s) {
	        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
	            return 0.0 ;
	        } else {
	            return Double.valueOf(s);
	        }
	    }


	    @Override
	    public String toString(Double d) {
	        return d.toString();
	    }
	};

	static TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
	
	
	
	public static void dodajDoMag(Stage primaryStage)
	{
		Label header=new Label("DODAWANIE DO MAGAZYNU");
		header.setAlignment(Pos.CENTER);
	
		
		Label h2=new Label("PODAJ NOWE DANE:");
		h2.setLayoutX(25);
		
		TextField ind=new TextField();
		ind.setPromptText("INDEX");
		ind.setAlignment(Pos.CENTER);
		ind.setLayoutX(25);
		ind.setPrefWidth(200);
		TextField nazwa=new TextField();
		nazwa.setPromptText("NAZWA TOWARU");
		nazwa.setAlignment(Pos.CENTER);
		nazwa.setPrefWidth(200);
		
		Label jj=new Label("JEDNOSTKA MIARY:");
		jj.setLayoutX(25);
		
		ChoiceBox jm = new ChoiceBox(FXCollections.observableArrayList("kg", "szt", "kpl", "m", "cm"));
		jm.setPrefWidth(200);
		jm.setLayoutX(25);
		
		
		Label jj2=new Label("ILOSC:");
		jj2.setLayoutX(250);
		
		TextField ilosc=new TextField();		
		ilosc.setTextFormatter(textFormatter);
		ilosc.setPromptText("ILOŒÆ");
		ilosc.setLayoutX(250);
		
		Button potwierdz=new Button("Potwierdz");
		potwierdz.setLayoutX(25);
		
		HBox hb1=new HBox(20);
		hb1.getChildren().addAll(ind, nazwa);
		
		VBox jedm=new VBox(20);
		jedm.getChildren().addAll(jj, jm);
		
		VBox iloo=new VBox(20);
		iloo.getChildren().addAll(jj2, ilosc);

		HBox hb2=new HBox(20);
		hb2.getChildren().addAll(jedm, iloo);
		//Produkt p=new Produkt(ind.getText(), nazwa.getText(), jm.getText(), ilosc.getText());
			
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(30);
        dialogVbox.getChildren().addAll(header,  h2,  hb1, hb2, potwierdz);
        Scene dialogScene = new Scene(dialogVbox, 500, 400) ;
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	public static void wydajNaProdukcje(Stage primaryStage)
	{
		Label header=new Label("Wydawanie na Produkcjê");
		header.setAlignment(Pos.CENTER);
		
		Label h1=new Label("WYBIERZ Z DOSTÊPNYCH:");
		h1.setLayoutX(25);
		h1.setPadding(Insets.EMPTY);
		
		ArrayList<Produkt> p=new ArrayList<Produkt>();
		p=Tabele.getProdukty();
		
		ChoiceBox index = new ChoiceBox(FXCollections.observableArrayList(p));
		index.setPrefWidth(450);
		index.setLayoutX(25);
		
		
		Label jj2=new Label("ILOSC:");
		jj2.setLayoutX(25);
		
		TextField ilosc=new TextField();		
		ilosc.setTextFormatter(textFormatter);
		ilosc.setPromptText("ILOŒÆ");
		ilosc.setLayoutX(25);
		
		Button potwierdz=new Button("Wydaj");
		potwierdz.setLayoutX(25);
		
		
		//Produkt p=new Produkt(ind.getText(), nazwa.getText(), jm.getText(), ilosc.getText());
			
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().addAll(header, h1, index, jj2, ilosc, potwierdz);
        Scene dialogScene = new Scene(dialogVbox, 400, 250) ;
        dialog.setScene(dialogScene);
        dialog.show();
	}
	public static void dodajMaszyne(Stage primaryStage)
	{
		Label header=new Label("Dodawanie Maszyny");
		header.setAlignment(Pos.CENTER);
		
		TextField nazwa=new TextField();
		nazwa.setPromptText("NAZWA MASZYNY");
		nazwa.setAlignment(Pos.CENTER);
		nazwa.setPrefWidth(200);
		
		Label jj2=new Label("CZAS PROCESU:");
		jj2.setLayoutX(25);
		
		TextField ilosc=new TextField();		
		ilosc.setTextFormatter(textFormatter);
		ilosc.setPromptText("ILOŒÆ");
		ilosc.setLayoutX(25);
		
		Button potwierdz=new Button("Dodaj");
		potwierdz.setLayoutX(25);
			
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().addAll(header, nazwa, jj2, ilosc, potwierdz);
        Scene dialogScene = new Scene(dialogVbox, 300, 200) ;
        dialog.setScene(dialogScene);
        dialog.show();
	}
}

