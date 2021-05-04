package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.GSON;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.GSON;

import java.io.*;
import java.net.Socket;
import java.util.Locale;
import java.util.Map;

public class ServerGameProtocol implements Runnable {

    /**
     * this attribute represents the client socket
     */
    private final Socket clientSocket;

    /**
     * this attribute represents the connected game
     */
    private final Game connectedGame;

    /**
     * this attribute represents the player
     */
    private final Player player;

    /**
     * constructor of ServerGameProtocol
     * @param player Player
     * @param connectedGame Game
     * @param clSocket Socket
     */
    public ServerGameProtocol(Player player, Game connectedGame, Socket clSocket) {
        this.clientSocket = clSocket;
        this.connectedGame = connectedGame;
        this.player = player;
    }

    /**
     * this method catches the command and the parameters from the client and parses it
     * @throws IOException
     */
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(true){
            String cmd = null;
            try {
                cmd = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert cmd != null;
            if(cmd.equalsIgnoreCase("quit")){
                break;
            }

            String[] command = cmd.replace("\\s", "").split("-");
            String jsonString = ("{\"command\" :" + command[0] + "}");
            if(command.length>1) {
                jsonString = jsonString.concat("{\"parameters\" : [");
                for (int i = 0; i < command.length-1; i++)
                    jsonString = jsonString.concat("\"" +command[i]+"\",");
                jsonString = jsonString.concat("\"" +command[command.length-1]+"\"");
            }
            Command c = GSON.commandParser(jsonString);
            c.setCommandPlayer(player);
            String returnValue = c.executeCommand();
            RequiredClientActions action = new RequiredClientActions(c, clientSocket, player);;
            if(returnValue.contains("$"))
                action.execute(returnValue.replace("$", ""));
            else
                out.println(c.executeCommand());
        }


        }
}
