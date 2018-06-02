package server.connectors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnector {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ClientConnector(ServerSocket serverSocket) throws IOException {
        socket = serverSocket.accept();

        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendObject(Object object) throws IOException {
        objectOutputStream.writeObject(object);
    }

    public Object recieveObject() throws IOException, ClassNotFoundException {
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
