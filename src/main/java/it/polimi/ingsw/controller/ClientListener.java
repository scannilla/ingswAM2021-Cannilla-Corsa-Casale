package it.polimi.ingsw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable{

    /**
     * represents the client socket
     */
    Socket clientSocket;

    /**
     * constructor for ClientListener
     * @param clientSocket Socket
     */
    public ClientListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * this method makes the client listen to the server
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(clientSocket!=null)
                System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
