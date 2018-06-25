package application.connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnector extends Thread {

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ServerConnector() {
        System.out.println("Creating ServerConnector");

        String host = "localhost";
        int port = 9999;

        try {
            socket = new Socket(host, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }

    public void sendObject(Object object) throws IOException {
        System.out.println("Sending object..");
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
