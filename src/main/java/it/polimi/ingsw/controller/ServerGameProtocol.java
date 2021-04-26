package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

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

            String command = cmd.substring(cmd.indexOf(" "));
            String firstParameter = cmd.substring(cmd.indexOf(" ") + 1);
            //String secondParameter = cmd.substring(cmd)
            String jsonString = ("{command :" + command + "}");
            Command clientCommand = new Command(cmd);


        }


        }
}
