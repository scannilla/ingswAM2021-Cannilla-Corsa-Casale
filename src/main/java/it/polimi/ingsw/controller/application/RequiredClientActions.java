package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.controller.networkserver.Response;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;
import it.polimi.ingsw.leader.LeaderOfConversions;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.leader.LeaderOfProductions;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;

import java.util.Arrays;

public class RequiredClientActions {


    /**
     * represents the player
     */
    private final Player player;

    private final String[] parameters;

    private final MessageHandler mHandler;


    /**
     * constructor of RequiredClientActions
     * @param command Command
     * @param player Player
     * @param mHandler MessageHandler
     */
    public RequiredClientActions(Command command, Player player, MessageHandler mHandler) {

        this.player = player;
        this.parameters = command.getParameters();
        this.mHandler = mHandler;
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
                    player.setActionDone(true);
                    buyCard();
                    EventManager.notifyListener(EventType.CARDMARKET, player.getConnectedGame().getCardsMarket());
                    EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
                    break;

                case "production": //this case is activated when the player has activated a development card production
                    activateProduction();
                    EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
                    break;

                case "market": //this case is activated when the player is buying resources from the market
                    player.setActionDone(true);
                    buyFromMarket();
                    EventManager.notifyListener(EventType.MARKET, player.getConnectedGame().getMarket());
                    EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
                    break;

                case "standard": //this case is activated when the player has activated the standard production
                    player.setActionDone(true);
                    standardProduction();
                    EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
                    break;

                case "leaderprod":
                    leaderProduction();
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
    private void anyResource(Resource resource, String depotType) throws EndingGameException {
        do {
            switch (depotType) {
                case "warehousedepot":
                    for (int i=0; i<3; i++) {
                        if (resource.equals(player.getPersonalBoard().getWarehouseDepot().checkResource(i))) {
                            try {
                                player.getPersonalBoard().getWarehouseDepot().useResource(resource);
                                sendResponse("resource taken", 129);
                                return;
                            } catch (IllegalArgumentException e) {
                                sendResponse("You don't have this resource in your warehouse depot, select a new resource and location", 431);
                            }
                        }
                    }
                    break;
                case "extradepot":
                    int position = CheckCommand.leaderCardChecker("depot", player, resource);
                    if(position==0)
                        sendResponse("You don't have any active Leader of Depots card", 432);
                    else if ((position==1 || position ==2 ) && ((LeaderOfDepots)player.getActiveLeaderCards()[position-1]).getExtraDepot()[0]!=null) {
                        try {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[position - 1]).useResource(resource);
                            sendResponse("resource taken", 129);
                            return;
                        } catch (IllegalArgumentException e1) {
                            sendResponse("You don't have this resource in your extra depot, select a new depot and resource", 431);
                        }
                    }
                    else if (position==3) {
                        if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getExtraDepot()[0]!=null) {
                            try {
                                ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(resource);
                                sendResponse("resource taken", 129);
                                return;
                            } catch (IllegalArgumentException e2) {
                                sendResponse("You don't have this resource in your extra depot, select a new depot and resource", 431);
                            }
                        }
                        else if (((LeaderOfDepots)player.getActiveLeaderCards()[1]).getExtraDepot()[0]!=null){
                            try {
                                ((LeaderOfDepots) player.getActiveLeaderCards()[1]).useResource(resource);
                                sendResponse("resource taken", 129);
                                return;
                            } catch (IllegalArgumentException e3) {
                                sendResponse("You don't have this resource in your extra depot, select a new depot and resource", 431);
                            }
                        }
                    }
                    break;
                case "strongbox":
                    try {
                        player.getPersonalBoard().getStrongbox().useResourceStrongbox(resource);
                        return;
                    } catch (IllegalArgumentException e) {
                        sendResponse("You don't have this resource in your strongbox, select a new depot and resource", 431);
                    }
                    break;
            }
            depotType = selectDepot(new String[] {"Warehouse Depot", "Extra Depot", "Strongbox"});
            resource = selectResource();
        } while(true);
    }

    /**
     * this method allow the player to use a resource from his/her ExtraDepot
     * @param costArray Resource[]
     * @param readResource Resource
     */
    private void useExtraDepotResource(Resource[] costArray, Resource readResource) throws EndingGameException {
        boolean done = false;
        for (int i = 0; i< costArray.length; i++) {
            if (readResource.equals(costArray[i])) {
                if (player.getActiveLeaderCards()[0]!=null && player.getActiveLeaderCards()[0].getAbility() == 1) {
                    if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getResource().equals(readResource)) {
                        try {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(costArray[i]);
                            EventManager.notifyListener(EventType.LEADERCARD, player.getActiveLeaderCards(), player.getNickname());
                            costArray[i] = null;
                            done = true;
                        } catch (IllegalArgumentException e){
                            sendResponse("You don't have this resource in your extra depot", 431);
                        }
                    }
                }
                if (player.getActiveLeaderCards()[1] != null && player.getActiveLeaderCards()[1].getAbility() == 1 && !done) {
                    if(((LeaderOfDepots)player.getActiveLeaderCards()[1]).getResource().equals(readResource)) {
                        try {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[1]).useResource(costArray[i]);
                            EventManager.notifyListener(EventType.LEADERCARD, player.getActiveLeaderCards(), player.getNickname());
                            costArray[i] = null;
                            done = true;
                        } catch (IllegalArgumentException e){
                            sendResponse("You don't have this resource in your extra depot", 431);
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
    private void useWarehouseResource(Resource[] requiredRes, Resource chosenResource) throws EndingGameException {
        for (int i=0; i< requiredRes.length; i++) {
            if (chosenResource.equals(requiredRes[i])) {
                try {
                    player.getPersonalBoard().getWarehouseDepot().useResource(requiredRes[i]);
                    sendResponse("Resource used", 130);
                    requiredRes[i] = null;
                    EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard(), player.getNickname());
                    return;
                } catch (IllegalArgumentException e) {
                    sendResponse("This resource isn't available in your warehouse depot", 434);
                    return;
                }
            }
        }
        sendResponse("This resource isn't required", 435);
    }

    private void useStrongboxResource(Resource[] requiredRes, Resource chosenResource) throws EndingGameException {
        for (int i = 0; i < requiredRes.length; i++) {
            if(chosenResource.equals(requiredRes[i])) {
                try {
                    player.getPersonalBoard().getStrongbox().useResourceStrongbox(chosenResource);
                    sendResponse("Resource used", 130);
                    requiredRes[i] = null;
                    EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard(), player.getNickname());
                    return;
                } catch (IllegalArgumentException e) {
                    sendResponse("This resource isn't available in your strongbox", 434);
                }
            }
        }
        sendResponse("This resource isn't required", 435);
    }

    /**
     * Ask client where insert the new Resource
     * @param res Resource
     */
    private void insertResource(Resource res) throws EndingGameException {
        try {
            do {
                mHandler.drawResource(res, player.getNickname());
                sendResponse("Select where you want to insert the new resource (warehouse depot or extra depot) or if you want to discard it", 198);
                String choice = mHandler.readClientMessage().toLowerCase().replace(" ", "");
                choice = CheckCommand.commandChecker(new String[]{"warehousedepot","extradepot","discard"}, choice, mHandler);
                switch (choice) {
                    case "warehousedepot":
                        sendResponse("Select a line to insert the resource into", 197);
                        String chosenColumn = mHandler.readClientMessage();
                        int column = CheckCommand.checkNumber(chosenColumn,  mHandler);
                        try {
                            player.getPersonalBoard().getWarehouseDepot().insertNewResource(res, column-1);
                            EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard(), player.getNickname());
                            return;
                        } catch (IllegalArgumentException e) {
                            sendResponse(e.getMessage(), 436);
                            sendResponse("Choose if you want to move your resources (move resources -first line -second line) or discard this resource (discard resource) otherwise just press anything to try again", 133);
                            String[] action = mHandler.readClientMessage().toLowerCase().replace(" ", "").split("-");
                            String commandMove = action[0];
                            switch (commandMove){
                                case "moveresources":
                                    int line1;
                                    int line2;
                                    try {
                                        line1 = CheckCommand.checkNumber(action[1], mHandler);
                                        line2 = CheckCommand.checkNumber(action[2], mHandler);
                                    } catch (IndexOutOfBoundsException e2) {
                                        sendResponse("Missing parameters", 409);
                                        break;
                                    }
                                    try {
                                        player.getPersonalBoard().getWarehouseDepot().moveResources(line1-1, line2-1);
                                        sendResponse("The selected line have been correctly switched, now insert the command again", 133);
                                        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard(), player.getNickname());
                                    } catch (IllegalArgumentException exc) {
                                        sendResponse(exc.getMessage(), 437);
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
                            sendResponse("You don't have any leader card of depot", 432);
                        else if (position==1 || position==2) {
                            try {
                                ((LeaderOfDepots) player.getActiveLeaderCards()[position - 1]).addNewResource(res);
                                EventManager.notifyListener(EventType.LEADERCARD, player.getActiveLeaderCards(), player.getNickname());
                                return;
                            } catch (IllegalArgumentException fail) {
                                sendResponse(fail.getMessage(), 438);
                            }
                        }
                        else {
                            int chosenCard;
                            do {
                                sendResponse("Choose if you want to insert this resource either in the first card or the second card", 194);
                                String select =  mHandler.readClientMessage();
                                chosenCard = CheckCommand.checkNumber(select,  mHandler);
                            } while (chosenCard != 1 && chosenCard!=2);
                            try {
                                ((LeaderOfDepots) player.getActiveLeaderCards()[chosenCard - 1]).addNewResource(res);
                                EventManager.notifyListener(EventType.LEADERCARD, player.getActiveLeaderCards(), player.getNickname());
                                return;
                            } catch (IllegalArgumentException e) {
                                sendResponse(e.getMessage(), 438);
                            }
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
            mHandler.drawMarble(marble, player.getNickname());
            try {
                Resource resource = marble.returnAbility();
                insertResource(resource);
            } catch (Exception e) {
                if(e.getMessage().equals("white")) {
                    if(player.getActiveLeaderCards()[0]!=null && player.getActiveLeaderCards()[1]!=null && (player.getActiveLeaderCards()[0].getAbility()==2 || player.getActiveLeaderCards()[1].getAbility()==2)) {
                        sendResponse("Select if you want to transform or discard the white marble", 193);
                        try {
                            String transformChoice=  mHandler.readClientMessage().toLowerCase();
                            transformChoice = CheckCommand.commandChecker(new String[]{"transform", "discard"}, transformChoice, mHandler);
                            switch (transformChoice) {
                                case "transform": //transform marble
                                    int conversionPosition = CheckCommand.leaderCardChecker("conversion", player);
                                    if(conversionPosition==1 || conversionPosition==2) {
                                        resource1 = ((LeaderOfConversions)player.getActiveLeaderCards()[conversionPosition-1]).getConvertedResource();
                                        insertResource(resource1);
                                    }
                                    else {
                                        int card;
                                        do {
                                            sendResponse("You can only convert one resource at a time, select the leader card you prefer", 194);
                                            card = CheckCommand.checkNumber(mHandler.readClientMessage(), mHandler);
                                        } while (card!=1 && card!=2);
                                        insertResource(((LeaderOfConversions)player.getActiveLeaderCards()[card-1]).getConvertedResource());
                                    }
                                    break;
                                case "discard":
                                    sendResponse("White marble discarded", 137);
                            }
                        } catch (EndingGameException ex) {
                           throw new EndingGameException();
                        }

                    }
                }
                else {
                    try {
                        mHandler.sendMessageToClient("faith increased", 149);
                    } catch (EndingGameException ex){
                        throw new EndingGameException();
                    }
                }
            }
        }
    }

    private void discardedResources(){
        player.discardedResourceUser(player);
    }

    private void buyCard() throws EndingGameException {
        Resource[] costArray = player.getConnectedGame().getCardsMarket().getTopCards()[Integer.parseInt(parameters[0])-1][Integer.parseInt(parameters[1])-1].getCostArray();
        boolean discountActivated = false;
        boolean done;
        CheckCommand.checkDiscount(player, costArray, mHandler);
        for (Resource resource : costArray) {
            if (resource == null) {
                discountActivated = true;
                break;
            }
        }
        if(discountActivated) {
            int extraDepots = CheckCommand.leaderCardChecker("depot", player);
            int[] extraDepotRes1 = new int[4];
            int[] extraDepotRes2;
            if(extraDepots==1 || extraDepots==2)
                extraDepotRes1 = ResourceCounter.resCount(((LeaderOfDepots)player.getActiveLeaderCards()[extraDepots-1]).getExtraDepot());
            else if(extraDepots==3){
                extraDepotRes1 = ResourceCounter.resCount(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getExtraDepot());
                extraDepotRes2 = ResourceCounter.resCount(((LeaderOfDepots)player.getActiveLeaderCards()[1]).getExtraDepot());
                for (int i = 0; i < 4; i++) {
                    extraDepotRes1[i] += extraDepotRes2[i];
                }
            }
            int[] price = ResourceCounter.resCount(costArray);
            for (int i=0; i<4; i++) {
                if(player.getPersonalBoard().getWarehouseDepot().getDepotResourceAmount()[i]+player.getPersonalBoard().getStrongbox().getStrongboxResourcesAmount()[i]+
                        extraDepotRes1[i] < price[i])
                    sendResponse("You don't have enough resources using the discount", 412);
            }
        }
        sendResponse("You can buy the selected card, please choose the resources to use", 120);
        do {
            String chosenDepot = selectDepot(new String[] {"Warehouse depot", "Extra Depot", "Strongbox"});
            Resource chosenResource = selectResource();
            switch(chosenDepot) {
                case "warehousedepot":
                    useWarehouseResource(costArray, chosenResource);
                    break;

                case "extradepot":
                    useExtraDepotResource(costArray, chosenResource);
                    break;

                case "strongbox":
                    for (int i = 0; i<costArray.length; i++) {
                        if (costArray[i].equals(chosenResource)) {
                            try {
                                player.getPersonalBoard().getStrongbox().useResourceStrongbox(costArray[i]);
                                costArray[i] = null;
                            } catch (IllegalArgumentException e) {
                                sendResponse("This resource isn't available in your Strongbox", 428);
                            }
                            break; //break for each
                        }
                        sendResponse("This resource isn't required", 429);
                    }
                    break; //break case "strongbox"
                default:
                    sendResponse("select a valid resource location (Warehouse Depot, Extra Depot or Strongbox", 430);
                    break;
            }
            done = true;
            for (Resource resource : costArray) {
                if (resource != null) {
                    done = false;
                    break;
                }
            }
        }
        while (!done);
        int position;
        do {
            sendResponse("Select a position to insert the card in", 121);
            position = CheckCommand.checkNumber(mHandler.readClientMessage(), mHandler);
        } while (position<1 || position > 3);
        try {
            player.buyProductionCard(Integer.parseInt(parameters[0])-1,Integer.parseInt(parameters[1])-1, position);
        } catch (IllegalArgumentException e) {
            sendResponse("so na sega io", 400);
        }
        sendResponse("Card inserted in the selected slot", 122);
    }

    private void activateProduction() throws EndingGameException {
        sendResponse("You can activate this production, select where you want to take the resources from", 122);
        Resource[] requiredRes = player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getRequiredRes();
        do {
            Resource chosenResource = selectResource();
            switch (selectDepot(new String[] {"warehouse depot", "extra depot", "strongbox"})) {
                case "warehousedepot":
                    useWarehouseResource(requiredRes, chosenResource);
                    break;

                case "extradepot":
                    useExtraDepotResource(requiredRes, chosenResource);
                    break;

                case "strongbox":
                    useStrongboxResource(requiredRes, chosenResource);
                default:
                    sendResponse("Select a valid resource location (Warehouse Depot, Extra Depot or strongbox)", 430);
                    break;
            }
        } while(Arrays.equals(requiredRes, new Resource[requiredRes.length]));
        for (Resource r : player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getGivenRes())
            player.getPersonalBoard().getStrongbox().insertNewResource(r);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
        sendResponse("Production activated and finished", 123);
    }

    private void buyFromMarket() throws EndingGameException {
        MarketMarble[] marbles;
        int chosenLine = Integer.parseInt(parameters[1])-1;
        marbles = player.buyResourceFromMarket(chosenLine, parameters[0]);
        readMarbles(marbles);
        sendResponse("Resources bought", 124);
    }

    private void standardProduction() throws EndingGameException {
        sendResponse("You can activate the standard production, select where you want to take the resources from (warehouse depot or extra depot)", 125);
        int givenRes = 0;
        do {
            String chosenLocation =  selectDepot(new String[] {"Warehouse depot", "Extra Depot", "Strongbox"});
            Resource chosenResource = selectResource();
            anyResource(chosenResource, chosenLocation);
            givenRes++;
        } while (givenRes<2);
        sendResponse("Now choose a resource to get from the production", 126);
        Resource choice = selectResource();
        player.getPersonalBoard().getStrongbox().insertNewResource(choice);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
        sendResponse("Standard production finished", 126);
    }

    private void leaderProduction() throws EndingGameException{
        LeaderOfProductions chosenCard = (LeaderOfProductions)player.getActiveLeaderCards()[Integer.parseInt(parameters[0])];
        mHandler.sendMessageToClient("You can activate this production", 127);
        Resource[] requiredRes = new Resource[1];
        requiredRes[0] = chosenCard.getRequiredResource();
        do {
            sendResponse("Select where you want to take the required resource from", 127);
            String selectedDepot = selectDepot(new String[] {"Warehouse depot", "Extra depot", "Strongbox"});
            if (selectedDepot.equals("warehousedepot")) {
                useWarehouseResource(requiredRes, chosenCard.getRequiredResource());
            }
            else
                useExtraDepotResource(requiredRes, chosenCard.getRequiredResource());
        } while (requiredRes[0]!=null);
        mHandler.sendMessageToClient("Faith increased, select now the resource you want", 128);
        player.increaseFaith(1);
        Resource resource = selectResource();
        player.getPersonalBoard().getStrongbox().insertNewResource(resource);
        mHandler.sendMessageToClient("Production activated!", 129);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
    }

    private void sendResponse(String message, int code) throws EndingGameException {
        mHandler.sendMessageToClient(new Response(message, code));
    }

    private Resource selectResource() throws EndingGameException {
        sendResponse("Select a resource", 196);
        String chosenResource = CheckCommand.commandChecker(new String[] {"Coin", "Stone", "Servant", "Shield"}, mHandler.readClientMessage(), mHandler);
        return new Resource(chosenResource);
    }

    private String selectDepot(String[] depots) throws EndingGameException {
        sendResponse("Select a depot", 195);
        return CheckCommand.commandChecker(depots, mHandler.readClientMessage(), mHandler);
    }
}
