package Communication.Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Tabele {
	public static class Person {
	    private final SimpleStringProperty firstName;
	    private final SimpleStringProperty lastName;
	    private final SimpleStringProperty login;
	    private final SimpleIntegerProperty uprawnienia;
	 
	    private Person(User u) {
	        this.firstName = new SimpleStringProperty(u.getImie());
	        this.lastName = new SimpleStringProperty(u.getNazwisko());
	        this.login = new SimpleStringProperty(u.getLogin());
	        this.uprawnienia = new SimpleIntegerProperty(u.getUprawnienia());
	    }
	 
	    public String getFirstName() {
	        return firstName.get();
	    }
	    public void setFirstName(String fName) {
	        firstName.set(fName);
	    }
	        
	    public String getLastName() {
	        return lastName.get();
	    }
	    public void setLastName(String fName) {
	        lastName.set(fName);
	    }

		public String getLogin() {
			return login.get();
		}

		public Integer getUprawnienia() {
			return uprawnienia.get();
		}
	    
	   
	        
	}
	public static TableView zawartosc()
	{
		TableView zawartosc = new TableView();
		zawartosc.setEditable(true);
		
		TableColumn nazwa = new TableColumn("ZAWARTOSC");
        nazwa.setPrefWidth(1200); 
        TableColumn index = new TableColumn("INDEX");
        index.setPrefWidth(300);
        TableColumn name = new TableColumn("Nazwa");
        name.setPrefWidth(300);
        TableColumn jm = new TableColumn("Jednostka miary");
        jm.setPrefWidth(300);
        TableColumn ilosc = new TableColumn("Ilosc");
        ilosc.setPrefWidth(300);
        
        nazwa.getColumns().addAll(index, name, jm, ilosc);
        zawartosc.getColumns().addAll(nazwa);
        return zawartosc;
	}
	
	public static TableView pracownicy()
	{
		User u1=new User(1, "Adam11", "Kowalski",  "Adam", "Kowalski", 1);
		User u2=new User(1, "Jan22", "Brzechwa",  "Jan", "Brzechwa", 1);
		User u3=new User(1, "Adam33", "Banc",  "Adam", "Banc", 1);
		//ArrayList<User> userzy=getUserzy();
		final ObservableList<Person> data = FXCollections.observableArrayList(
			    new Person(u1),
			    new Person(u2),
			    new Person(u3),
			    new Person(u1),
			    new Person(u2),
			    new Person(u3),
			    new Person(u1),
			    new Person(u2),
			    new Person(u3),
			    new Person(u1),
			    new Person(u2),
			    new Person(u3)			    
			);
		
		TableView pracownicy = new TableView();
		pracownicy.setEditable(true);
		 pracownicy.setItems(data);
		TableColumn nazwa = new TableColumn("PRACOWNICY");
        nazwa.setPrefWidth(1200);  
        TableColumn imie = new TableColumn("Imie");
        imie.setPrefWidth(300);
        TableColumn nazwisko = new TableColumn("Nazwisko");
        nazwisko.setPrefWidth(300);
        TableColumn login = new TableColumn("Nazwa uzytkownika w systemie");
        login.setPrefWidth(300);
        TableColumn uprawnienia = new TableColumn("Uprawnienia");
        uprawnienia.setPrefWidth(300);
        
        
        
      /*  imie.setCellValueFactory(c -> new SimpleStringProperty(new String(userzy.get(0).getImie())));
        nazwisko.setCellValueFactory(c -> new SimpleStringProperty(new String(userzy.get(0).getNazwisko())));
        imie.setCellValueFactory(c -> new SimpleStringProperty(new String(userzy.get(1).getImie())));
        nazwisko.setCellValueFactory(c -> new SimpleStringProperty(new String(userzy.get(1).getNazwisko())));
        imie.setCellValueFactory(c -> new SimpleStringProperty(new String(userzy.get(2).getImie())));
        nazwisko.setCellValueFactory(c -> new SimpleStringProperty(new String(userzy.get(2).getNazwisko())));*/
        imie.setCellValueFactory(
        	    new PropertyValueFactory<Person,String>("firstName")
        	);
        	nazwisko.setCellValueFactory(
        	    new PropertyValueFactory<Person,String>("lastName")
        	);
        	login.setCellValueFactory(
        	    new PropertyValueFactory<Person,String>("login")
        	);
        	uprawnienia.setCellValueFactory(
            	    new PropertyValueFactory<Person,Integer>("uprawnienia")
            	);
        
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
	public static ArrayList<User> getUserzy(){
		try {
			int port = 1003;//port na sztywno			
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));		
			
			String str = "userzy";//warunek na serwerze ServerThred.30
			
			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
            ArrayList<User> userzy = (ArrayList<User>) objInputStream.readObject();//znowu castowanko
			System.out.println("Pobralem userow");
			socket.close();
			return userzy;
		} catch (Exception e) {
			System.err.println(e);
			return null;
	}
	}
}
