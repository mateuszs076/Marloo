package Data;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Tabele {
	public static TableView zawartosc()
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
	
	public static TableView pracownicy()
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
	
	public static TableView odbiorcy()
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
	
	public static TableView maszyny()
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
}
