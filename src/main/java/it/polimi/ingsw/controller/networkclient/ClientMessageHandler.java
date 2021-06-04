package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public final class ClientMessageHandler {

    private final ObjectInputStream in;

    private final ObjectOutputStream out;

    /**
     * Constructor of this ClientMessageHandler
     * @param clientSocket Socket
     * @throws EndingGameException e
     */
    public ClientMessageHandler(Socket clientSocket) throws EndingGameException {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    /**
     * This method sends a message (String and code 1) to Server
      * @param message String
     * @throws EndingGameException e
     */
    public void sendMessageToServer(String message) throws EndingGameException {
        try {
            out.writeObject(new Message(1, message, null));
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    /**
     * This method sends a generic message to server
     * @param message String
     * @param code int
     * @throws EndingGameException e
     */
    public void sendMessageToServer(String message, int code) throws EndingGameException {
        try {
            out.writeObject(new Message(code, message, null));
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    /**
     * This method reads a message from Server
     * if the message received has code 5 this method responds with a message with message "pong" and code 6
     * @return receivedMessage || null
     * @throws EndingGameException e
     */
    public Message readMessage() throws EndingGameException {
        try {
            Object read = in.readObject();
            Message received = (Message) read;
            if (received.getCode() == 5)
                sendMessageToServer("pong", 6);
            else
                return received;

        } catch (IOException | ClassNotFoundException e) {
            throw new EndingGameException();
        }
        return null;
    }


}

