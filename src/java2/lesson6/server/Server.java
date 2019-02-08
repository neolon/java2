package java2.lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Server {

    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(){
        ServerSocket server = null;
        Socket socket = null;
        try {

            server = new ServerSocket(8080);
            System.out.println("server start");

            while (true) {
                socket = server.accept();
                System.out.println("client connected");
                clients.add(new ClientHandler(this, socket));
            }

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

    public void broadCast(String msg){
        for (ClientHandler handler: clients){
            handler.sendMsg(msg);
        }
    }
}
