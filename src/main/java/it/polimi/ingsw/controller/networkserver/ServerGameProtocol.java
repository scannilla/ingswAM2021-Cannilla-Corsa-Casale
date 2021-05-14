package it.polimi.ingsw.controller.networkserver;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.application.Command;
import it.polimi.ingsw.controller.application.RequiredClientActions;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Callable;

public class ServerGameProtocol implements Callable<Integer> {

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

    private int actionCounter;

    private final boolean[] activatedCard = new boolean[3];

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
        this.actionCounter = 0;
    }

    /**
     * this method catches the command and the parameters from the client and parses it
     */
    @Override
    public Integer call() throws EndingGameException {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        new Thread(new CheckConnection(out)).start();
        while(true) {
            try {
                checkConnection();
            } catch (EndingGameException e) {
                throw new EndingGameException();
            }
            String cmd = null;
            try {
                //out.println("What do you want to do?");
                cmd = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert cmd != null;
            if (!cmd.equals("pong")) {
                if (cmd.equalsIgnoreCase("quit")) {
                    //
                    break;
                }
                if (player.isActive()) { //only the active player can send a command
                    Command c = GSON.commandParser(commandCreator(cmd)); //the command is created
                    c.setCommandPlayer(player);
                    String returnValue = c.executeCommand();
                    RequiredClientActions action = new RequiredClientActions(c, clientSocket, player);
                    int checkCard;
                    if (returnValue.contains("$")) {
                        if(returnValue.contains("$production") && actionCounter <3) {
                            actionCounter++;
                            checkCard = Integer.parseInt(returnValue.replace("$","").split(" ")[1]) -1;
                            if(!activatedCard[checkCard]) {
                                action.execute(returnValue.replace("$", ""));
                                activatedCard[checkCard] = true;
                            }
                            else
                                out.println("You have already used this production");
                        }
                        else if (actionCounter == 0) {
                            action.execute(returnValue.replace("$", ""));
                            actionCounter = 1;
                        } else
                        out.println("You already performed one action, wait for your next turn to do this action");
                    } else if (returnValue.equals("end"))
                        endTurn(player);
                    else
                        out.println(returnValue);
                } else {
                    out.println("Please wait for your turn, in the meanwhile type view to see the current state of the game");
                    //view commands;
                }
            }
        }
        return 1;
    }

    private void endTurn(Player player){
        actionCounter=0;
        for (int i = 0; i < 3; i++) {
            activatedCard[i] = false;
        }
        for(Player p : connectedGame.getPlayers()) {
            if (p==player) {
                p.setActive(false);
                try {
                    Player next= connectedGame.nextPlayer(p);
                    next.setActive(true);
                } catch (IllegalArgumentException e) {
                    return;
                }
            }
        }

    }

    private void checkConnection() throws EndingGameException {
        try {
            clientSocket.setSoTimeout(5000);
        } catch (SocketException e) {
            e.printStackTrace();
            //
        }
    }

    private String commandCreator(String cmd) {
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

