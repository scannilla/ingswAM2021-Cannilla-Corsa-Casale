package it.polimi.ingsw.controller.singleplayer;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SPMessageHandler extends MessageHandler {

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public SPMessageHandler() throws EndingGameException {
        super(null);
    }

    @Override
    public String readClientMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Did your keyboard just exploded?");
        }
        return null;
    }

    @Override
    public void sendMessageToClient(String message) {
        System.out.println(message);
    }
}
