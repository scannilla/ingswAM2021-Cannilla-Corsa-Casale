package it.polimi.ingsw.controller.networkserver;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class CheckConnection implements Runnable{

    private final MessageHandler mHandler;

    public CheckConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                return;
            }
            try {
                mHandler.sendMessageToClient("ping", 5);
            } catch (EndingGameException e) {
                return;
            }
        }
    }
}
