import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class SocketConnectionHandler implements Runnable{
    final Socket socket;

    public SocketConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            oos.writeObject("gotów");

            int n = (int) ois.readObject();

            int[] arr = (int[]) ois.readObject();

            oos.writeObject("gotowy do odbioru");

            for(int i = 0; i < n; i++) {
                Message message = (Message) ois.readObject();
                System.out.println(Arrays.toString(message.getNumber()) +" "+ message.getContent());

        }
            oos.writeObject("skończyłem");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
