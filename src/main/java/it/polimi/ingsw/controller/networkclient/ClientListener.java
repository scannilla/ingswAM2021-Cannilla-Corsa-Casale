package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

public class ClientListener implements Runnable{

    /**
     * represents the client message handler
     */
    private  final ClientMessageHandler cmHandler;

    private final boolean gui;

    /**
     * constructor for ClientListener
     */
    public ClientListener(ClientMessageHandler cmHandler, boolean gui) {
        this.cmHandler = cmHandler;
        this.gui = gui;
    }

    /**
     * this method makes the client listen to the server
     */
    @Override
    public void run() {
        if(!gui) {
            while (true) {
                try {
                    String read = cmHandler.readMessage();
                    System.out.println(read);
                } catch (EndingGameException e) {
                    break;
                }
            }
        }
    }
}
