package server;

import server.connectors.ClientConnector;
import server.threads.ConnectionListenerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.print("Tworzymy nowy serwer");

            //nasluchujemy na polaczenie klientow i tworzymy nowe watki
            while(true){
                ClientConnector clientConnector = new ClientConnector(serverSocket);

                Executor executor = Executors.newCachedThreadPool();
                executor.execute(new ConnectionListenerThread(clientConnector));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
