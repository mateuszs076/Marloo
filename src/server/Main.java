package server;

import server.connectors.ClientConnector;
import server.threads.ConnectionListenerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);

            while (true) {
                ClientConnector clientConnector = new ClientConnector(serverSocket);

                Executor executor = Executors.newCachedThreadPool();
                executor.execute(new ConnectionListenerThread(clientConnector));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

