package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TestClient {
	public static void main(String args[]) {
		try {
			int port = 752;
			
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String str = "getPictures,0,20,date DESC";
			
			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			
			System.out.println("rozpoczynam odbiór");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			
            /*ArrayList<Picture> p = (ArrayList<Picture>) objInputStream.readObject();
            System.out.println(p.get(0).getDate());*/
            
			System.out.println("koñczê odbiór");
			
			socket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
