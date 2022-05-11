import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9797)){
            Socket socket = server.accept();
            System.out.println("Connection established: " + socket);
            Thread serverThread = new Thread(new SocketConnectionHandler(socket));
            serverThread.start();

        }
        catch (IOException exception) {
            System.err.println(exception);
        }
    }
}
