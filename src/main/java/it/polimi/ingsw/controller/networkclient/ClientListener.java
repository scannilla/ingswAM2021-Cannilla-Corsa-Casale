package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.controller.Message;

import java.io.*;
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
        ObjectInputStream in;
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());

            while (clientSocket != null) {
                Message received;
                try {
                    received = (Message) in.readObject();
                } catch (ClassNotFoundException | IOException e) {
                    break;
                }
                if (received.getCode() == 5) {
                    out.writeObject(new Message(6, "pong", null));
                    out.flush();
                }
                System.out.println(received.getMessage());
                in.close();
            }
        } catch (IOException e) {
            System.err.println("boh");
            e.printStackTrace();
            System.exit(1);
        }

    }
}
