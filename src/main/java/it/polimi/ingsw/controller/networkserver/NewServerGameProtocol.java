package it.polimi.ingsw.controller.networkserver;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.application.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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

    private final int id;

    private static CommandsHandler handler;

    public NewServerGameProtocol(Socket clientSocket, Player player, CommandsHandler handler) {
        this.clientSocket = clientSocket;
        this.player = player;
        playerNumber++;
        id = playerNumber;
        NewServerGameProtocol.handler = handler;
    }

    @Override
    public Integer call() throws Exception {
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            return 17;
        }
        while (true) {
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                return 17;
            }
            out.println(handler.tryCommand(command, clientSocket, player));
        }

    }
}
