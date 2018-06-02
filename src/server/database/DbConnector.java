package server.database;

import java.sql.*;
import java.util.Properties;

public class DbConnector {
    private static DbConnector dbConnector = new DbConnector();
    private Statement st;
    private Connection con;

    public static DbConnector getInstance() {
        return dbConnector;
    }
        public static boolean checkDriver(String driver) {
            System.out.print("Sprawdzanie sterownika:");
            try {
                Class.forName(driver).newInstance();
                return true;
            } catch (Exception e) {
                System.out.println("Blad przy ladowaniu sterownika bazy!");
                return false;
            }
        }

    public static Connection connectToDatabase(String kindOfDatabase, String adress,
                                               String dataBaseName, String userName, String password) {
        System.out.print("\nLaczenie z baz¹ danych:");
        String baza = kindOfDatabase + adress + "/" + dataBaseName;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(baza, userName, password);
        } catch (SQLException e) {
            System.out.println("Blad przy po³¹czeniu z baz¹ danych!");
            System.exit(1);
        }
        return connection;
    }

    public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try {
            conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
            System.out.println("B³¹d po³¹czenia z baz¹ danych! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(2);
        }
        System.out.println("Po³¹czenie z baz¹ danych: ... OK");
        return conn;
    }

    public Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("B³¹d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }

    private static void closeConnection(Connection connection, Statement s) {
        System.out.print("\nZamykanie polaczenia z baz¹:");
        try {
            s.close();
            connection.close();
        } catch (SQLException e) {
            System.out
                    .println("Bl¹d przy zamykaniu pol¹czenia z baz¹! " + e.getMessage() + ": " + e.getErrorCode());;
            System.exit(4);
        }
        System.out.print(" zamkniêcie OK");
    }

    public ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return null;
    }
    public static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return -1;
    }

    private DbConnector(){
        if (checkDriver("com.mysql.jdbc.Driver"))
            System.out.println(" ... OK");
        else
            System.exit(1);
        con = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
        st = createStatement(con);
        if (executeUpdate(st, "USE test;") == 0)
            System.out.println("Baza wybrana");
        else {
            System.out.println("Baza nie istnieje! Tworzymy bazê: ");
            if (executeUpdate(st, "create Database test;") == 1)
                System.out.println("Baza utworzona");
            else
                System.out.println("Baza nieutworzona!");
            if (executeUpdate(st, "USE test;") == 0)
                System.out.println("Baza wybrana");
            else
                System.out.println("Baza niewybrana!");
        }

        if (executeUpdate(st,
                "CREATE TABLE pytania (id INT NOT NULL AUTO_INCREMENT, question TEXT(200) NOT NULL, answer_a TEXT(200), answer_b TEXT(200), answer_c TEXT(200), answer_d TEXT(200), correct TEXT(200), PRIMARY KEY (id) );") == 0);

        ResultSet rs = executeQuery(st, "SELECT * from pytania");
        int counter = 0;

        try {
            while(true){
                if(rs.next()){
                    counter++;
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(counter == 5){
            System.out.println("Wszystkie pytania znajdują się już w bazie danych");
        } else {
            addQuestionsToDb(st);
        }

//        closeConnection(con, st);
    }

    private void addQuestionsToDb(Statement st){
        executeUpdate(st, "INSERT INTO pytania VALUES(1, 'Jaką funkcją można zliczyć ilość wierszy w zapytaniu SQL?', 'ADD', 'SUM', 'COUNT', 'PLUS', 'COUNT');");
        executeUpdate(st, "INSERT INTO pytania VALUES(2, 'Do czego służy słowo kluczowe continue', 'Kończy działanie pętli', 'Zatrzymuje pętlę na pewien czas', 'Przechodzi do następnej instrukcji warunkowej if', 'Przechodzi do nowej iteracji w pętli for', 'Przechodzi do nowej iteracji w pętli for');");
        executeUpdate(st, "INSERT INTO pytania VALUES(3, 'Wynikiem dodawania 2+2 jest: ', '4', '3', '2', '1', '4');");
        executeUpdate(st, "INSERT INTO pytania VALUES(4, 'Wynikiem mnożenia 3*33 jest: ', '66', '99', '36', '11', '99');");
        executeUpdate(st, "INSERT INTO pytania VALUES(5, 'Wynikiem dzielenia 15/5 jest: ', '5', '3', '4', '10', '3');");
    }

    public Statement getSt() {
        return st;
    }
}
