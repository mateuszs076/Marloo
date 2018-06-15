package server;

import server.connectors.ClientConnector;
import server.threads.ConnectionListenerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    private static final Server server = new Server();

    private Server() {
    }

    void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.print("Tworzymy nowy serwer");

            //nasluchujemy na polaczenie klientow i tworzymy nowe watki
            while(true){
                ClientConnector clientConnector = ClientConnector.getInstance();
                clientConnector.acceptSocket(serverSocket);

                Executor executor = Executors.newCachedThreadPool();
                executor.execute(new ConnectionListenerThread(clientConnector));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        return server;
    }
}
