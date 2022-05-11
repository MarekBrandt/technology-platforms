import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String [] args) {
        try(Socket client = new Socket("localhost", 9797)){
            try (ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                 ObjectInputStream ois = new ObjectInputStream(client.getInputStream())) {
                String message = (String) ois.readObject();
                if(!Objects.equals(message, "gotów")){
                    System.out.println("not working1");
                    return;
                }
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();

                int[] arr = new int[10];
                for (int i = 0; i < 10; i++){
                    arr[i] = (int)(Math.random()*n) + 1;
                }
                oos.writeObject(n);
                oos.writeObject(arr);

                message = (String) ois.readObject();
                if(!Objects.equals(message, "gotowy do odbioru")){
                    System.out.println("not working2");
                    return;
                }

                for(int i = 0; i<n; i++){
                    oos.writeObject(new Message(arr, "hello"));
                }

                message = (String) ois.readObject();
                if(!Objects.equals(message, "skończyłem")){
                    System.out.println("not working3");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (IOException exception) {
            System.err.println(exception);
        }
    }
}
