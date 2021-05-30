package it.polimi.ingsw.controller.networkserver;


import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.application.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Callable;

public class NewServerGameProtocol implements Callable<Integer> {
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



    /**
     * Constructor of this NewServerGameProtocol
     * @param clientSocket Socket
     * @param player Player
     * @param handler CommandsHandler
     */
    public NewServerGameProtocol(Socket clientSocket, Player player, CommandsHandler handler) {
        this.clientSocket = clientSocket;
        this.player = player;
        playerNumber++;
        NewServerGameProtocol.handler = handler;
    }

    @Override
    public Integer call() throws Exception {


        new Thread(new CheckConnection(clientSocket));
        while (true) {
            checkConnection();
            String command;
            try {
                command = MessageHandler.readClientMessage(clientSocket);
                String response = handler.tryCommand(createCommand(command), clientSocket, player);
                MessageHandler.sendMessageToClient(response, clientSocket);
            } catch (EndingGameException e) {
                break;
            }
        }
        return 1;
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
            e.printStackTrace();
        }
    }
}
