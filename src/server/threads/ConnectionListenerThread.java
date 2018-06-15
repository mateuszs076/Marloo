package server.threads;

import Communication.Communication;
import Communication.User;
import server.connectors.ClientConnector;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionListenerThread implements Runnable {

    private ClientConnector cc;
    private ExecutorService executor;

    public ConnectionListenerThread(ClientConnector cc) {
        this.cc = cc;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        Object obj;

        while (true) {
            try {
                obj = cc.receiveObject();

                if (obj.equals(Communication.ACTION1)) {
                    System.out.println("got action1");

                    cc.sendObject(new User("janek", "1234"));
                    cc.closeSocket();
                }

            } catch (SocketException se) {
                cc.closeSocket();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

