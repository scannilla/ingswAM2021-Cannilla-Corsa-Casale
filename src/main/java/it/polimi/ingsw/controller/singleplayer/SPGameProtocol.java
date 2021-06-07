package it.polimi.ingsw.controller.singleplayer;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.application.Command;
import it.polimi.ingsw.controller.application.RequiredClientActions;
import it.polimi.ingsw.controller.virtualview.*;

public class SPGameProtocol implements Runnable{

    private SPMessageHandler handler;

    private final Player player;

    public SPGameProtocol(Player player) {
        this.player = player;
        try {
            this.handler = new SPMessageHandler();
        } catch (EndingGameException e) {
            System.err.println("Unable to connect to the keyboard");
        }
    }

    @Override
    public void run() {
        String command;
        String jsonString;
        String returnValue;

        while(true) {
            command = handler.readClientMessage();
            jsonString = createCommand(command);
            Command c = GSON.commandParser(jsonString);
            c.setCommandPlayer(player);
            returnValue = c.executeCommand();
            if(returnValue.equals("end")) {
                try {
                    player.getConnectedGame().endTurn();
                } catch (RuntimeException | EndingGameException e) {
                    System.out.println("Game Over");
                }
            }
            if(returnValue.contains("$")) {
                try {
                    new RequiredClientActions(c, player, handler).execute(returnValue.replace("$",""));
                } catch (EndingGameException e) {
                    e.printStackTrace();
                    break;
                    //change notification method
                }
            }
        }
    }

    private void createListeners() {
        EventManager.subscribe(EventType.CARDMARKET, new CardMarketListener(handler));
        EventManager.subscribe(EventType.LEADERCARD, new LeaderCardListener(handler));
        EventManager.subscribe(EventType.MARKET, new MarketListener(handler));
        EventManager.subscribe(EventType.PERSONALBOARD, new PersonalBoardListener(handler));
    }

    private String createCommand(String cmd){
        String[] command = cmd.replace(" ", "").split("-");
        String jsonString = ("{\"command\" : \"" + command[0] + "\"");
        if (command.length > 1) {
            jsonString = jsonString.concat(",\"parameters\" : [");
            for (int i = 1; i < command.length - 1; i++)
                jsonString = jsonString.concat("\"" + command[i] + "\",");
            jsonString = jsonString.concat("\"" + command[command.length - 1] + "\"" + "]");
        }
        jsonString = jsonString.concat("}");
        return jsonString;
    }

}
