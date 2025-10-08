import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Server.register(oos);

            while(true){
                Object cMsg = ois.readObject();

                System.out.println("From client:" + (String) cMsg);

                String serverMsg = (String) cMsg;

                Server.broadcast(serverMsg);
            }
        } catch (Exception e){
            System.out.println("Thread interrupted!");
        }
    }
}
