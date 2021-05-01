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

    private final Socket clientSocket;
    private final Game connectedGame;
    private final Player player;


    public ServerGameProtocol(Player player, Game connectedGame, Socket clSocket) {
        this.clientSocket = clSocket;
        this.connectedGame = connectedGame;
        this.player = player;
    }

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
