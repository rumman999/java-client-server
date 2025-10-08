import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Server {
    private static final List<ObjectOutputStream> clientOutputs = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server started");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            new ServerThread(socket);

        }
    }

    static void broadcast(String msg) throws IOException {
        synchronized (clientOutputs){
            for(ObjectOutputStream oos: clientOutputs){
                oos.writeObject(msg);
            }
        }
    }

    static void register(ObjectOutputStream oos){
        clientOutputs.add(oos);
    }

    static void unregister(ObjectOutputStream oos){
        clientOutputs.remove(oos);
    }
}