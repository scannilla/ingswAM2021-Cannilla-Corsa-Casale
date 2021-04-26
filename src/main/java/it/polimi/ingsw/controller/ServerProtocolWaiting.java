package it.polimi.ingsw.controller;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerProtocolWaiting implements Runnable {
    private static int numberOfClients = 0;
    private final Socket client;
    private final int clientNumber;


    public ServerProtocolWaiting(Socket client) {
        this.client = client;
        numberOfClients++;
        clientNumber = numberOfClients;
    }

    @Override
    public void run() {
        if (numberOfClients >= 4)
            return;
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String command;


        while (true) {
            try {
                command = in.readLine();
                if (command.equals("quit"))
                    break;
                else {
                    System.out.println("Received " + command);
                    //String json = ("{");
                    //Command command1 = new Command();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ended");
    }
}






