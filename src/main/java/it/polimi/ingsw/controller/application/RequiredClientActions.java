package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.application.CheckCommand;
import it.polimi.ingsw.controller.application.Command;
import it.polimi.ingsw.leader.LeaderOfConversions;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.resources.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Locale;

public class RequiredClientActions {

    /**
     * represents the command sent from the client
     */
    private final Command command;

    /**
     * represents the client socket
     */
    private final Socket clientSocket;

    /**
     * represents the player
     */
    private final Player player;

    /**
     * constructor of RequiredClientActions
     * @param command Command
     * @param clientSocket Socket
     * @param player Player
     */
    public RequiredClientActions(Command command, Socket clientSocket, Player player) {
        this.command = command;
        this.clientSocket = clientSocket;
        this.player = player;
    }

    /**
     * this method manages the user interactions, asking him where to take the resources from
     * (strongbox, warehouse depot or extra depot)
     * @param cmd String
     */
    public void execute(String cmd) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String[] parameters = command.getParameters();

            switch(cmd) {
                case "buy": //this case is activated when the player is trying to buy a new dev card
                    Resource[] costArray = player.getConnectedGame().getCardsMarket().getCard(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1])).getCostArray();
                    CheckCommand.checkDiscount(player, in, out, costArray);
                    out.println("you can buy the selected card, please choose the resources to use");
                    do {
                        String chosenResource = in.readLine();

                        switch(chosenResource.split(" ")[1].toLowerCase()) {
                            case "warehousedepot":
                                useWarehouseResource(out, costArray, chosenResource);
                                break;

                            case "extradepot":
                                Resource readResource = checkResource(chosenResource.split(" ")[0].toLowerCase());
                                useExtraDepotResource(costArray, readResource, out);
                                break;

                            case "strongbox":
                                Resource resource = checkResource(chosenResource.split(" ")[0].toLowerCase());
                                for (Resource res : costArray) {
                                    if (res.equals(resource)) {
                                        try {
                                            player.getPersonalBoard().getStrongbox().useResourceStrongbox(res);
                                            res = null;
                                        } catch (IllegalArgumentException e) {
                                            out.println("This resource isn't available in your Strongbox");
                                        }
                                        break; //break for each
                                    }
                                    out.println("This resource isn't required");
                                }
                                break; //break case "strongbox"
                            default:
                                out.println("select a valid resource location (Warehouse Depot, Extra Depot or Strongbox");
                                break;
                        }
                    }
                    while (Arrays.equals(costArray, new Resource[costArray.length]));
                    int position;
                    do {
                        out.println("Select a position to insert the card in");
                        position = CheckCommand.checkNumber(in, out, in.readLine());
                    } while (position<1 || position > 3);
                    player.buyProductionCard(Integer.parseInt(parameters[0]),Integer.parseInt(parameters[1]), position);
                    break;

                case "activateproduction": //this case is activated when the player has activated a development card production
                    out.println("You can activate this production, select where you want to take the resources from");
                    Resource[] requiredRes = player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getRequiredRes();
                    do {
                        String chosenResource = in.readLine();
                        switch (chosenResource.split(" ")[1].toLowerCase()) {
                            case "warehousedepot":
                                useWarehouseResource(out, requiredRes, chosenResource);
                                break;

                            case "extradepot":
                                Resource resExtra = checkResource(chosenResource.split(" ")[0].toLowerCase()); //resExtra is the resource chosen by the client
                                useExtraDepotResource(requiredRes, resExtra, out);

                                break;

                            default:
                                out.println("Select a valid resource location (Warehouse Depot or Extra Depot)");
                                break;
                        }


                    } while(Arrays.equals(requiredRes, new Resource[requiredRes.length]));

                    break;

                case "buyfrommarket": //this case is activated when the player is buying resources from the market
                    MarketMarble[] marbles;
                    int chosenLine = Integer.parseInt(parameters[1])-1;
                    marbles = player.buyResourceFromMarket(chosenLine-1, parameters[0]);
                    readMarbles(out, in, marbles);

                case "standardproduction": //this case is activated when the player has activated the standard production
                    out.println("You can activate the standard production, select where you want tot take the resources from");
                    int givenRes = 0;
                    do {

                        String chosenResource = in.readLine();
                        switch (chosenResource.split(" ")[1].toLowerCase()) {
                            case "warehousedepot":
                                anyResource(new Resource(chosenResource.split(" ")[0].toLowerCase()), "warehouse", out, in);
                                givenRes++;
                                break;

                            case "extradepot":
                                Resource resExtra = checkResource(chosenResource.split(" ")[0].toLowerCase()); //resExtra is the resource chosen by the client
                                anyResource(new Resource(chosenResource.split(" ")[0].toLowerCase()), "extra", out, in);
                                givenRes++;
                                break;

                            default:
                                out.println("Select a valid resource location (Warehouse Depot or Extra Depot)");
                                break;
                        }
                    } while (givenRes<2);
                    out.println("Now choose a resource to get from the production");
                    do {
                        String choice = in.readLine();
                        try {
                            Resource res = new Resource(choice.toLowerCase());
                            insertResource(res, out, in);
                            break;
                        } catch (IllegalArgumentException e) {
                            out.println("select a valid resource");
                        }
                    } while (true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method take any resource from Warehouse depot or Extra Depot
     * @param resource Resource
     * @param depotType String
     * @param out PrintWriter
     * @param in BufferedReader
     */
    private void anyResource(Resource resource, String depotType, PrintWriter out, BufferedReader in) {
        do {
            switch (depotType) {
                case "warehouse":
                    for (int i=0; i<3; i++) {
                        if (player.getPersonalBoard().getWarehouseDepot().checkResource(i).equals(resource)) {
                            player.getPersonalBoard().getWarehouseDepot().useResource(resource);
                            out.println("resource taken");
                            return;
                        }
                    }
                    out.println("You don't have this resource in your warehouse depot, select a new resource and location");
                    break;
                case "extra":
                    int position = CheckCommand.leaderCardChecker("depot", player, resource);
                    if(position==0)
                        out.println("You don't have any active Leader of Depots card");
                    else if ((position==1 || position ==2 ) && ((LeaderOfDepots)player.getActiveLeaderCards()[position-1]).getExtraDepot()[0]!=null) {
                        ((LeaderOfDepots)player.getActiveLeaderCards()[position-1]).useResource(resource);
                        out.println("resource taken");
                        return;
                    }
                    else if (position==3) {
                        if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getExtraDepot()[0]!=null) {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(resource);
                            out.println("resource taken");
                            return;
                        }
                        else if (((LeaderOfDepots)player.getActiveLeaderCards()[1]).getExtraDepot()[0]!=null){
                            ((LeaderOfDepots) player.getActiveLeaderCards()[1]).useResource(resource);
                            out.println("resource taken");
                            return;
                        }
                    }
                    break;
            }
            out.println("Insert a new resource and location");
            String newInput = null;
            try {
                newInput = in.readLine();
            } catch (IOException e) {
                System.exit(1);
            }
            depotType = newInput.split(" ")[1];
            resource =  new Resource(newInput.split(" ")[0]);

        } while(true);
    }

    /**
     * this method allow the player to use a resource from his/her ExtraDepot
     * @param costArray Resource[]
     * @param readResource Resource
     * @param out PrintWriter
     */
    private void useExtraDepotResource(Resource[] costArray, Resource readResource, PrintWriter out) {
        boolean done = false;
        for (int i = 0; i< costArray.length; i++) {
            if (readResource.equals(costArray[i])) {
                if (player.getActiveLeaderCards()[0]!=null && player.getActiveLeaderCards()[0].getAbility() == 1) {
                    if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getResource().equals(costArray[i])) {
                        try {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(costArray[i]);
                           costArray[i] = null;
                            done = true;
                        } catch (IllegalArgumentException e){
                            out.println(e.getMessage());
                        }
                        }
                }
                if (player.getActiveLeaderCards()[1] != null && player.getActiveLeaderCards()[1].getAbility() == 1 && !done) {
                    if(((LeaderOfDepots)player.getActiveLeaderCards()[1]).getResource().equals(costArray[i])) {
                        try {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[1]).useResource(costArray[i]);
                            costArray[i] = null;
                            done = true;
                        } catch (IllegalArgumentException e){
                            out.println(e.getMessage());
                        }
                    }
                }
            }
            if(done)
                break;
        }
    }

    /**
     * this method allow the player to use a resource from his/her Warehouse Depot
     * @param out PrintWriter
     * @param requiredRes Resource[]
     * @param chosenResource String
     */
    private void useWarehouseResource(PrintWriter out, Resource[] requiredRes, String chosenResource) {
        try {
            Resource resource = checkResource(chosenResource.split(" ")[0].toLowerCase());
            for (int i=0; i< requiredRes.length; i++) {
                if (requiredRes[i].equals(resource)) {
                    try {
                        player.getPersonalBoard().getWarehouseDepot().useResource(requiredRes[i]);
                        out.println("Resource used");
                        requiredRes[i] = null;
                    } catch (IllegalArgumentException e) {
                        out.println("This resource isn't available in your warehouse depot");
                    }
                    break;
                }
                out.println("This resource isn't required");
            }

        } catch (IllegalArgumentException e) {
            out.println("Select a valid resource (Coin, Stone, Servant or Shield)");
        }
    }

    /**
     * this method checks if the selected resource is valid and creates a new resource
     * @param resource String
     * @throws IllegalArgumentException e
     * @return new Resource(resource) Resource
     */
    private Resource checkResource(String resource) {
        if(resource.equals("coin") || resource.equals("stone") || resource.equals("servant") || resource.equals("shield") )
            return new Resource(resource);
        else
            throw new IllegalArgumentException();
    }

    /**
     * Ask client where insert the new Resource
     * @param res Resource
     * @param out PrintWriter
     * @param in BufferedReader
     */
    private void insertResource(Resource res, PrintWriter out, BufferedReader in) {
        try {
            do {
                out.println("Select where you want to insert the new resource (warehouse depot or extra depot) or if you want to discard it");
                String choice = in.readLine().toLowerCase().replace(" ", "");
                choice = CheckCommand.commandChecker(new String[]{"warehousedepot","extradepot","discard"}, choice, in, out);
                switch (choice) {
                    case "warehousedepot":
                        out.println("select a line to insert the resource into");
                        String chosenColumn=in.readLine();
                        int column = CheckCommand.checkNumber(in, out, chosenColumn);
                        try {
                            player.getPersonalBoard().getWarehouseDepot().insertNewResource(res, column);
                            return;
                        } catch (IllegalArgumentException e) {
                            out.println(e.getMessage());
                            out.println("Choose if you want to move your resources (move resources -first line -second line) or discard this resource (discard resource) otherwise just press anything to try again");
                            String[] action = in.readLine().toLowerCase().replace(" ", "").split("-");
                            String commandMove = action[0];
                            switch (commandMove){
                                case "moveresources":
                                    int line1 = CheckCommand.checkNumber(in, out, action[1]);
                                    int line2 = CheckCommand.checkNumber(in, out, action[2]);
                                    try {
                                        player.getPersonalBoard().getWarehouseDepot().moveResources(line1, line2);
                                        out.println("The selected line have been correctly switched, now insert the command again");
                                    } catch (IllegalArgumentException exc) {
                                        out.println(exc.getMessage());
                                    }
                                    break;
                                case "discardresource":
                                    discardedResources();
                                    return;
                                }
                            }
                        break;
                    case "extradepot":
                        break;

                    case "discard":
                        discardedResources();
                        return;
                }
            }
            while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method read and analyze the marble array from the market for transform them into resource or to discard them
     * @param out PrintWriter
     * @param in BufferedReader
     * @param marbles MarketMarble[]
     */
    private void readMarbles (PrintWriter out, BufferedReader in, MarketMarble[] marbles){
        Resource resource1;
        for (MarketMarble marble : marbles) {
            try {
                Resource resource = marble.returnAbility();
                insertResource(resource, out, in);
            } catch (Exception e) {
                if(e.getMessage().equals("white")) {
                    if(player.getActiveLeaderCards()[0]!=null && player.getActiveLeaderCards()[1]!=null && (player.getActiveLeaderCards()[0].getAbility()==2 || player.getActiveLeaderCards()[1].getAbility()==2)) {
                        out.println("select if you want to transform or discard the white marble");
                        try {
                            String transformChoice= in.readLine().toLowerCase();
                            transformChoice = CheckCommand.commandChecker(new String[]{"transform", "discard"}, transformChoice, in, out);
                            switch (transformChoice) {
                                case "transform": //transform marble
                                    int conversionPosition = CheckCommand.leaderCardChecker("conversion", player);
                                    if(conversionPosition==1 || conversionPosition==2) {
                                        resource1 = ((LeaderOfConversions)player.getActiveLeaderCards()[conversionPosition-1]).getConvertedResource();
                                        insertResource(resource1, out, in);
                                    }
                                    else {
                                        out.println("You can only convert one resource at a time, select the one you prefer");
                                        String selectedResource = in.readLine().toLowerCase();
                                        Resource chosenRes = new Resource(CheckCommand.commandChecker(new String[]{((LeaderOfConversions)player.getActiveLeaderCards()[0]).getConvertedResource().toString(), ((LeaderOfConversions)player.getActiveLeaderCards()[0]).getConvertedResource().toString()}, selectedResource, in, out));
                                        insertResource(chosenRes, out, in);
                                    }
                                    break;
                                case "discard":
                                    out.println("White marble discarded");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    }
                }
                else {
                    out.println("faith increased");
                }
            }
        }
    }
}
