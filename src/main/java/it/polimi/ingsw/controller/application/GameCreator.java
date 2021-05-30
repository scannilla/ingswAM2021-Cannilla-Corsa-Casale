package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameCreator {

    /**
     * represents the number of players connected
     */

    private int numberOfPlayers;

    /**
     * represents the client socket
     */

    private final Socket clientSocket;

    /**
     * this method starts the game setup and sets the number of players
     *
     */

    public GameCreator(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * this method starts the game setup and sets the number of players
     * @return numberOfPlayers
     */

    public int createGame(Game game) {
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
            try {
                numberOfPlayers = CheckCommand.checkNumber(clientSocket, MessageHandler.readClientMessage(clientSocket));
            } catch (EndingGameException e) {
                throw new EndingGameException();
            }
        }
        while (numberOfPlayers!=1 && numberOfPlayers != 2 && numberOfPlayers !=3 && numberOfPlayers != 4);
        game.setNumberOfPlayers(numberOfPlayers);
        return numberOfPlayers;
    }

}
