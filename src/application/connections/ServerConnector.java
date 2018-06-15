package application.connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerConnector {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ServerConnector() throws IOException {
        System.out.println("Creating ServerConnector");

        String host = "localhost";
        int port = 1234;

        socket = new Socket(host, port);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public void sendObject(Object object) throws IOException {
        objectOutputStream.writeObject(object);
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }

    public Socket getSocket() {
        return socket;
    }
    public void closeSocket() throws IOException {
        socket.close();
        objectOutputStream.close();
    }
}
