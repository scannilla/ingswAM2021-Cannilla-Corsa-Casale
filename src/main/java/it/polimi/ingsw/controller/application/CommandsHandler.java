package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.net.Socket;

public class CommandsHandler {
    private static CommandsHandler instance;
    private static Automaton fsm;
    private static Game game;

    private CommandsHandler(Automaton fsm) {
        CommandsHandler.fsm = fsm;
        game = new Game();
    }

    public static CommandsHandler instanceCreator(Automaton fsm) {
        if(instance==null)
            instance = new CommandsHandler(fsm);
        return instance;
    }

    public String tryCommand(String[] cmd, Socket clientSocket, Player player) {
        GamePhase phase = fsm.getPhase();
        switch (phase) {
            case ACCEPTANCE:
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    fsm.evolveGamePhase();
                    return "ok";
                }
                return "ko";
            case GAME_CREATOR:
                if(CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    new GameCreator(clientSocket).createGame(game);
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
