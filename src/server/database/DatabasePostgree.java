package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import Communication.Data.Produkt;

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
                 //Logger.getLogger(DatabasePostgree.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return con;
    }

    private ResultSet executeQuery(Statement s, String sql) {
		try {
			return s.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return null;
	}

	private int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return -1;
	}
    
	public void init() {
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
 public void init2() {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO Produkty VALUES ( 0, '2PRO1DUKT0', 'Produkt Pierwszy Testowy Duży EXP', 'kg', 200.20);");
            st.close();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Produkty VALUES ( 1, '2PRO2DUKT2', 'Produkt Drugi Testowy Mały Lock', 'cm', 1233);");
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
            st.executeUpdate("INSERT INTO Maszyny VALUES ( 0, 'MB1', 380, 'Produkt Drugi Testowy Mały Lock');");
            st.close();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Maszyny VALUES ( 1, 'KRT2', 1200, 'Produkt Drugi Testowy Mały Lock');");
            st.close();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Maszyny VALUES ( 2, 'KRT3', 540, 'Alternat. EXP Produkt Duży');");
            st.close();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Maszyny VALUES ( 3, 'KRT4', 600, 'Alternat. EXP Produkt Dużyy');");
            st.close();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Maszyny VALUES ( 4, 'WMB', 100,  'Produkt Pierwszy Testowy Duży EXP');");
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static DatabasePostgree getInstance() {
        return postgree;
    }

//    public ArrayList<Produkt> readProdukty() {
//        System.out.println("DatabasePosgre - > readProdukty NIE DZIAĹ�A!");
//        ArrayList<Produkt> a = new ArrayList<Produkt>();
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
//        return a;
//    }
    
    public ArrayList<Produkt> readProdukty()
    {
	       ArrayList<Produkt> a = new ArrayList<Produkt>();
	       try {
	            Statement st = con.createStatement();	            
	            ResultSet r = st.executeQuery("SELECT * FROM Produkty");
	            while(r.next())
	            {
	                a.add(new Produkt(r.getInt("id"), r.getString("index"), r.getString("nazwa"), r.getString("jm"), r.getDouble("ilosc")));
	            }
	            st.close();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	        System.out.println(a.toString());
	        return a;
    }

    public void addProduct(Produkt p) {
		try {
			System.out.println("dodaje produkt");
			Statement st = con.createStatement();
			ResultSet r = executeQuery(st, "Select MAX(id) from produkty;");

			int id = 0;
			if (r.next()) {
				id = ((int) r.getObject(1) + 1);
			}
			executeUpdate(st, "INSERT INTO produkty VALUES(" + id + ", '" + p.getIndex() + "', '" + p.getNazwa() + "', '"
					+ p.getJednostka() + "', '" + p.getIlosc() + "');");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

    public void uptadeProduct(Produkt p) {
		try {
			System.out.println("upuje produkt");
			Statement st = con.createStatement();
			ResultSet r = executeQuery(st, "Select ilosc from produkty WHERE id="+p.getId()+";");
			double ilosc = 0;
			if (r.next()) {
				ilosc = ((double) r.getObject(1));
			}
			System.out.println("Ilosc:"+ilosc+" pilosc: "+p.getIlosc());
			executeUpdate(st, "UPDATE produkty SET ilosc="+(ilosc-p.getIlosc())+" WHERE id="+p.getId()+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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