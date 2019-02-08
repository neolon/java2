package java2.lesson6_2.client;

import java2.lesson6_2.server.Server;

import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        new Thread (()-> client.receiveMessage()).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String messageSend;
            messageSend = scanner.nextLine();
            client.sendMessage(messageSend);

        }
    }
}
