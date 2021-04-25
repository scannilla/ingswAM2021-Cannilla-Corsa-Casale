package it.polimi.ingsw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameSetup {
    private int numberOfPlayers;
    private final Socket clientSocket;

    public GameSetup(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }



    public int setupGame () {
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (
                IOException e) {
            e.printStackTrace();
            return 0;
        }

        do {
            out.println("Insert number of players 1-4");
            try {
                numberOfPlayers = in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (numberOfPlayers!=1 && numberOfPlayers != 2 && numberOfPlayers !=3 && numberOfPlayers != 4);
        return numberOfPlayers;
    }

}
