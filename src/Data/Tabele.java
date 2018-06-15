package Data;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tabele implements Serializable{
	
	public static class Person implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6114401368785397315L;
		private final SimpleStringProperty firstName;
		private final SimpleStringProperty lastName;
		private final SimpleStringProperty login;
		private final SimpleStringProperty uprawnienia;

		private Person(User u) {
			this.firstName = new SimpleStringProperty(u.getImie());
			this.lastName = new SimpleStringProperty(u.getNazwisko());
			this.login = new SimpleStringProperty(u.getLogin());
			switch (u.getUprawnienia()) {
			case 0:
				this.uprawnienia = new SimpleStringProperty("Administrator");
				break;
			case 1:
				this.uprawnienia = new SimpleStringProperty("Kierownik");
				break;
			case 2:
				this.uprawnienia = new SimpleStringProperty("Pracownik Magazynu");
				break;
			case 3:
				this.uprawnienia = new SimpleStringProperty("Starszy Pracownik Produkcji");
				break;
			case 4:
				this.uprawnienia = new SimpleStringProperty("Młodszy Pracownik Produkcji");
				break;
			case 5:
				this.uprawnienia = new SimpleStringProperty("Stażysta / Nowy Pracownik");
				break;
			default:
				this.uprawnienia = new SimpleStringProperty("Niezidentyfikowano");
				break;
			}
		}

		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", uprawnienia="
					+ uprawnienia + "]";
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

		public String getUprawnienia() {
			return uprawnienia.get();
		}

	}
	
	public static class Customer {
	
		private SimpleStringProperty name;
		private SimpleStringProperty country;
		private SimpleStringProperty city;
		private SimpleStringProperty address;
		
		public Customer(Odbiorca o) {
			super();
			this.name = new SimpleStringProperty(o.getNazwa());
			this.country = new SimpleStringProperty(o.getKraj());
			this.city = new SimpleStringProperty(o.getMiasto());
			this.address = new SimpleStringProperty(o.getAdres());
		}

		public String getName() {
			return name.get();
		}

		public String getCountry() {
			return country.get();
		}

		public String getCity() {
			return city.get();
		}

		public String getAddress() {
			return address.get();
		}

		
		public void setName(SimpleStringProperty name) {
			this.name = name;
		}

		public void setCountry(SimpleStringProperty country) {
			this.country = country;
		}

		public void setCity(SimpleStringProperty city) {
			this.city = city;
		}

		public void setAddress(SimpleStringProperty address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "Customer [name=" + name + ", country=" + country + ", city=" + city + ", address=" + address + "]";
		}


	
	}

	
	public static class Product {
		
		private SimpleStringProperty index;
		private SimpleStringProperty nazwa;
		private SimpleStringProperty jednostka;
		private SimpleDoubleProperty ilosc;
		
		
		public Product(Produkt p) {
			super();
			this.index = new SimpleStringProperty(p.getIndex());
			this.nazwa = new SimpleStringProperty(p.getNazwa());
			this.jednostka = new SimpleStringProperty(p.getJednostka());
			this.ilosc = new SimpleDoubleProperty(p.getIlosc());
		}


		public String getIndex() {
			return index.get();
		}


		public void setIndex(SimpleStringProperty index) {
			this.index = index;
		}


		public String getNazwa() {
			return nazwa.get();
		}


		@Override
		public String toString() {
			return "Product [index=" + index + ", nazwa=" + nazwa + ", jednostka=" + jednostka + ", ilosc=" + ilosc
					+ "]";
		}


		public void setNazwa(SimpleStringProperty nazwa) {
			this.nazwa = nazwa;
		}


		public String getJednostka() {
			return jednostka.get();
		}


		public void setJednostka(SimpleStringProperty jednostka) {
			this.jednostka = jednostka;
		}


		public double getIlosc() {
			return ilosc.get();
		}


		public void setIlosc(SimpleDoubleProperty ilosc) {
			this.ilosc = ilosc;
		}
		
		
		

	
	}
	

	public static TableView zawartosc() {
		ArrayList<Produkt> produkty=new ArrayList<Produkt>();
		ArrayList<Product> products=new ArrayList<Product>();
		
		// userzy=getUserzy(); tutaj ma być dodawanie z bazy danych
		
		//next 10 lines to remove
		Produkt u1 = new Produkt("4COSTAM2COSTAM3", "Dzbanek na wodę", "szt", 1);
		Produkt u2 = new Produkt("4COSTAM2COSTAM3", "Mąka", "kg", 1200);
		Produkt u3 = new Produkt("4COSTAM2COSTAM3", "Taśma malarska duża", "m", 12.4);
		Produkt u4 = new Produkt("4COSTAM2COSTAM3", "Parapet zielony", "szt", 1);
		Produkt u5 = new Produkt("4COSTAM2COSTAM3", "Wungiel z ślunska", "kg", 11500.200);
		produkty.add(u1);
		produkty.add(u2);
		produkty.add(u3);
		produkty.add(u4);
		produkty.add(u5);
		
		
		for(int i=0; i<produkty.size(); i++)
		{
			products.add(new Product(produkty.get(i)));
		}
		
		final ObservableList<Product> data = FXCollections.observableArrayList();
		data.addAll(products);

		
		TableView zawartosc = new TableView();
		zawartosc.setEditable(true);
		zawartosc.setItems(data);

		TableColumn nazwa = new TableColumn("ZAWARTOŚĆ");
		nazwa.setPrefWidth(1200);
		TableColumn index = new TableColumn("INDEX");
		index.setPrefWidth(300);
		TableColumn name = new TableColumn("Nazwa");
		name.setPrefWidth(300);
		TableColumn jm = new TableColumn("Jednostka miary");
		jm.setPrefWidth(300);
		TableColumn ilosc = new TableColumn("Ilo��");
		ilosc.setPrefWidth(300);
		
		index.setCellValueFactory(new PropertyValueFactory<Product, String>("index"));
		name.setCellValueFactory(new PropertyValueFactory<Product, String>("nazwa"));
		jm.setCellValueFactory(new PropertyValueFactory<Product, String>("jednostka"));
		ilosc.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ilosc"));

		nazwa.getColumns().addAll(index, name, jm, ilosc);
		zawartosc.getColumns().addAll(nazwa);
		return zawartosc;
	}

	public static TableView pracownicy() {
		//getUserzy();
		ArrayList<User> userzy=new ArrayList<User>();
		ArrayList<Person> people=new ArrayList<Person>();
		
		userzy=getUserzy(); //tutaj ma by� dodawanie z bazy danych
		
		//next 10 lines to remove
		/*User u1 = new User(1, "Adam11", "Kowalski", "Adam", "Kowalski", 0);
		User u2 = new User(1, "Jan22", "Brzechwa", "Jan", "Brzechwa", 1);
		User u3 = new User(1, "Adam33", "Banc", "Adam", "Banc", 2);
		User u4 = new User(1, "Adam33", "Adam33", "AdaAdam33Adam33m", "Adam33sad", 3);
		User u5 = new User(1, "Aasdfasdfdam33", "Baasdfasdfnc", "Adaasdfm", "Bancasdf", 4);
		userzy.add(u1);
		userzy.add(u2);
		userzy.add(u3);
		userzy.add(u4);
		userzy.add(u5);
		*/
		
		for(int i=0; i<userzy.size(); i++)
		{
			people.add(new Person(userzy.get(i)));
		}
		
		
		
		final ObservableList<Person> data = FXCollections.observableArrayList();
		data.addAll(people);

		TableView pracownicy = new TableView();
		pracownicy.setEditable(true);
		pracownicy.setItems(data);
		TableColumn nazwa = new TableColumn("PRACOWNICY");
		nazwa.setPrefWidth(1200);
		TableColumn imie = new TableColumn("Imi�");
		imie.setPrefWidth(300);
		TableColumn nazwisko = new TableColumn("Nazwisko");
		nazwisko.setPrefWidth(300);
		TableColumn login = new TableColumn("Nazwa u�ytkownika w systemie");
		login.setPrefWidth(300);
		TableColumn uprawnienia = new TableColumn("Uprawnienia");
		uprawnienia.setPrefWidth(300);

		
		imie.setCellValueFactory(new PropertyValueFactory<Product, String>("firstName"));
		nazwisko.setCellValueFactory(new PropertyValueFactory<Product, String>("lastName"));
		login.setCellValueFactory(new PropertyValueFactory<Product, String>("login"));
		uprawnienia.setCellValueFactory(new PropertyValueFactory<Product, Integer>("uprawnienia"));

		nazwa.getColumns().addAll(imie, nazwisko, login, uprawnienia);
		pracownicy.getColumns().addAll(nazwa);

		return pracownicy;
	}

	public static TableView odbiorcy() {
		
		ArrayList<Odbiorca> odbiorcy=new ArrayList<Odbiorca>();
		ArrayList<Customer> customers=new ArrayList<Customer>();
		
		// userzy=getUserzy(); tutaj ma by� dodawanie z bazy danych
		
		//next 10 lines to remove
		Odbiorca u1 = new Odbiorca ("IBM ENTERTEJMENT", "Stany Zjednoczone", "Kalafiornia", "Ul. Jana Pawa�a 2 12");
		Odbiorca u2 = new Odbiorca ("Google", "Stany Zjednoczone", "Kalafiornia", "Ul. Zdziska 12");
		Odbiorca u3 = new Odbiorca ("Kokodzambo i do przodu", "Kenia", "Kenia City", "Ul. Jana Pawa�a 2 12");
		Odbiorca u4 = new Odbiorca ("Rurex", "Polska", "Warszawa", "ul d�uga 222222");
		Odbiorca u5 = new Odbiorca ("Maspex GMW", "Polska", "Wadowice", "Chopina 10");
		odbiorcy.add(u1);
		odbiorcy.add(u2);
		odbiorcy.add(u3);
		odbiorcy.add(u4);
		odbiorcy.add(u5);
		
		
		for(int i=0; i<odbiorcy.size(); i++)
		{
			customers.add(new Customer(odbiorcy.get(i)));
		}
		
		
		
		final ObservableList<Customer> data = FXCollections.observableArrayList();
		data.addAll(customers);
		
		TableView odbiorcyy = new TableView();
		odbiorcyy.setEditable(true);
		odbiorcyy.setItems(data);

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
		
		imie.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		nazwisko.setCellValueFactory(new PropertyValueFactory<Customer, String>("country"));
		login.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
		uprawnienia.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));

		nazwa.getColumns().addAll(imie, nazwisko, login, uprawnienia);
		odbiorcyy.getColumns().addAll(nazwa);
		return odbiorcyy;
	}

	public static TableView maszyny() {
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

	public static ArrayList<User> getUserzy() {
		try {
			int port = 1003;// port na sztywno
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "userzy";// warunek na serwerze ServerThred.30

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			System.out.println("ArrayLista");
			ArrayList<User> userzy = (ArrayList<User>) objInputStream.readObject();// znowu
																					// castowanko
			System.out.println("Pobra�em user�w");
			socket.close();
			return userzy;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
