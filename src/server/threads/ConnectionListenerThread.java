package server.threads;

import Communication.Communication;
import Communication.Data.User;
import server.connectors.ClientConnector;
import server.database.DatabsaeMySQL;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Communication.Communication.AUTHORIZED_LOGIN;
import static Communication.Communication.UNAUTHORIZED_LOGIN;

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
                if(obj != null) {
                    System.out.println("najs!");
                }
                if (obj instanceof User) {
                    System.out.println("got action1");
                    try {
                        var user = DatabsaeMySQL.getInstance().login(((User) obj).getLogin(), ((User) obj).getHaslo());
                        if (user != null) {
                            cc.sendObject((User) user);
                            System.out.println("tak!");
                        } else {
                            cc.sendObject(UNAUTHORIZED_LOGIN);
                            System.out.println("nie1");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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

