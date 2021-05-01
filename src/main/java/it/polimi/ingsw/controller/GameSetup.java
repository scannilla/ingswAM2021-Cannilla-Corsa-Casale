package it.polimi.ingsw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameSetup {

    /**
     * represents the number of players connected
     */

    private int numberOfPlayers;

    /**
     * represents the client socket
     */

    private final Socket clientSocket;

    /**
     * this method starts the game setup and sets the nuer of players
     *
     */

    public GameSetup(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * this method starts the game setup and sets the nuer of players
     * @return numberOfPlayers
     * @throws IOException IOException
     */

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
                numberOfPlayers = Integer.parseInt(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                out.println("Insert a valid number");
            }
        }
        while (numberOfPlayers!=1 && numberOfPlayers != 2 && numberOfPlayers !=3 && numberOfPlayers != 4);
        return numberOfPlayers;
    }

}
