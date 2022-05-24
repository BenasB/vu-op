import java.io.*;
import java.net.*;

public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            System.out.println("Starting user thread");

            String serverMessage = "New user connected";
            server.broadcast(serverMessage);

            String clientMessage;
            do {
                clientMessage = reader.readLine();
                System.out.println("Got a message: " + clientMessage);

                if (clientMessage.equals("disconnect"))
                    break;

                serverMessage = clientMessage;
                server.broadcast(serverMessage);
            } while (true);

            server.removeUser(this);
            socket.close();

            serverMessage = "A user has quit";
            server.broadcast(serverMessage);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}