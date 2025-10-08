import java.io.ObjectInputStream;

public class ReaderThread implements Runnable{

    private ObjectInputStream ois;
    private String name;

    public ReaderThread(ObjectInputStream ois, String name) {
        this.ois = ois;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true){
            try{
                Object received = ois.readObject();

                System.out.println(name + " - got says: " + (String) received);
            } catch (Exception e){
                System.out.println("Thread interrupted");
            }
        }
    }
}
