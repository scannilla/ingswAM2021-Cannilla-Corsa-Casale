package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequiredClientActions {
    private final Command command;
    private final Socket clientSocket;
    private final Player player;

    public RequiredClientActions(Command command, Socket clientSocket, Player player) {
        this.command = command;
        this.clientSocket = clientSocket;
        this.player = player;
    }

    public void execute(String cmd) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            switch(cmd) {
                case "buy":
                    out.println("you can buy the selected card, please choose the resources to use");
                    do {
                        String chosenResource = in.readLine();
                        switch(chosenResource.split(" ")[1]) {
                            case "warehousedepot":

                                break;

                            case "extradepot":

                                break;

                            case "strongbox":

                                break;

                            default:
                                out.println("select a valid resource location (Warehouse Depot, Extra Depot or Strongbox");
                                break;
                        }
                    }
                    while (false);
                    break;

                case "production":

                    break;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
