package it.polimi.ingsw.controller;


import it.polimi.ingsw.controller.networkserver.MessageHandler;


public class EndGame {
    /**
     * This method print in this client that the game is over
     * @param mHandler MessageHandler
     */
    public static void end (MessageHandler mHandler){
        try {
            mHandler.sendMessageToClient("Game over, server shutting down", 0);
        } catch (EndingGameException e) {
            System.err.println("User already disconnected");
        }
    }
}
