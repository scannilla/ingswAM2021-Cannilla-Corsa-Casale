package it.polimi.ingsw.controller;


import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServerMain {

    public static void main (String[] args) {
        int portNumber;
        ExecutorService executor = Executors.newCachedThreadPool();
        if (args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        } else {
            portNumber = 48745;
        }
        System.out.println("Starting server");
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(serverSocket==null)
            System.exit(9);

        Socket clientSocket;

        int numberOfPlayers = 0;
        BufferedReader in;
        PrintWriter out;
        String nickname;
        ArrayList<Player> connectedPlayers = new ArrayList<>();
        HashMap<Player, Socket> mapAllPlayer = new HashMap<>();

        try {
            clientSocket = serverSocket.accept();
            do {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("insert a nickname");
                nickname= in.readLine();
            }
            while (nickname==null);
            System.out.println(nickname);
            numberOfPlayers = new GameSetup(clientSocket).setupGame();
            executor.submit(new ServerProtocolWaiting(clientSocket));
            mapAllPlayer.put(new Player(nickname), clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("unable to create game");
            System.exit(1);
        }
        int connectedClients=1;


        while (connectedClients<numberOfPlayers) {
            try {
                clientSocket = serverSocket.accept();
                nickname=null;
                do {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("insert a nickname");
                    nickname= in.readLine();
                }
                while (nickname==null);
                connectedClients++;
                executor.submit(new ServerProtocolWaiting(clientSocket));
                mapAllPlayer.put(new Player(nickname), clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        executor.shutdownNow();
        Game game = new Game(numberOfPlayers, connectedPlayers);
        try {
            game.initialSet();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to start game");
        }



        for (Map.Entry<Player, Socket> entry : mapAllPlayer.entrySet()) {
            executor.submit(new ServerGameProtocol(entry.getKey(), game, entry.getValue()));
        }

        while (true) {
            try {
                if (!executor.awaitTermination(2, TimeUnit.HOURS)) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

