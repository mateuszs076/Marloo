package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import users.User;

public class DatabsaeMySQL {
	static Connection con;
	
	public DatabsaeMySQL()
	{
		initDB();
	}
	
	public static boolean checkDriver(String driver) {
		// LADOWANIE STEROWNIKA
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance();
			return true;
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			return false;
		}
	}
	
	/**
	 * Metoda sluzy do nawiazania polaczenia z baza danych
	 * 
	 * @param adress - adres bazy danych
	 * @param dataBaseName - nazwa bazy
	 * @param userName - login do bazy
	 * @param password - haslo do bazy
	 * @return - polaczenie z baza
	 */
	public static Connection connectToDatabase(String kindOfDatabase, String adress,
			String dataBaseName, String userName, String password) {
		System.out.print("\nLaczenie z baza danych:");
		String baza = kindOfDatabase + adress + "/" + dataBaseName;
		// objasnienie opisu bazy:
		// jdbc: - mechanizm laczenia z baza (moze byc inny, np. odbc)
		// mysql: - rodzaj bazy
		// adress - adres serwera z baza (moze byc tez z nazwy)
		// dataBaseName - nazwa bazy
		java.sql.Connection connection = null;
		try {
			connection = DriverManager.getConnection(baza, userName, password);
		} catch (SQLException e) {
			System.out.println("Blad przy polaczeniu z baza danych!");
			System.exit(1);
		}
		return connection;
	}
	/**
	 * Metoda sluzy do polaczenia z MySQL bez wybierania konkretnej bazy
	 * @return referencja do uchwytu bazy danych
	 */
	public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		try {
			conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
					connectionProps);
		} catch (SQLException e) {
			System.out.println("Blad polaczenia z baza danych! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(2);
		}
		System.out.println("Polaczenie z baza danych: ... OK");
		return conn;
	}
	
	/**
	 * tworzenie obiektu Statement przesylajacego zapytania do bazy connection
	 * 
	 * @param connection - polaczenie z baza
	 * @return obiekt Statement przesylajacy zapytania do bazy
	 */
	static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Blad createStatement! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(3);
		}
		return null;
	}

	/**
	 * Zamykanie polaczenia z baza danych
	 * 
	 * @param connection - polaczenie z baza
	 * @param s - obiekt przesylajacy zapytanie do bazy
	 */
	private static void closeConnection(Connection connection, Statement s) {
		System.out.print("\nZamykanie polaczenia z baza:");
		try {
			s.close();
			connection.close();
		} catch (SQLException e) {
			System.out
					.println("Blad przy zamykaniu polaczenia z baza! " + e.getMessage() + ": " + e.getErrorCode());;
			System.exit(4);
		}
		System.out.print(" zamkniecie OK");
	}

	/**
	 * Wykonanie kwerendy i przeslanie wynikow do obiektu ResultSet
	 * 
	 * @param s - Statement
	 * @param sql - zapytanie
	 * @return wynik
	 */
	private static ResultSet executeQuery(Statement s, String sql) {
		try {
			return s.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return null;
	}
	private static int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return -1;
	}
	
	/**
	 * Wyswietla dane uzyskane zapytaniem select
	 * @param r - wynik zapytania
	 */
	private static void printDataFromQuery(ResultSet r) {
		ResultSetMetaData rsmd;
		try {
			rsmd = r.getMetaData();
			int numcols = rsmd.getColumnCount(); // pobieranie liczby kolumn
			// wyswietlanie nazw kolumn:
			for (int i = 1; i <= numcols; i++) {
				System.out.print("\t" + rsmd.getColumnLabel(i) + "\t|");
			}
			System.out
					.print("\n____________________________________________________________________________\n");
			/**
			 * r.next() - przejecie do kolejnego rekordu (wiersza) otrzymanych wynikow
			 */
			// wyswietlanie kolejnych rekordow:
			while (r.next()) {
				for (int i = 1; i <= numcols; i++) {
					Object obj = r.getObject(i);
					if (obj != null)
						System.out.print("\t" + obj.toString() + "\t|");
					else
						System.out.print("\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Blad odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}
	}
	/**
	 * Metoda pobiera dane na podstawie nazwy kolumny
	 */
	public static void sqlGetDataByName(ResultSet r) {
		System.out.println("Pobieranie danych z wykorzystaniem nazw kolumn");
		try {
			ResultSetMetaData rsmd = r.getMetaData();
			int numcols = rsmd.getColumnCount();
			// Tytul tabeli z etykietami kolumn zestawow wynikow
			for (int i = 1; i <= numcols; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "\t|\t");
			}
			System.out
			.print("\n____________________________________________________________________________\n");
			while (r.next()) {
				int size = r.getMetaData().getColumnCount();
				for(int i = 1; i <= size; i++){
					switch(r.getMetaData().getColumnTypeName(i)){
					case "INT":
						System.out.print(r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					case "DATE":
						System.out.print(r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					case "VARCHAR":
						System.out.print(r.getString(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					default:
						System.out.print(r.getMetaData().getColumnTypeName(i));
					}
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Blad odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}
	}

	public static void addUser(User u)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from uzytkownicy_;");
			r.next();
			int id = ((int)r.getObject(1)+1);
			executeUpdate(st, "INSERT INTO users VALUES("+id+", '"+u.getLogin()+"', '"+u.getPassword()+"', '"+u.getName()+"', '"+u.getSurname()+","+u.getLevel()+");");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int login(String login, String pass)
	{
		
		System.out.println("Zalogowano");
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select id,login,haslo,name,surname,level from users where login='"+login+"' and haslo='"+pass+"';");
			r.next();
			User u = new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getInt(6));
			st.close();
			return u.getId();
		} catch (SQLException e) {
			System.out.println("====\nBlad logowania " + login + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return -1;
		}
	}
	public static ArrayList<User> pobierzUserow() {
		Statement st = createStatement(con);
		ResultSet r = executeQuery(st, "SELECT * FROM uzytkownicy_;");
		ArrayList<User> listeczka = new ArrayList<User>();
		try {
			while (r.next())
				listeczka.add(new User(r.getInt("id"), r.getString("login"), r.getString("haslo"), r.getString("imie"),
						r.getString("nazwisko"), r.getInt("uprawnienia")));
			st.close();
			System.out.println("Userzy pobrane");
			return listeczka;

		} catch (SQLException e) {
			System.out.println("Nie mogłem pobrać userow, ponieważ:");
			e.printStackTrace();
		}
		return listeczka;
	}
	public static void initDB() {
		if (checkDriver("com.mysql.jdbc.Driver"))
			System.out.println(" ... OK");
		else
			System.exit(1);
		// 2 sposďż˝b poďż˝ďż˝czenia
		Connection con = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
		Statement st = createStatement(con);
		// prďż˝ba wybrania bazy
		if (executeUpdate(st, "USE nowaBaza;") == 0)
			System.out.println("Baza wybrana");
		else {
			System.out.println("Baza nie istnieje! Tworzymy baza: ");
			if (executeUpdate(st, "create Database nowaBaza;") == 1)
				System.out.println("Baza utworzona");
			else
				System.out.println("Baza nieutworzona!");
			if (executeUpdate(st, "USE nowaBaza;") == 0)
				System.out.println("Baza wybrana");
			else
				System.out.println("Baza niewybrana!");
		}
		if (executeUpdate(st,
				"CREATE TABLE uzytkownicy_ ( id INT NOT NULL, login VARCHAR(50) NOT NULL, haslo VARCHAR(50) NOT NULL, imie VARCHAR(50) NOT NULL, nazwisko VARCHAR(50) NOT NULL, uprawnienia INT NOT NULL, PRIMARY KEY (id) );") == 0)
			System.out.println("Tabela utworzona");
		else
			System.out.println("Tabela nie utworzona!");
		String sql = "INSERT INTO uzytkownicy_ VALUES(1, 'admin', 'admin', 'adam', 'admin', 0);";
		executeUpdate(st, sql);
		sql = "Select * from uzytkownicy_;";
		printDataFromQuery(executeQuery(st, sql));
		//closeConnection(con, st);
	
	}

}
