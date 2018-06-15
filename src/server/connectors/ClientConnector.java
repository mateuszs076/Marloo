package server.connectors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnector {

    private static final ClientConnector clientConnector = new ClientConnector();

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private ClientConnector() {
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

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptSocket(ServerSocket s) {
        try {
            socket = s.accept();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientConnector getInstance() {
        return clientConnector;
    }
}
