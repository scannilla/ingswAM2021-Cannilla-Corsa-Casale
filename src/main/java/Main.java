import it.polimi.ingsw.controller.networkclient.ClientMain;
import it.polimi.ingsw.controller.networkserver.ServerMain;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String mode;
        try {
            mode = args[0];
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Select either -s for server or -c for client");
            return;
        }
        if(mode.equals("-s")) {
            String portNumber;
            try {
                portNumber = args[1];
                ServerMain.main(new String[] {portNumber});
            } catch (IndexOutOfBoundsException e) {
                ServerMain.main(new String[] {});
            }
        }
        else if(mode.equals("-c")) {
            ArrayList<String> clientArgs = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
            ClientMain.main(clientArgs.toArray(new String[0]));
        }
        else
            System.err.println("Select either -s for server or -c for client");
    }
}
