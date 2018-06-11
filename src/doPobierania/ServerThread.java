package doPobierania;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

import database.DatabsaeMySQL;


public class ServerThread extends Thread {
	Socket mySocket;

	/**
	 * Konstruktor klasy ServerThread
	 * 
	 * @param socket
	 *            Obiekt typu socket wspomagaj¹cy zdefiniowanie portu
	 */
	public ServerThread(Socket socket) {
		super();
		mySocket = socket;
	}

	public void run() {
		try {
			
			
			if (mySocket.getLocalPort() == 1003) {
				BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
				String str = in.readLine();
				System.out.println(mySocket.getInetAddress() + " : " + str);
				String[] data = str.split(",");
				OutputStream outputStream = mySocket.getOutputStream();
				ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
				if (data[0].equals("login")) {
					System.out.println("Login");
					DatabsaeMySQL.login(data[1], data[2]);					
				}
				if (data[0].equals("userzy")) {
					System.out.println("Userzy");
					System.out.println(DatabsaeMySQL.pobierzUserow());
					objOutputStream.writeObject(DatabsaeMySQL.pobierzUserow());
					objOutputStream.flush();			
				}
				System.out.println("Zakoñczono po³¹czenie z" + mySocket.getInetAddress());
			} else if (mySocket.getLocalPort() == 1004) {
				System.out.println("Zaraz nast¹pi aktualizaacja bazy danych");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
				/*Pytanie p = (Pytanie) objInputStream.readObject();// castowanko
																	// na
																	// Pytanko
				JDBC.dodajPytanie(p);
				System.out.println("Aktualizacja bazy danych zakoñczona");*/
			}
			mySocket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
