package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import Data.Produkt;

import javax.xml.crypto.Data;

public class DatabasePostgree {

    private static final DatabasePostgree postgree = new DatabasePostgree();
    private Connection con;
    private static String address = "jdbc:postgresql://127.0.0.1:5432";

    private DatabasePostgree() {
    }

    public Connection polacz() {
        Properties p = new Properties();
        p.put("user", "postgres");
        p.put("password", "postgres");
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(address + "/postgres", p);
            System.out.println("Polaczono z baza Postrge");
        } catch (Exception e) {

        }
        return con;
    }

    public void init() {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE Produkty( id int PRIMARY KEY, index varchar(255) NOT NULL, nazwa varchar(255) NOT NULL, jm varchar(15) NOT NULL, ilosc double precision NOT NULL);");
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void init2() {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO Produkty VALUES ( 0, '2PRO1DUKT0', 'Produkt Pierwszy Testowy Duży EXP', 'kg', 200.20);");
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static DatabasePostgree getInstance() {
        return postgree;
    }

    public ArrayList<Produkt> readProdukty() {
        System.out.println("DatabasePosgre - > readProdukty NIE DZIAŁA!");
        ArrayList<Produkt> a = new ArrayList<Produkt>();
	       /* try {
	            Statement st = con.createStatement();
	            
	            ResultSet r = st.executeQuery("SELECT * FROM Produkty");
	            while(r.next())
	            {
	                a.add(new Produkt(r.getString("index"), r.getString("nazwa"), r.getString("jm"), r.getDouble("ilosc")));
	            }
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        System.out.println(a.toString());*/
        return a;
    }

    public void maininit() {
        polacz();
        init();
        init2();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        DatabasePostgree.address = address;
    }
}