package Communication.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import Communication.Communication;
import application.MainWindow;
import application.connections.ServerConnector;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
				this.uprawnienia = new SimpleStringProperty("M³odszy Pracownik Produkcji");
				break;
			case 5:
				this.uprawnienia = new SimpleStringProperty("Sta¿ysta / Nowy Pracownik");
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
	
	public static class Customer implements Serializable{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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

	
	public static class Product implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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

	public static class Machine implements Serializable{
		
		/**
		 * 
		 */
		private static long serialVersionUID = 1L;
		private SimpleStringProperty nazwa;
		private SimpleIntegerProperty czasprocesu;
		private SimpleStringProperty produkowane;
		
		public Machine(Maszyna m) {
			super();
			this.nazwa = new SimpleStringProperty(m.getNazwa());
			this.czasprocesu = new SimpleIntegerProperty(m.getCzasprocesu());
			this.produkowane = new SimpleStringProperty(m.getProdukowane1());
		
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public static void setSerialversionuid(long serialversionuid) {
			serialVersionUID = serialversionuid;
		}

		public String getNazwa() {
			return nazwa.get();
		}

		public void setNazwa(SimpleStringProperty nazwa) {
			this.nazwa = nazwa;
		}

		public Integer getCzasprocesu() {
			return czasprocesu.get();
		}

		public void setCzasprocesu(SimpleIntegerProperty czasprocesu) {
			this.czasprocesu = czasprocesu;
		}

		public String getProdukowane() {
			return produkowane.get();
		}

		public void setProdukowane(SimpleStringProperty produkowane) {
			this.produkowane = produkowane;
		}
	
		

	
	}
	

	public static TableView zawartosc(ServerConnector sv) {
		ArrayList<Produkt> produkty=new ArrayList<Produkt>();
		ArrayList<Product> products=new ArrayList<Product>();
		
		produkty=getProdukty(sv); //tutaj ma byæ dodawanie z bazy danych
		
		//next 10 lines to remove
		/*Produkt u1 = new Produkt("4COSTAM2COSTAM3", "Dzbanek na wodê", "szt", 1);
		Produkt u2 = new Produkt("4COSTAM2COSTAM3", "M¹ka", "kg", 1200);
		Produkt u3 = new Produkt("4COSTAM2COSTAM3", "Taœma malarska du¿a", "m", 12.4);
		Produkt u4 = new Produkt("4COSTAM2COSTAM3", "Parapet zielony", "szt", 1);
		Produkt u5 = new Produkt("4COSTAM2COSTAM3", "Wungiel z œlunska", "kg", 11500.200);
		produkty.add(u1);
		produkty.add(u2);
		produkty.add(u3);
		produkty.add(u4);
		produkty.add(u5);*/
		
		
		for(int i=0; i<produkty.size(); i++)
		{
			products.add(new Product(produkty.get(i)));
		}
		
		final ObservableList<Product> data = FXCollections.observableArrayList();
		data.addAll(products);

		
		TableView zawartosc = new TableView();
		zawartosc.setEditable(true);
		zawartosc.setItems(data);

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
		
		index.setCellValueFactory(new PropertyValueFactory<Product, String>("index"));
		name.setCellValueFactory(new PropertyValueFactory<Product, String>("nazwa"));
		jm.setCellValueFactory(new PropertyValueFactory<Product, String>("jednostka"));
		ilosc.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ilosc"));

		nazwa.getColumns().addAll(index, name, jm, ilosc);
		zawartosc.getColumns().addAll(nazwa);
		return zawartosc;
	}

	public static TableView pracownicy(ServerConnector sv) {
		//getUserzy();
		ArrayList<User> userzy=new ArrayList<User>();
		ArrayList<Person> people=new ArrayList<Person>();
		
		userzy=getUserzy(sv); //tutaj ma byæ dodawanie z bazy danych
		
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
		TableColumn imie = new TableColumn("Imiê");
		imie.setPrefWidth(300);
		TableColumn nazwisko = new TableColumn("Nazwisko");
		nazwisko.setPrefWidth(300);
		TableColumn login = new TableColumn("Nazwa u¿ytkownika w systemie");
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
		
		odbiorcy=getOdbiorcy(); //tutaj ma byæ dodawanie z bazy danych
		
		//next 10 lines to remove
		/*Odbiorca u1 = new Odbiorca ("IBM ENTERTEJMENT", "Stany Zjednoczone", "Kalafiornia", "Ul. Jana Pawa³a 2 12");
		Odbiorca u2 = new Odbiorca ("Google", "Stany Zjednoczone", "Kalafiornia", "Ul. Zdziska 12");
		Odbiorca u3 = new Odbiorca ("Kokodzambo i do przodu", "Kenia", "Kenia City", "Ul. Jana Pawa³a 2 12");
		Odbiorca u4 = new Odbiorca ("Rurex", "Polska", "Warszawa", "ul d³uga 222222");
		Odbiorca u5 = new Odbiorca ("Maspex GMW", "Polska", "Wadowice", "Chopina 10");
		odbiorcy.add(u1);
		odbiorcy.add(u2);
		odbiorcy.add(u3);
		odbiorcy.add(u4);
		odbiorcy.add(u5);*/
		
		
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
		ArrayList<Maszyna> maszynny=new ArrayList<Maszyna>();
		ArrayList<Machine> machines=new ArrayList<Machine>();
		
		maszynny=getMaszyny(); //tutaj ma byæ dodawanie z bazy danych
		
		//next 10 lines to remove
		/*Odbiorca u1 = new Odbiorca ("IBM ENTERTEJMENT", "Stany Zjednoczone", "Kalafiornia", "Ul. Jana Pawa³a 2 12");
		Odbiorca u2 = new Odbiorca ("Google", "Stany Zjednoczone", "Kalafiornia", "Ul. Zdziska 12");
		Odbiorca u3 = new Odbiorca ("Kokodzambo i do przodu", "Kenia", "Kenia City", "Ul. Jana Pawa³a 2 12");
		Odbiorca u4 = new Odbiorca ("Rurex", "Polska", "Warszawa", "ul d³uga 222222");
		Odbiorca u5 = new Odbiorca ("Maspex GMW", "Polska", "Wadowice", "Chopina 10");
		odbiorcy.add(u1);
		odbiorcy.add(u2);
		odbiorcy.add(u3);
		odbiorcy.add(u4);
		odbiorcy.add(u5);*/
		
		
		for(int i=0; i<maszynny.size(); i++)
		{
			machines.add(new Machine(maszynny.get(i)));
		}
		
		
		
		final ObservableList<Machine> data = FXCollections.observableArrayList();
		data.addAll(machines);
		maszyny.setItems(data);

		TableColumn nazwa = new TableColumn("MASZYNY");
		nazwa.setPrefWidth(1200);
		TableColumn imie = new TableColumn("Nazwa");
		imie.setPrefWidth(400);
		TableColumn nazwisko = new TableColumn("Czas procesu");
		nazwisko.setPrefWidth(400);
		TableColumn login = new TableColumn("Produkowane");
		login.setPrefWidth(400);
		
		imie.setCellValueFactory(new PropertyValueFactory<Machine, String>("nazwa"));
		nazwisko.setCellValueFactory(new PropertyValueFactory<Machine, String>("czasprocesu"));
		login.setCellValueFactory(new PropertyValueFactory<Machine, String>("produkowane"));

		nazwa.getColumns().addAll(imie, nazwisko, login);
		maszyny.getColumns().addAll(nazwa);
		return maszyny;
	}

	public static ArrayList<User> getUserzy(ServerConnector serverConnector) {
		System.out.println("Zaczynam getProdukty()");
        AtomicBoolean isAuth = new AtomicBoolean(false);
        Object answer = Communication.GET_PRODUKTY_FAILD;

     
            System.out.printf("new thread - get produkty");
        
                    try {
                        System.out.println(serverConnector.toString());
                        serverConnector.sendObject(new String("getuserzy"));

                        Object obj = serverConnector.receiveObject();
                        System.out.println("received Userzy?");
                        if (obj != Communication.GET_PRODUKTY_FAILD) {
                            System.out.println("getuem");
                            isAuth.set(true);
                            ArrayList<User> obj2 = (ArrayList<User>) obj;
							return obj2;
                            
                            //Platform.runLater(() -> new MainWindow(serverConnector).mainWindow(primaryStage, root, (User) obj));
                        } else {
                            System.out.println("cos nie tak z userem");
                            //todo okno z niepoprawnym loginem
                            isAuth.set(false);
                            return null;
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
////                        serverConnector = null;
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    } catch (ClassNotFoundException e) {
////                        e.printStackTrace();
////                    }
//                }).start();}
					
	}
    

	
	public static ArrayList<Odbiorca> getOdbiorcy() {
		try {
			int port = 1003;// port na sztywno
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "odbiorcy";// warunek na serwerze ServerThred.30

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			System.out.println("ArrayLista odbiorcy");
			ArrayList<Odbiorca> userzy = (ArrayList<Odbiorca>) objInputStream.readObject();// znowu
																					// castowanko
			System.out.println("Pobra³em odbiorców");
			socket.close();
			return userzy;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	public static ArrayList<Produkt> getProdukty(ServerConnector serverConnector) {
		System.out.println("Zaczynam getProdukty()");
        AtomicBoolean isAuth = new AtomicBoolean(false);
        Object answer = Communication.GET_PRODUKTY_FAILD;

     
            System.out.printf("new thread - get produkty");
        
                    try {
                        System.out.println(serverConnector.toString());
                        serverConnector.sendObject(new String("getprodukty"));

                        Object obj = serverConnector.receiveObject();
                        System.out.println("received Produkty?");
                        if (obj != Communication.GET_PRODUKTY_FAILD) {
                            System.out.println("getuem");
                            isAuth.set(true);
                            ArrayList<Produkt> obj2 = (ArrayList<Produkt>) obj;
							return obj2;
                            
                            //Platform.runLater(() -> new MainWindow(serverConnector).mainWindow(primaryStage, root, (User) obj));
                        } else {
                            System.out.println("cos nie tak z userem");
                            //todo okno z niepoprawnym loginem
                            isAuth.set(false);
                            return null;
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
////                        serverConnector = null;
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    } catch (ClassNotFoundException e) {
////                        e.printStackTrace();
////                    }
//                }).start();}
					
	}
    


        //DatabsaeMySQL.login(l.getText(), p.getText());
        //if(getUserzy(l.getText(), p.getText()))
//            try {
//                if (DatabsaeMySQL.login(l.getText(), p.getText()) >= 0)
//                    MainWindow.mainWindow(primaryStage, root, DatabsaeMySQL.login(l.getText(), p.getText()));
//                else
//                    System.out.println("coÅš poszÅ‚o nie tak");
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
   

		/*try {
			int port = 1003;// port na sztywno
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "produkty";// warunek na serwerze ServerThred.30

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			System.out.println("ArrayLista PRodukty");
			ArrayList<Produkt> produkty = (ArrayList<Produkt>) objInputStream.readObject();// znowu
																					// castowanko
			System.out.println("Pobra³em produkty");
			socket.close();
			return produkty;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}*/
	
	public static ArrayList<Maszyna> getMaszyny() {
		try {
			int port = 1003;// port na sztywno
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "maszyny";// warunek na serwerze ServerThred.30

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			System.out.println("ArrayLista Maszyna");
			ArrayList<Maszyna> produkty = (ArrayList<Maszyna>) objInputStream.readObject();// znowu
																					// castowanko
			System.out.println("Pobra³em Maszyna");
			socket.close();
			return produkty;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
