package it.polimi.ingsw.controller;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerProtocolWaiting implements Runnable {

    /**
     * this attribute represents the number of connected clients
     */

    private static int numberOfClients = 0;

    /**
     * this attribute represents the client socket
     */

    private final Socket client;

    /**
     * this attribute represents the id of the connected clinet
     */

    private final int clientNumber;

    /**
     * this method creates a new waiting room for the selected client
     * @client Socket
     */

    public ServerProtocolWaiting(Socket client) {
        this.client = client;
        numberOfClients++;
        clientNumber = numberOfClients;
    }

    /**
     * this method catches the command sent from the client
     * @throws IOException IOException
     */

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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ended");
    }
}






