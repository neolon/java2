package java2.lesson6_2.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private final String HOST = "localhost";
    private final int PORT = 8080;

    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        try {
            socket = new Socket(HOST, PORT);
            System.out.println("Client start");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

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
                System.out.println("Message from server: " + in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

