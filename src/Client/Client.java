package Client;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		(new Thread(new Laczezserverem())).start();
	}
}
