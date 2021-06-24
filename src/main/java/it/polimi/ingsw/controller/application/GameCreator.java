package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

import java.io.*;
import java.net.Socket;

public class GameCreator {

    /**
     * represents the number of players connected
     */

    private int numberOfPlayers;

    private final MessageHandler mHandler;

    /**
     * this method starts the game setup and sets the number of players
     */

    public GameCreator(MessageHandler mHandler) {
        this.mHandler = mHandler;
    }

    /**
     * this method starts the game setup and sets the number of players
     *
     * @return numberOfPlayers
     */

    public int createGame(Game game) throws EndingGameException {

        do {
            try {
                mHandler.sendMessageToClient("Insert the number of players", 111);
                numberOfPlayers = CheckCommand.checkNumber(mHandler.readClientMessage(), mHandler);
            } catch (EndingGameException e) {
                throw new EndingGameException();
            }
        }
        while (numberOfPlayers != 1 && numberOfPlayers != 2 && numberOfPlayers != 3 && numberOfPlayers != 4);
        game.setNumberOfPlayers(numberOfPlayers);
        return numberOfPlayers;
    }
}
