package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.LeaderBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.controller.networkserver.Response;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;
import it.polimi.ingsw.controller.virtualview.TokenListener;

import java.io.IOException;
import java.util.ArrayList;

public class CommandsHandler {
    private static CommandsHandler instance;
    private static Automaton fsm;
    private static Game game;
    private int setup=0;
    private static boolean first = true;
    private static boolean creator = true;

    /**
     * Constructor of this CommandsHandler
     * @param fsm Automaton
     */
    private CommandsHandler(Automaton fsm) {
        CommandsHandler.fsm = fsm;
        game = new Game();
    }

    /**
     * Creates a new instance and returns it
     * @param fsm Automaton
     * @return instance
     */
    public static CommandsHandler instanceCreator(Automaton fsm) {
        if(instance==null)
            instance = new CommandsHandler(fsm);
        return instance;
    }

    /**
     * Tries to execute a command in different Phase:
     * checks if this command is executable in this phase, if not throws EndingGameException
     * @param cmd String[]
     * @param player Player
     * @param mHandler MessageHandler
     * @return ok or ko
     * @throws EndingGameException e
     */
    public synchronized Response tryCommand(String[] cmd, Player player, MessageHandler mHandler) throws EndingGameException {
        GamePhase phase = fsm.getPhase();
        if(!game.getPlayers().contains(player))
            game.addPlayer(player);
        switch (phase) {
            case ACCEPTANCE:
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    fsm.evolveGamePhase();
                    if(first) {
                        first = false;
                        return new Response("Game joined", 202);
                    }
                    else
                        return new Response("Game joined", 203);
                }
                return new Response("Incorrect command", 401);
            case GAME_CREATOR:
                if(creator) {
                    int numbOfPlayers;
                    if (CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                        creator = false;
                        numbOfPlayers = new GameCreator(mHandler).createGame(game);
                        if (game.getPlayers().indexOf(player) == 0)
                            new Thread(new AutoCheckerWait(game, fsm, numbOfPlayers)).start();
                        if(numbOfPlayers==1)
                            EventManager.subscribe(EventType.TOKEN, new TokenListener(mHandler));
                        fsm.evolveGamePhase();
                        return new Response("ok", 201);
                    }
                    else
                        return new Response("Incorrect command", 401);
                }
                else
                    return new Response("Wait for the host to pick the number of players", 112);

            case WAITING_ROOM:
                return new Response("Nothing to do here, wait for the game to start", 402);
            case GAME_SETUP:
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    setup++;
                    new GameSetup(game, player, mHandler).gameSetUp(game.getPlayers().indexOf(player));
                    if(setup==game.getNumberOfPlayers())
                        fsm.evolveGamePhase();
                    return new Response("ok", 201);
                }
                return new Response("Incorrect command", 401);
            case GAME_PHASE:
                if(!player.isActive() && !cmd[0].equals("view")) {
                    mHandler.sendMessageToClient("Wait for you turn, in the meanwhile you can call only view methods", 190);
                    return new Response("ok", 201);
                }
                Command c = GSON.commandParser(cmd[1]);
                c.setCommandPlayer(player);
                Response returnValue = c.executeCommand();
                if(!CheckCommand.commandChecker(fsm.validCommands(), cmd[0]))
                    return new Response("Incorrect command", 401);
                if (returnValue.getMessage().equals("end")) {
                    try {
                        game.endTurn();
                    } catch (RuntimeException e) {
                        createLeaderboard(true);
                        return new Response("end", 999);
                    } catch (Error e1) {
                        createLeaderboard(false);
                        return new Response("end", 999);
                    }
                    return new Response("Turn ended", 191);
                }
                else if (returnValue.getMessage().contains("$")) {
                    RequiredClientActions r = new RequiredClientActions(c, player, mHandler);
                    r.execute(returnValue.getMessage().replace("$",""));
                    return new Response("ok", 201);
                }
                else
                    return returnValue;
            case END:
            case UNKNOWN:
            default:
        }
        return new Response("Incorrect command", 401);
    }

    public Response getValidCommands() {
        String validCommand = "You inserted a wrong command. These are the valid commands: ";
        for(int i=0; i<fsm.validCommands().length-1; i++)
            validCommand = validCommand.concat(fsm.validCommands()[i]+ ", ");
        validCommand = validCommand.concat(fsm.validCommands()[fsm.validCommands().length-1] + ".");
        return new Response(validCommand, 113);
    }

    private void createLeaderboard(boolean win) {
        ArrayList<Player> leaderBoard = new ArrayList<>();
        if(game.getPlayers().size()>1) {
            for (Player p : game.getPlayers()) {
                if (leaderBoard.isEmpty())
                    leaderBoard.add(p);
                else {
                    for (Player p1 : leaderBoard) {
                        if (p.getWp() > p1.getWp()) {
                            leaderBoard.add(leaderBoard.indexOf(p1), p);
                            break;
                        } else if (p.getWp() <= leaderBoard.get(leaderBoard.size() - 1).getWp()) {
                            leaderBoard.add(p);
                        }
                    }
                }
            }
            LeaderBoard lb = new LeaderBoard(leaderBoard);
            EventManager.notifyListener(EventType.LEADERBOARD, lb);
        }
        else {
            if(!win) {
                LeaderBoard lb= new LeaderBoard(game.getPlayers().get(0), game.getLorenzo(), false);
                EventManager.notifyListener(EventType.LEADERBOARD, lb, null, 659);
            }
            else {
                LeaderBoard lb = new LeaderBoard(game.getPlayers().get(0), game.getLorenzo(), true);
                EventManager.notifyListener(EventType.LEADERBOARD, lb, null, 658);
            }
        }
    }

}

class AutoCheckerWait implements Runnable {

    private final Game game;
    private final Automaton fsm;
    private final int numbOfPlayers;

    /**
     * Constructor of AutoCheckerWait
     * @param game Game
     * @param fsm Automaton
     * @param numbOfPlayers int
     */
    public AutoCheckerWait(Game game, Automaton fsm, int numbOfPlayers) {
        this.game = game;
        this.fsm = fsm;
        this.numbOfPlayers = numbOfPlayers;

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (game.getPlayers().size() == numbOfPlayers && fsm.getPhase()==GamePhase.WAITING_ROOM) {
                try {
                    EventManager.notifyListener(EventType.GAMESTART, null);
                    game.initialSet();
                    fsm.evolveGamePhase();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
        }
    }
}


