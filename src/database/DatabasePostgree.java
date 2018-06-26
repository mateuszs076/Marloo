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

import Data.Maszyna;
import Data.Produkt;

public class DatabasePostgree {
	
	  private static Connection con;
	    private static String address = "jdbc:postgresql://127.0.0.1:5432";

	    public static String getAddress() {
	        return address;
	    }

	    public static void setAddress(String address) {
	    	DatabasePostgree.address = address;
	    }

	public static Connection polacz() {
        Properties p = new Properties();
        p.put("user", "postgres");
        p.put("password", "postgres");
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(address+"/serwer", p);
            System.out.println("Polaczono z baza Postrge");
        } catch (Exception e) {
            try {
                System.out.println(e);
                con = DriverManager.getConnection(address+"/postgres", p);
                Statement statement = con.createStatement();
                statement.execute("CREATE DATABASE serwer");
                con.close();
                con = DriverManager.getConnection(address+"/serwer", p);
            } catch (SQLException ex) {
                Logger.getLogger(DatabasePostgree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
	 public static void init() {
	        try {
	            Statement st = con.createStatement();
	            st.executeUpdate("CREATE TABLE Produkty( id int PRIMARY KEY, index varchar(255) NOT NULL, nazwa varchar(255) NOT NULL, jm varchar(15) NOT NULL, ilosc double precision NOT NULL);");
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        try {
	            Statement st = con.createStatement();
	            st.executeUpdate("CREATE TABLE Maszyny( id int PRIMARY KEY, nazwa varchar(255) NOT NULL, czasprocesu integer NOT NULL, produkowane varchar(300) NOT NULL);");
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	 public static void init2() {
	        try {
	            Statement st = con.createStatement();
	            st.executeUpdate("INSERT INTO Produkty VALUES ( 0, '2PRO1DUKT0', 'Produkt Pierwszy Testowy Du¿y EXP', 'kg', 200.20);");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Produkty VALUES ( 1, '2PRO2DUKT2', 'Produkt Drugi Testowy Ma³y Lock', 'cm', 1233);");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Produkty VALUES ( 2, '2PRO3DUKT3', 'Trzeci Produkt Zamiennik Zielony Metalik', 'szt', 3);");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Produkty VALUES ( 3, '2PRO4DUKT4', 'Kolejny Produkt EXP', 'szt', 42);");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Produkty VALUES ( 4, '2PRO5DUKT5', 'Alternat. EXP Produkt Du¿y', 'cm', 42.22);");
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        try {
	            Statement st = con.createStatement();
	            st.executeUpdate("INSERT INTO Maszyny VALUES ( 0, 'MB1', 380, 'Produkt Drugi Testowy Ma³y Lock');");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Maszyny VALUES ( 1, 'KRT2', 1200, 'Produkt Drugi Testowy Ma³y Lock');");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Maszyny VALUES ( 2, 'KRT3', 540, 'Alternat. EXP Produkt Du¿y');");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Maszyny VALUES ( 3, 'KRT4', 600, 'Alternat. EXP Produkt Du¿y');");
	            st.close();
	            st = con.createStatement();
	            st.executeUpdate("INSERT INTO Maszyny VALUES ( 4, 'WMB', 100,  'Produkt Pierwszy Testowy Du¿y EXP');");
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	 
	 public static ArrayList<Produkt> readProdukty()
	    {
		System.out.println("DatabasePosgre - > readProdukty NIE DZIA£A!");
	       ArrayList<Produkt> a = new ArrayList<Produkt>();
	       try {
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
	        System.out.println(a.toString());
	        return a;
	    }
	 public static ArrayList<Maszyna> readMaszyna()
	    {
		System.out.println("DatabasePosgre - > readMaszyny NIE DZIA£A!");
	       ArrayList<Maszyna> a = new ArrayList<Maszyna>();
	       try {
	            Statement st = con.createStatement();
	            
	            ResultSet r = st.executeQuery("SELECT * FROM Maszyny");
	            while(r.next())
	            {
	                a.add(new Maszyna(r.getString("nazwa"), r.getInt("czasprocesu"), r.getString("produkowane")));
	            }
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        System.out.println(a.toString());
	        return a;
	    }
	 public static void maininit()
	 {
		 polacz();
		 init();
		 init2();
	 }
}