package it.polimi.ingsw.controller.networkserver;


import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndGame;
import it.polimi.ingsw.controller.application.Automaton;
import it.polimi.ingsw.controller.application.Command;
import it.polimi.ingsw.controller.application.CommandsHandler;
import it.polimi.ingsw.controller.application.GameCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;


public class ServerMain {

    private static final LinkedHashMap<Player, Socket> mapAllPlayer = new LinkedHashMap<>();

    private static int numberOfPlayers = 0;

    private static int connectedClients = 0;

    private static ServerSocket serverSocket;

    private static final ExecutorService executor = Executors.newCachedThreadPool();



    /**
     * this method sets the connection with the clients
     * @param args String[]
     */
    public static void main (String[] args) {
        //starting server
        startServer(args);
        //creation of the fsm
        CommandsHandler handler = CommandsHandler.instanceCreator(new Automaton());
        //if the number of players chosen is more than 1 all connected players are connected to a waiting lobby TODO move to FSM
        createWaitingLobby();
        //when all players are connected the game is created TODO move to FSM
        executor.shutdownNow();
        ArrayList<Player> players = new ArrayList<>(mapAllPlayer.keySet());

        //actual game, move everything to a different function

        //check if a thread either end or throws an exception

        for(Socket sock : mapAllPlayer.values())
            EndGame.end(sock);
        System.exit(1);
    }

    private static void firstPlayer() {
        BufferedReader in;
        PrintWriter out;
        String nickname;
        try {
            Socket clientSocket;
            clientSocket = serverSocket.accept();
            do {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("insert a nickname");
                nickname= in.readLine();
            }
            while (nickname==null);
            System.out.println(nickname);
            //numberOfPlayers = new GameCreator(clientSocket).createGame();
            executor.submit(new ServerProtocolWaiting(clientSocket));
            mapAllPlayer.put(new Player(nickname), clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("unable to create game");
            System.exit(1);
        }
        connectedClients = 1;
    }

    private static void createWaitingLobby() {
        BufferedReader in;
        PrintWriter out;
        String nickname;
        while (connectedClients<numberOfPlayers) {
            try {
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                nickname = null;
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
    }

    private static Integer checkEnd (ArrayList<Future<Integer>> futures){
        Integer end = 0;
        while (end.equals(0)) {
            for (Future<Integer> f : futures) {
                try {
                    end = f.get(5, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException e) {
                    for(Socket sock : mapAllPlayer.values())
                        EndGame.end(sock);
                    System.out.println("Server shutting down");
                    System.exit(1);
                } catch (TimeoutException e) {
                    System.out.println("waiting for a thread to end");
                }
            }
        }
        return end;
    }

    private static ArrayList<Future<Integer>> createProtocol (Game game) {
        ExecutorService gameExecutor = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        Future<Integer> future;
        for (Map.Entry<Player, Socket> entry : mapAllPlayer.entrySet()) {
            future = gameExecutor.submit(new ServerGameProtocol(entry.getKey(), game, entry.getValue()));
            futures.add(future);
        }
        return futures;
    }

    private static void startServer(String[] args) {
        int portNumber;
        if (args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        } else {
            portNumber = 48745;
        }
        System.out.println("Starting server");
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(serverSocket==null)
            System.exit(9);
    }

}

