import java.io.*;
import java.net.*;

public class ChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started...");
            while (true) {
                new Thread(() -> {
                    try (Socket socket = serverSocket.accept();
                         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                        String msg;
                        while ((msg = in.readLine()) != null) {
                            System.out.println("Client: " + msg);
                            out.println("Server: " + msg);
                        }
                    } catch (IOException e) { e.printStackTrace(); }
                }).start();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
