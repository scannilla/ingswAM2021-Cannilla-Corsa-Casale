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

    public String tryCommand(String command, Socket clientSocket, Player player) {
        String[] cmd = commandReader(command);
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
                if(player.isActive() && CheckCommand.commandChecker(fsm.validCommands(), cmd[0])) {
                    Command c = GSON.commandParser(commandCreator(command));
                    c.executeCommand();
                    if(CheckCommand.commandChecker(new String[] {"buyresource", "buyproductioncard", "cardproduction", "standardproduction"}, cmd[0])) {
                        fsm.actionDone();
                        fsm.evolveTurnPhase();
                    }
                    if(cmd[0].equals("endturn")) {
                        fsm.evolveToTurnPhase(TurnPhase.END_TURN);
                    }
                    return "ok";
                }
                return "ko";
            case END:

            case UNKNOWN:
            default:
        }
        return "ko";
    }

    private String[] commandReader(String command) {
        return command.toLowerCase().replace(" ","").split("-");
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
