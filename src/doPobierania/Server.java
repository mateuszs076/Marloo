package doPobierania;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	ServerSocket serverSocket = null;
	int port;

	/**
	 * Konstruktor klasy Server
	 * 
	 * @param port
	 *            Numer portu na którym bêdzie dzia³a³ nasz server
	 */
	public Server(int port) {
		super();
		this.port = port;
	}

	/**
	 * Metoda umo¿liwiaj¹ca uruchomienie serwera jako w¹tek
	 */
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while (!Thread.interrupted()) {// dopóki dzia³a
				Socket socket = serverSocket.accept();
				(new ServerThread(socket)).start();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metoda s³u¿¹ca do zakoñczenia po³¹czenia
	 */
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}