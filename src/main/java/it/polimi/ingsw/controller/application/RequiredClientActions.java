package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;
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

public class RequiredClientActions {

    /**
     * represents the client socket
     */
    private final Socket clientSocket;

    /**
     * represents the player
     */
    private final Player player;

    private final String[] parameters;


    /**
     * constructor of RequiredClientActions
     * @param command Command
     * @param clientSocket Socket
     * @param player Player
     */
    public RequiredClientActions(Command command, Socket clientSocket, Player player) {
        this.clientSocket = clientSocket;
        this.player = player;
        this.parameters = command.getParameters();
    }

    /**
     * this method manages the user interactions, asking him where to take the resources from
     * (strongbox, warehouse depot or extra depot)
     * @param cmd String
     */
    public void execute(String cmd) throws EndingGameException {
        try {
            switch (cmd) {
                case "buy": //this case is activated when the player is trying to buy a new dev card
                    buyCard();
                    break;
                case "activateproduction": //this case is activated when the player has activated a development card production
                    activateProduction();
                    break;

                case "buyfrommarket": //this case is activated when the player is buying resources from the market
                    buyFromMarket();
                    break;
                case "standardproduction": //this case is activated when the player has activated the standard production
                    standardProduction();
                    break;
            }
        } catch (EndingGameException e) {
            throw new EndingGameException();
        }
    }

    /**
     * this method take any resource from Warehouse depot or Extra Depot
     * @param resource Resource
     * @param depotType String
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
                        try {
                        MessageHandler.sendMessageToClient("resource taken", clientSocket);
                        } catch (EndingGameException e){
                            throw new EndingGameException();
                        }
                        return;
                    }
                    else if (position==3) {
                        if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getExtraDepot()[0]!=null) {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(resource);
                            try {
                                MessageHandler.sendMessageToClient("resource taken", clientSocket);
                            } catch (EndingGameException e){
                                throw new EndingGameException();
                            }
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
            try {
                MessageHandler.sendMessageToClient("Insert a new resource and location", clientSocket);
            } catch (EndingGameException e){
                //
            }
            String newInput;
            newInput = MessageHandler.readClientMessage(clientSocket);
            depotType = newInput.split(" ")[1];
            resource =  new Resource(newInput.split(" ")[0]);

        } while(true);
    }

    /**
     * this method allow the player to use a resource from his/her ExtraDepot
     * @param costArray Resource[]
     * @param readResource Resource
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
     */
    private void insertResource(Resource res) throws EndingGameException {
        try {
            do {
                MessageHandler.sendMessageToClient("Select where you want to insert the new resource (warehouse depot or extra depot) or if you want to discard it", clientSocket);
                String choice = MessageHandler.readClientMessage(clientSocket).toLowerCase().replace(" ", "");
                choice = CheckCommand.commandChecker(new String[]{"warehousedepot","extradepot","discard"}, choice, clientSocket);
                switch (choice) {
                    case "warehousedepot":
                        MessageHandler.sendMessageToClient("select a line to insert the resource into", clientSocket);
                        String chosenColumn=MessageHandler.readClientMessage(clientSocket);
                        int column = CheckCommand.checkNumber(clientSocket, chosenColumn);
                        try {
                            player.getPersonalBoard().getWarehouseDepot().insertNewResource(res, column);
                            return;
                        } catch (IllegalArgumentException e) {
                            MessageHandler.sendMessageToClient(e.getMessage(), clientSocket);
                            MessageHandler.sendMessageToClient("Choose if you want to move your resources (move resources -first line -second line) or discard this resource (discard resource) otherwise just press anything to try again", clientSocket);
                            String[] action = MessageHandler.readClientMessage(clientSocket).toLowerCase().replace(" ", "").split("-");
                            String commandMove = action[0];
                            switch (commandMove){
                                case "moveresources":
                                    int line1 = CheckCommand.checkNumber(clientSocket, action[1]);
                                    int line2 = CheckCommand.checkNumber(clientSocket, action[2]);
                                    try {
                                        player.getPersonalBoard().getWarehouseDepot().moveResources(line1, line2);
                                        MessageHandler.sendMessageToClient("The selected line have been correctly switched, now insert the command again", clientSocket);
                                    } catch (IllegalArgumentException exc) {
                                        MessageHandler.sendMessageToClient(exc.getMessage(), clientSocket);
                                    }
                                    break;
                                case "discardresource":
                                    discardedResources();
                                    return;
                                }
                            }
                        break;
                    case "extradepot":
                        int position = CheckCommand.leaderCardChecker("depot", player, res);
                        if (position==0)
                            MessageHandler.sendMessageToClient("You don't have any leader card of depot", clientSocket);
                        else if (position==1 || position==2) {
                            try {
                                ((LeaderOfDepots) player.getActiveLeaderCards()[position - 1]).addNewResource(res);
                            } catch (IllegalArgumentException fail) {
                                MessageHandler.sendMessageToClient(fail.getMessage(), clientSocket);
                            }
                        }
                        else {
                            int chosenCard;
                            do {
                                MessageHandler.sendMessageToClient("Choose if you want to insert this resource either in the first card or the second card", clientSocket);
                                String select = MessageHandler.readClientMessage(clientSocket);
                                chosenCard = CheckCommand.checkNumber(clientSocket, select);
                            } while (chosenCard != 1 && chosenCard!=2);
                            ((LeaderOfDepots) player.getActiveLeaderCards()[chosenCard-1]).addNewResource(res);
                        }
                        break;

                    case "discard":
                        discardedResources();
                        return;
                }
            }
            while (true);
        } catch (EndingGameException e) {
            throw new EndingGameException();
        }
    }

