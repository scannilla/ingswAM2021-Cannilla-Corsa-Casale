package it.polimi.ingsw.controller.singleplayer;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;



public class SPClientMessageHandler extends ClientMessageHandler {

    private final SPMessageHandler spMessageHandler;
    /**
     * Constructor of this ClientMessageHandler
     *
     * @throws EndingGameException e
     * @param spMessageHandler SPMessageHandler
     */
    public SPClientMessageHandler(SPMessageHandler spMessageHandler) throws EndingGameException {
        super(null);
        this.spMessageHandler = spMessageHandler;
    }

    @Override
    public void sendMessageToServer(String message) throws EndingGameException {
        super.sendMessageToServer(message);
    }

    @Override
    public void sendMessageToServer(String message, int code) throws EndingGameException {
        spMessageHandler.readClientMessage();
    }

    @Override
    public Message readMessage() throws EndingGameException {
        return super.readMessage();
    }

    public void readClientMessage(String message) {
        System.out.println(message);
    }
}
