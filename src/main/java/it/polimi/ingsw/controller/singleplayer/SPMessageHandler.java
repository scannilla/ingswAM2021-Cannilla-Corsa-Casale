package it.polimi.ingsw.controller.singleplayer;

import it.polimi.ingsw.cli.Color;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkclient.ClientListener;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.resources.Resource;

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

    @Override
    public void sendObjectToClient(ObjectMessage obj) {
        ClientListener.printObj(obj);
    }

    @Override
    public void drawResource(Resource res, String nickname) {
        String reset = Color.ANSI_RESET.escape();
        String resource = "";
        switch (res.toString()) {
            case "stone":
                resource = Color.ANSI_GREY + "R" + reset;
                break;
            case "servant":
                resource = Color.ANSI_PURPLE + "S" + reset;
                break;
            case "shield":
                resource = Color.ANSI_BLUE + "H" + reset;
                break;
            case "coin":
                resource = Color.ANSI_YELLOW + "C" + reset;
                break;
        }
        System.out.println(resource);
    }


}
