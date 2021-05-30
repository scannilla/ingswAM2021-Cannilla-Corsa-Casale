package it.polimi.ingsw.controller.networkserver;

import it.polimi.ingsw.controller.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class CheckConnection implements Runnable{

    private final Socket clientSocket;

    public CheckConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
           return;
        }
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                return;
            }
            try {
                out.writeObject(new Message(5,"ping", null));
            } catch (IOException e) {
                return;
            }
        }
    }
}
