package java2.lesson6_2.server;

import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server();
        new Thread (()-> server.receiveMessage()).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String messageSend;
            messageSend = scanner.nextLine();
            server.sendMessage(messageSend);

        }
    }
}
