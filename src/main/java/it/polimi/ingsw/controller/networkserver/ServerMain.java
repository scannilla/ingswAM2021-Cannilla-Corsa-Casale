package it.polimi.ingsw.controller.networkserver;



import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndGame;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.application.Automaton;
import it.polimi.ingsw.controller.application.CommandsHandler;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.*;


public class ServerMain {

    private static final LinkedHashMap<Player, Socket> mapAllPlayer = new LinkedHashMap<>();

    private static ServerSocket serverSocket;

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private static final CommandsHandler handler = CommandsHandler.instanceCreator(new Automaton());

    private static final ArrayList<MessageHandler> mHandlers = new ArrayList<>();



    /**
     * this method sets the connection with the clients
     * @param args String[]
     */
    public static void main (String[] args) {
        //starting server
        startServer(args);
        //creating socket for each player, the server only accept a maximum number of 4 players, as soon as someone disconnects from the server
        //everyone receives a warning and the server shut down
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        while(futures.size()<=4) {
            try {
                futures.add(createConnection());
            } catch (EndingGameException e) {
                for (MessageHandler m : mHandlers) {
                    EndGame.end(m);
                }
                System.out.println("Server shutting down");
                System.exit(1);
            }
        }
        checkEnd(futures);
    }



    private static Integer checkEnd (ArrayList<Future<Integer>> futures){
        Integer end = 0;
        while (end.equals(0)) {
            for (Future<Integer> f : futures) {
                try {
                    end = f.get(5, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException e) {
                    for(MessageHandler m : mHandlers)
                        EndGame.end(m);
                    System.out.println("Server shutting down");
                    System.exit(1);
                } catch (TimeoutException e) {
                    System.out.println("waiting for a thread to end");
                }
            }
        }
        return end;
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

    private static Future<Integer> createConnection() throws EndingGameException {
        Socket clientSocket;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new EndingGameException();
        }
        String nickname;
        MessageHandler mHandler = new MessageHandler(clientSocket);
        mHandlers.add(mHandler);
        try {
            nickname = askForNickname(mHandler);
        } catch (EndingGameException e) {
            throw new EndingGameException();
        }

        Player player = new Player(nickname);
        mapAllPlayer.put(player, clientSocket);
        return executor.submit(new NewServerGameProtocol(clientSocket, player, handler, mHandler, false));
    }

    private static String askForNickname(MessageHandler mHandler) throws EndingGameException {
        String nickname;
        boolean used;
        do {
            used = false;
            try {
                mHandler.sendMessageToClient("Insert a valid nickname", 110);
                nickname = mHandler.readClientMessage();
            } catch (EndingGameException e) {
                throw new EndingGameException();
            }
            for(Player p : mapAllPlayer.keySet()) {
                if (nickname.equals(p.getNickname())) {
                    used = true;
                    break;
                }
            }
        } while (nickname.isBlank() || nickname.isEmpty() || used);
        mHandler.sendMessageToClient(nickname, 310);
        return nickname;
    }

}

class CrashChecker implements Runnable {
    private final Future<Integer> future;

    public CrashChecker(Future<Integer> future) {
        this.future = future;
    }

    @Override
    public void run() {

    }
}

