package server.threads;

import server.connectors.ClientConnector;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionListenerThread implements Runnable{
    private ClientConnector cc;

    private Object object;
    private ExecutorService executor;

    public ConnectionListenerThread(ClientConnector cc){
         this.cc = cc;
         executor = Executors.newCachedThreadPool();
    }
    @Override
    public void run() {
        try {
            while (true) try {
                object = cc.recieveObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
