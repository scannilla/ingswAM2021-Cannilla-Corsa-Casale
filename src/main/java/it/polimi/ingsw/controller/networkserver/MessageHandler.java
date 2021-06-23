package it.polimi.ingsw.controller.networkserver;

import it.polimi.ingsw.cli.Color;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.resources.Resource;

import java.io.*;
import java.net.Socket;

public class MessageHandler {

    private final ObjectOutputStream out;

    private final ObjectInputStream in;

    /**
     * Constructor of this MessageHandler linked to this clientSocket
     * @param clientSocket Socket
     * @throws EndingGameException e
     */
    public MessageHandler(Socket clientSocket) throws EndingGameException {
        if(clientSocket==null) {
            in = null;
            out = null;
        }
        else {
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                throw new EndingGameException();
            }
        }
    }


    /**
     * Reads a message from Client
     * @return String message
     * @throws EndingGameException e
     */
    public String readClientMessage() throws EndingGameException {

        try{
            return ((Message)in.readObject()).getMessage();
        } catch (IOException | ClassNotFoundException e) {
            throw new EndingGameException();
        }

    }

    /**
     * Sends a message to Output Stream
     * @param message String
     * @throws EndingGameException e
     */
    public void sendMessageToClient(String message) throws EndingGameException {
        Message m = new Message(1, message, "prova");

        try {
            out.writeObject(m);
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    public void sendMessageToClient(String message, int code) throws EndingGameException {
        Message m = new Message(code, message, null);

        try {
            out.writeObject(m);
            out.reset();
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    public void sendMessageToClient(Response response) throws EndingGameException {
        Message m = new Message(response.getCode(), response.getMessage(), null);

        try {
            out.writeObject(m);
            out.reset();
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    public void sendObjectToClient(ObjectMessage obj) throws EndingGameException {
        try {
            out.writeObject(obj);
            out.reset();
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    public void drawResource(Resource res, String nickname) throws EndingGameException {
        String reset = Color.ANSI_RESET.escape();
        String resource = "";
        switch (res.toString()) {
            case "coin":
                resource = Color.ANSI_YELLOW + "C" + reset;
                break;
            case "stone":
                resource = Color.ANSI_GREY + "R" + reset;
                break;
            case "servant":
                resource = Color.ANSI_PURPLE + "S" + reset;
                break;
            case "shield":
                resource = Color.ANSI_BLUE + "H" + reset;
                break;
        }
        Message m = new Message(600, resource, nickname);
        try {
            out.writeObject(m);
            out.reset();
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    public void drawMarble(MarketMarble marble, String nickname) throws EndingGameException {
        String reset = Color.ANSI_RESET.escape();
        String mar = "";
        switch (marble.getColor()) {
            case 0:
                mar = Color.ANSI_BRIGHTWHITE + "O" + reset;
                break;
            case 1:
                mar = Color.ANSI_GREY + "O" + reset;
                break;
            case 2:
                mar = Color.ANSI_BLUE + "O" + reset;
                break;
            case 3:
                mar = Color.ANSI_YELLOW + "0" + reset;
                break;
            case 4:
                mar = Color.ANSI_PURPLE + "O" + reset;
                break;
            case 5:
                mar = Color.ANSI_RED + "0" + reset;
                break;
        }
        Message m = new Message(601, mar, nickname);
        try {
            out.writeObject(m);
            out.reset();
            drawResource(marble.returnAbility(), nickname);
        } catch (IOException e) {
            throw new EndingGameException();
        } catch (Exception ignored) {
        }
    }



}
