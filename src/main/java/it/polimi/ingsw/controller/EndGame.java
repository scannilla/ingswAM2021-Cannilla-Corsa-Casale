package it.polimi.ingsw.controller;


import it.polimi.ingsw.controller.networkserver.MessageHandler;
import java.net.Socket;


public class EndGame {
    /**
     * This method print in this client that the game is over
     * @param mHandler MessageHandler
     */
    public static void end (MessageHandler mHandler){

        try {
            mHandler.sendMessageToClient("Game over, server shutting down");
        } catch (EndingGameException e) {
            System.err.println("User already disconnected");
        }


    }
}
