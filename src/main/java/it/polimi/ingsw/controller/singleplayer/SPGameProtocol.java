package it.polimi.ingsw.controller.singleplayer;

import it.polimi.ingsw.controller.EndingGameException;

public class SPGameProtocol implements Runnable{

    private SPMessageHandler handler;

    @Override
    public void run() {
        try {
            handler = new SPMessageHandler();
        } catch (EndingGameException e) {
            System.err.println("If you see this exception check if your keyboard is connected and try again");
        }
        String command;
        String jsonString;
        while(true) {
            command = handler.readClientMessage();
            jsonString = createCommand(command);
        }
    }

    private static String createCommand(String cmd){
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
