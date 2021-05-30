package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;

import java.net.Socket;

public class CommandsHandler {
    private static CommandsHandler instance;
    private static Automaton fsm;
    private static Game game;

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
     * @param clientSocket Socket
     * @param player Player
     * @return ok or ko
     * @throws EndingGameException e
     */
    public String tryCommand(String[] cmd, Socket clientSocket, Player player) throws EndingGameException {
        GamePhase phase = fsm.getPhase();
        if(!game.getPlayers().contains(player))
            game.addPlayer(player);
        switch (phase) {
            case ACCEPTANCE:
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    fsm.evolveGamePhase();
                    return "ok";
                }
                return "ko";
            case GAME_CREATOR:
                int numbOfPlayers;
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    numbOfPlayers = new GameCreator(clientSocket).createGame(game);
                    new Thread(new AutoCheckerWait(game, fsm, numbOfPlayers)).start();
                    fsm.evolveGamePhase();
                    return "ok";
                }
                return "ko";
            case WAITING_ROOM:
                return "ko";
            case GAME_SETUP:
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    new GameSetup(clientSocket, game, player).gameSetUp(game.getPlayers().indexOf(player));
                    return "ok";
                }
                return "ko";
            case GAME_PHASE:
                Command c = GSON.commandParser(cmd[1]);
                c.setCommandPlayer(player);
                String returnValue = c.executeCommand();
                if (returnValue.contains("$"))
                    new RequiredClientActions(c, clientSocket, player).execute(returnValue.replace("$",""));

                return "ko";
            case END:
            case UNKNOWN:
            default:
        }
        return "ko";
    }

}

class AutoCheckerWait implements Runnable {

    private final Game game;
    private final Automaton fsm;
    private final int numbOfPlayers;

    /**
     * Conctructor of AutoCheckerWait
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
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (game.getPlayers().size() == numbOfPlayers && fsm.getPhase()==GamePhase.WAITING_ROOM) {
                fsm.evolveGamePhase();
                return;
            }
        }
    }
}


