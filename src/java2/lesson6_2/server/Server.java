package java2.lesson6_2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server = null;
    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(){
        try {
            server = new ServerSocket(8080);
            System.out.println("Server start");
            socket = server.accept();
            System.out.println("Client connected");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessage(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage(){
        try {
            while (true) {
                System.out.println("Message from client: " + in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}