package doPobierania;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Data.User;
import database.DatabasePostgree;
import database.DatabsaeMySQL;


public class ServerThread extends Thread {
	Socket mySocket;

	/**
	 * Konstruktor klasy ServerThread
	 * 
	 * @param socket
	 *            Obiekt typu socket wspomagający zdefiniowanie portu
	 */
	public ServerThread(Socket socket) {
		super();
		mySocket = socket;
	}

	public void run() {
		try {
			

			System.out.println("dzialam na potrzeby server thread dla "+this.getName().toString());
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
					objOutputStream.writeObject(DatabsaeMySQL.pobierzUserow());
					objOutputStream.flush();			
				}
				if (data[0].equals("produkty")) {
					System.out.println("produkty");
					objOutputStream.writeObject(DatabasePostgree.readProdukty());
					objOutputStream.flush();			
				}
				if (data[0].equals("odbiorcy")) {
					System.out.println("odbiorcy");
					objOutputStream.writeObject(DatabsaeMySQL.pobierzOdbiorcow());
					objOutputStream.flush();			
				}
				if (data[0].equals("maszyny")) {
					System.out.println("maszyny");
					objOutputStream.writeObject(DatabasePostgree.readMaszyna());
					objOutputStream.flush();			
				}
				System.out.println("Zakończono połączenie z" + mySocket.getInetAddress());
			} else if(mySocket.getLocalPort() == 1004)
			{
				/*
				mySocket.setTcpNoDelay(true);
				System.out.println("Rozpoczynam tworzenie u¿ytkownika");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
	            User p = (User) objInputStream.readObject();
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
				String str = ""+JDBC.addUser(p.getLogin(), p.getName(), p.getEmail(), p.getPassword(), p.getSex(), p.getType());
				out.println(str);
				out.flush();
				System.out.println("Koñczê resjestracje");*/
			}
			mySocket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
