package it.polimi.ingsw.controller.networkserver;


import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.application.*;
import it.polimi.ingsw.controller.virtualview.*;


import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Callable;

public class NewServerGameProtocol implements Runnable {
    /**
     * this attribute represents the client socket
     */
    private final Socket clientSocket;

    /**
     * this attribute represents the player
     */
    private final Player player;

    private static int playerNumber=0;

    private static CommandsHandler handler;

    private final MessageHandler mHandler;

    private final boolean local;



    /**
     * Constructor of this NewServerGameProtocol
     * @param clientSocket Socket
     * @param player Player
     * @param handler CommandsHandler
     */
    public NewServerGameProtocol(Socket clientSocket, Player player, CommandsHandler handler, MessageHandler mHandler, boolean local) {
        this.clientSocket = clientSocket;
        this.player = player;
        playerNumber++;
        NewServerGameProtocol.handler = handler;
        this.mHandler = mHandler;
        this.local = local;
    }

    @Override
    public void run() {
        createListeners();
        if (!local)
            new Thread(new CheckConnection(mHandler)).start();
        while (true) {
            if(!local)
                checkConnection();
            String command;
            try {
                command = mHandler.readClientMessage();
                Response response = handler.tryCommand(createCommand(command), player, mHandler);
                if (response.getCode() == 999)
                    EventManager.notifyListener(EventType.ENDGAME, null);
                if (response.getCode() == 401)
                    response = handler.getValidCommands();
                mHandler.sendMessageToClient(response);
            } catch (EndingGameException e) {
                EventManager.notifyListener(EventType.ENDGAME, null);
                return;
            }
        }
    }

    /**
     * Constructor of a new command using parsing json
     * @param cmd String
     * @return command
     */
    private static String[] createCommand(String cmd){
        String[] command = cmd.replace(" ", "").split("-");
        String jsonString = ("{\"command\" : \"" + command[0] + "\"");
        if (command.length > 1) {
            jsonString = jsonString.concat(",\"parameters\" : [");
            for (int i = 1; i < command.length - 1; i++)
                jsonString = jsonString.concat("\"" + command[i] + "\",");
            jsonString = jsonString.concat("\"" + command[command.length - 1] + "\"" + "]");
        }
        jsonString = jsonString.concat("}");
        return new String[] {command[0], jsonString};
    }

    private void checkConnection(){
        try {
            clientSocket.setSoTimeout(5000);
        } catch (SocketException e) {
            EventManager.notifyListener(EventType.ENDGAME, null);
        }
    }

    private void createListeners() {
        EventManager.subscribe(EventType.CARDMARKET, new CardMarketListener(mHandler));
        EventManager.subscribe(EventType.MARKET, new MarketListener(mHandler));
        EventManager.subscribe(EventType.PERSONALBOARD, new PersonalBoardListener(mHandler));
        EventManager.subscribe(EventType.LEADERCARD, new LeaderCardListener(mHandler));
        EventManager.subscribe(EventType.LEADERBOARD, new LeaderboardListener(mHandler));
        EventManager.subscribe(EventType.GAMESTART, new GameStartListener(mHandler));
        EventManager.subscribe(EventType.VATICANBOARD, new VaticanBoardListener(mHandler));
    }
}