    /**
     * This method read and analyze the marble array from the market for transform them into resource or to discard them
     * @param marbles MarketMarble[]
     */
    private void readMarbles (MarketMarble[] marbles) throws EndingGameException {
        Resource resource1;
        for (MarketMarble marble : marbles) {
            try {
                Resource resource = marble.returnAbility();
                insertResource(resource);
            } catch (Exception e) {
                if(e.getMessage().equals("white")) {
                    if(player.getActiveLeaderCards()[0]!=null && player.getActiveLeaderCards()[1]!=null && (player.getActiveLeaderCards()[0].getAbility()==2 || player.getActiveLeaderCards()[1].getAbility()==2)) {
                        try {
                            MessageHandler.sendMessageToClient("select if you want to transform or discard the white marble", clientSocket);
                        } catch (EndingGameException ex){
                            throw new EndingGameException();
                        }
                        try {
                            String transformChoice= MessageHandler.readClientMessage(clientSocket).toLowerCase();
                            transformChoice = CheckCommand.commandChecker(new String[]{"transform", "discard"}, transformChoice, clientSocket);
                            switch (transformChoice) {
                                case "transform": //transform marble
                                    int conversionPosition = CheckCommand.leaderCardChecker("conversion", player);
                                    if(conversionPosition==1 || conversionPosition==2) {
                                        resource1 = ((LeaderOfConversions)player.getActiveLeaderCards()[conversionPosition-1]).getConvertedResource();
                                        insertResource(resource1);
                                    }
                                    else {
                                        String selectedResource;
                                        try{
                                            MessageHandler.sendMessageToClient("You can only convert one resource at a time, select the one you prefer", clientSocket);
                                            selectedResource = MessageHandler.readClientMessage(clientSocket);
                                        } catch (EndingGameException ex){
                                            throw new EndingGameException();
                                        }
                                        Resource chosenRes = new Resource(CheckCommand.commandChecker(new String[]{((LeaderOfConversions)player.getActiveLeaderCards()[0]).getConvertedResource().toString(), ((LeaderOfConversions)player.getActiveLeaderCards()[0]).getConvertedResource().toString()}, selectedResource, clientSocket));
                                        insertResource(chosenRes);
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

    private void discardedResources(){
        player.discardedResourceUser(player);
    }

    private void buyCard() throws EndingGameException {
        Resource[] costArray = player.getConnectedGame().getCardsMarket().getCard(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1])).getCostArray();
        CheckCommand.checkDiscount(player, clientSocket, costArray);
        MessageHandler.sendMessageToClient("you can buy the selected card, please choose the resources to use", clientSocket);
        do {
            String chosenResource;
            chosenResource = MessageHandler.readClientMessage(clientSocket);
            switch(chosenResource.split(" ")[1].toLowerCase()) {
                case "warehousedepot":
                    useWarehouseResource(costArray, chosenResource);
                    break;

                case "extradepot":
                    Resource readResource = checkResource(chosenResource.split(" ")[0].toLowerCase());
                    useExtraDepotResource(costArray, readResource);
                    break;

                case "strongbox":
                    Resource resource = checkResource(chosenResource.split(" ")[0].toLowerCase());
                    for (int i = 0; i<costArray.length; i++) {
                        if (costArray[i].equals(resource)) {
                            try {
                                player.getPersonalBoard().getStrongbox().useResourceStrongbox(costArray[i]);
                                costArray[i] = null;
                            } catch (IllegalArgumentException e) {
                                MessageHandler.sendMessageToClient("This resource isn't available in your Strongbox", clientSocket);
                            }
                            break; //break for each
                        }
                        MessageHandler.sendMessageToClient("This resource isn't required", clientSocket);
                    }
                    break; //break case "strongbox"
                default:
                    MessageHandler.sendMessageToClient("select a valid resource location (Warehouse Depot, Extra Depot or Strongbox", clientSocket);
                    break;
            }
        }
        while (Arrays.equals(costArray, new Resource[costArray.length]));
        int position;
        do {
            MessageHandler.sendMessageToClient("Select a position to insert the card in", clientSocket);
            position = CheckCommand.checkNumber(clientSocket, MessageHandler.readClientMessage(clientSocket));
        } while (position<1 || position > 3);
        player.buyProductionCard(Integer.parseInt(parameters[0]),Integer.parseInt(parameters[1]), position);
        MessageHandler.sendMessageToClient("Card inserted in the selected slot", clientSocket);
    }

    private void activateProduction() throws EndingGameException {
        MessageHandler.sendMessageToClient("You can activate this production, select where you want to take the resources from", clientSocket);
        Resource[] requiredRes = player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getRequiredRes();
        do {
            String chosenResource = MessageHandler.readClientMessage(clientSocket);
            switch (chosenResource.split(" ")[1].toLowerCase()) {
                case "warehousedepot":
                    useWarehouseResource(requiredRes, chosenResource);
                    break;

                case "extradepot":
                    Resource resExtra = checkResource(chosenResource.split(" ")[0].toLowerCase()); //resExtra is the resource chosen by the client
                    useExtraDepotResource(requiredRes, resExtra);
                    break;

                default:
                    MessageHandler.sendMessageToClient("Select a valid resource location (Warehouse Depot or Extra Depot)", clientSocket);
                    break;
            }
        } while(Arrays.equals(requiredRes, new Resource[requiredRes.length]));
        for (Resource r : player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getGivenRes())
            player.getPersonalBoard().getStrongbox().insertNewResource(r);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
        MessageHandler.sendMessageToClient("Production activated and finished", clientSocket);
    }

    private void buyFromMarket() throws EndingGameException {
        MarketMarble[] marbles;
        int chosenLine = Integer.parseInt(parameters[1])-1;
        marbles = player.buyResourceFromMarket(chosenLine-1, parameters[0]);
        readMarbles(marbles);
        MessageHandler.sendMessageToClient("Resources bought", clientSocket);
    }

    private void standardProduction() throws EndingGameException {
        MessageHandler.sendMessageToClient("You can activate the standard production, select where you want tot take the resources from", clientSocket);
        int givenRes = 0;
        do {

            String chosenResource = MessageHandler.readClientMessage(clientSocket);
            switch (chosenResource.split(" ")[1].toLowerCase()) {
                case "warehousedepot":
                    anyResource(new Resource(chosenResource.split(" ")[0].toLowerCase()), "warehouse");
                    givenRes++;
                    break;

                case "extradepot":
                    Resource resExtra = checkResource(chosenResource.split(" ")[0].toLowerCase()); //resExtra is the resource chosen by the client
                    anyResource(resExtra, "extra");
                    givenRes++;
                    break;

                default:
                    MessageHandler.sendMessageToClient("Select a valid resource location (Warehouse Depot or Extra Depot)", clientSocket);
                    break;
            }
        } while (givenRes<2);
        MessageHandler.sendMessageToClient("Now choose a resource to get from the production", clientSocket);
        do {
            String choice = MessageHandler.readClientMessage(clientSocket);
            try {
                Resource res = new Resource(choice.toLowerCase());
                insertResource(res);
                break;
            } catch (IllegalArgumentException e) {
                MessageHandler.sendMessageToClient("select a valid resource", clientSocket);
            }
        } while (true);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
        MessageHandler.sendMessageToClient("Standard production finished", clientSocket);
    }

}
