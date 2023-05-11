package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 53608;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    out.println("Hello! Write your name");
                    final String name = in.readLine();
                    System.out.println("New connection accepted. Username: " + name);
                    out.println("Are you child? (yes/no)");
                    final String age = in.readLine();
                    System.out.println("A child:" + age);
                    if (age.equals("yes") || age.equals("Yes")) {
                        out.println("Welcome to the kids area, " + name + ". Let's play!");
                    } else if (age.equals("no") || age.equals("No")) {
                        out.println("Welcome to the adult zone, " + name + ". Have a good rest, or a good working day!");
                    } else {
                        out.println("The answer is incorrect");
                    }
                }
            }
        }
    }
}