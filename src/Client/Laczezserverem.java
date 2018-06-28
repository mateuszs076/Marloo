package Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Laczezserverem implements Runnable{

	@Override
	public void run() {
		Socket socket=new Socket();
		InetSocketAddress sa=new InetSocketAddress("127.0.0.1", 1003);
		try {
			socket.connect(sa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(socket.isConnected())
			System.out.println("Uzyskano Połączenie z "+sa);
			System.out.println("Zaraz zostanie uruchomiona aplikacja");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
