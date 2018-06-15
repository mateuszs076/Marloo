package Client;

import Communication.Communication;
import Communication.Data.User;
import application.connections.ServerConnector;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerConnector serverConnector;
		try {

			//tworzenie klasy pomocniczej - zawiera objectOutputStream i objectInputStream i Socket
			serverConnector = new ServerConnector();

			//wysyłanie obiektu do serwera
			serverConnector.sendObject(Communication.ACTION1);

			//odebranie odpowiedzi od serwera
			Object o = serverConnector.receiveObject();

			//sprawdzamy czy otrzymana odpowiedz to jest User
			if (o instanceof User) {
				System.out.print(((User) o).getLogin() + ((User) o).getHaslo());
			}

			//zamykamy polaczenie
			serverConnector.closeSocket();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
