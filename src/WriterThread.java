import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class WriterThread implements Runnable{

    private ObjectOutputStream oos;
    private String name;


    public WriterThread(ObjectOutputStream oos, String name) {
        this.oos = oos;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            String message = sc.nextLine();

            try{
                oos.writeObject(message);
                System.out.println("Message sent...");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
