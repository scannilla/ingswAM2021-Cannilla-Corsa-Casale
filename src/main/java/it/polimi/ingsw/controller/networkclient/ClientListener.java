package it.polimi.ingsw.controller.networkclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while(clientSocket!=null) {
                line = in.readLine();
                if(line.equals("ping"))
                    out.println("pong");
                else
                    System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("unable to get in");
            e.printStackTrace();

        }

    }
}
