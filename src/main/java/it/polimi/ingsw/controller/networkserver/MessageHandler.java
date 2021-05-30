package it.polimi.ingsw.controller.networkserver;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;

import java.io.*;
import java.net.Socket;

public final class MessageHandler {

    /**
     * Reads a message from a file
     * @return String message
     * @throws EndingGameException e
     */
    public static String readClientMessage(Socket clientSocket) throws EndingGameException {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            String message = ((Message)in.readObject()).getMessage();
            in.close();
            return message;
        } catch (IOException | ClassNotFoundException e) {
            throw new EndingGameException();
        }

    }

    /**
     * Sends a message to Output Stream
     * @param message String
     * @throws EndingGameException e
     */
    public static void sendMessageToClient(String message, Socket clientSocket) throws EndingGameException {
        Message m = new Message(1, message, "prova");
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(m);
            out.close();
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }


}
