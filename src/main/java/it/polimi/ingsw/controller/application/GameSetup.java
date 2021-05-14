package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.io.*;
import java.net.Socket;

public class GameSetup {

    private final Socket clientSocket;

    private final Game game;

    private final Player player;

    public GameSetup(Socket clientSocket, Game game, Player player) {
        this.clientSocket = clientSocket;
        this.game = game;
        this.player = player;
    }

    public void gameSetUp(int index) {
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("unable to connect");
            return;
        }
        switch(index) {
            case 0:
                out.println("You're the first player so you're not gonna receive any resource or faith point");
                break;
            case 1:
                out.println("You're the second player so you can choose a resource to add to your warehouse depot");
                break;
            case 2:
                out.println("You're the third player so you can choose a resource to add to your warehouse depot, increased faith points by one");
                break;
            case 3:
                out.println("You're the fourth player so you can choose two resources to add to your warehouse depot, increased faith points by one");
                break;

        }
    }
}
