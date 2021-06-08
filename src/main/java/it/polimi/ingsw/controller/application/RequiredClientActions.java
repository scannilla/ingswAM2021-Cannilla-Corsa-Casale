package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;
import it.polimi.ingsw.leader.LeaderOfConversions;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.leader.LeaderOfProductions;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.resources.Resource;

import java.net.Socket;
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
                case "warehouse":
                    for (int i=0; i<3; i++) {
                        if (player.getPersonalBoard().getWarehouseDepot().checkResource(i).equals(resource)) {
                            player.getPersonalBoard().getWarehouseDepot().useResource(resource);
                            try {
                                mHandler.sendMessageToClient("resource taken");
                            } catch (EndingGameException e){
                                throw new EndingGameException();
                            }
                            return;
                        }
                    }
                    try {
                        mHandler.sendMessageToClient("You don't have this resource in your warehouse depot, select a new resource and location");
                    } catch (EndingGameException e){
                        throw new EndingGameException();
                    }

                    break;
                case "extra":
                    int position = CheckCommand.leaderCardChecker("depot", player, resource);
                    if(position==0)
                        try {
                            mHandler.sendMessageToClient("You don't have any active Leader of Depots card");
                        } catch (EndingGameException e){
                            throw new EndingGameException();
                        }
                        else if ((position==1 || position ==2 ) && ((LeaderOfDepots)player.getActiveLeaderCards()[position-1]).getExtraDepot()[0]!=null) {
                        ((LeaderOfDepots)player.getActiveLeaderCards()[position-1]).useResource(resource);
                        try {
                        mHandler.sendMessageToClient("resource taken");
                        } catch (EndingGameException e){
                            throw new EndingGameException();
                        }
                        return;
                    }
                    else if (position==3) {
                        if(((LeaderOfDepots)player.getActiveLeaderCards()[0]).getExtraDepot()[0]!=null) {
                            ((LeaderOfDepots) player.getActiveLeaderCards()[0]).useResource(resource);
                            try {
                                mHandler.sendMessageToClient("resource taken");
                            } catch (EndingGameException e){
                                throw new EndingGameException();
                            }
                            return;
                        }
                        else if (((LeaderOfDepots)player.getActiveLeaderCards()[1]).getExtraDepot()[0]!=null){
                            ((LeaderOfDepots) player.getActiveLeaderCards()[1]).useResource(resource);
                            try {
                                mHandler.sendMessageToClient("resource taken");
                            } catch (EndingGameException e){
                                throw new EndingGameException();
                            }
                            return;
                        }
                    }
                    break;
            }
            try {
                mHandler.sendMessageToClient("Insert a new resource and location");
            } catch (EndingGameException e){
                //
            }
            String newInput;
            newInput = mHandler.readClientMessage();
            depotType = newInput.split(" ")[1];
            resource =  new Resource(newInput.split(" ")[0]);

        } while(true);
    }

    /**
     * this method allow the player to use a resource from his/her ExtraDepot
     * @param costArray Resource[]
     * @param readResource Resource
     */
    private void useExtraDepotResource(Resource[] costArray, Resource readResource) {
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
                            try {
                                mHandler.sendMessageToClient(e.getMessage());
                            } catch (EndingGameException endingGameException) {
                                endingGameException.printStackTrace();
                            }
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
                            try {
                                mHandler.sendMessageToClient(e.getMessage());
                            } catch (EndingGameException endingGameException) {
                                endingGameException.printStackTrace();
                            }
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
    private void useWarehouseResource(Resource[] requiredRes, String chosenResource) throws EndingGameException {
        try {
            Resource resource = checkResource(chosenResource.split(" ")[0].toLowerCase());
            for (int i=0; i< requiredRes.length; i++) {
                if (requiredRes[i].equals(resource)) {
                    try {
                        player.getPersonalBoard().getWarehouseDepot().useResource(requiredRes[i]);
                        try {
                            mHandler.sendMessageToClient("Resource used");
                        } catch (EndingGameException e){
                            throw new EndingGameException();
                        }
                        requiredRes[i] = null;
                    } catch (IllegalArgumentException e) {
                        try {
                            mHandler.sendMessageToClient("This resource isn't available in your warehouse depot");
                        } catch (EndingGameException ex){
                            throw new EndingGameException();
                        }
                    }
                    break;
                }
                try {
                    mHandler.sendMessageToClient("This resource isn't required");
                } catch (EndingGameException e){
                    throw new EndingGameException();
                }
            }
        } catch (IllegalArgumentException | EndingGameException e) {
            try{
                mHandler.sendMessageToClient("Select a valid resource (Coin, Stone, Servant or Shield)");
            } catch (EndingGameException ex){
                throw new EndingGameException();
            }

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
                mHandler.sendMessageToClient("Select where you want to insert the new resource (warehouse depot or extra depot) or if you want to discard it");
                String choice = mHandler.readClientMessage().toLowerCase().replace(" ", "");
                choice = CheckCommand.commandChecker(new String[]{"warehousedepot","extradepot","discard"}, choice, mHandler);
                switch (choice) {
                    case "warehousedepot":
                        mHandler.sendMessageToClient("select a line to insert the resource into");
                        String chosenColumn=mHandler.readClientMessage();
                        int column = CheckCommand.checkNumber(chosenColumn,  mHandler);
                        try {
                            player.getPersonalBoard().getWarehouseDepot().insertNewResource(res, column);
                            return;
                        } catch (IllegalArgumentException e) {
                            mHandler.sendMessageToClient(e.getMessage());
                            mHandler.sendMessageToClient("Choose if you want to move your resources (move resources -first line -second line) or discard this resource (discard resource) otherwise just press anything to try again");
                            String[] action = mHandler.readClientMessage().toLowerCase().replace(" ", "").split("-");
                            String commandMove = action[0];
                            switch (commandMove){
                                case "moveresources":
                                    int line1 = CheckCommand.checkNumber(mHandler.readClientMessage(),  mHandler);
                                    int line2 = CheckCommand.checkNumber( mHandler.readClientMessage(),  mHandler);
                                    try {
                                        player.getPersonalBoard().getWarehouseDepot().moveResources(line1, line2);
                                        mHandler.sendMessageToClient("The selected line have been correctly switched, now insert the command again");
                                    } catch (IllegalArgumentException exc) {
                                        mHandler.sendMessageToClient(exc.getMessage());
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
                            mHandler.sendMessageToClient("You don't have any leader card of depot");
                        else if (position==1 || position==2) {
                            try {
                                ((LeaderOfDepots) player.getActiveLeaderCards()[position - 1]).addNewResource(res);
                            } catch (IllegalArgumentException fail) {
                                mHandler.sendMessageToClient(fail.getMessage());
                            }
                        }
                        else {
                            int chosenCard;
                            do {
                                mHandler.sendMessageToClient("Choose if you want to insert this resource either in the first card or the second card");
                                String select =  mHandler.readClientMessage();
                                chosenCard = CheckCommand.checkNumber(select,  mHandler);
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
                            mHandler.sendMessageToClient("select if you want to transform or discard the white marble");
                        } catch (EndingGameException ex){
                            throw new EndingGameException();
                        }
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
                                        String selectedResource;
                                        try{
                                            mHandler.sendMessageToClient("You can only convert one resource at a time, select the one you prefer");
                                            selectedResource =  mHandler.readClientMessage();
                                        } catch (EndingGameException ex){
                                            throw new EndingGameException();
                                        }
                                        Resource chosenRes = new Resource(CheckCommand.commandChecker(new String[]{((LeaderOfConversions)player.getActiveLeaderCards()[0]).getConvertedResource().toString(), ((LeaderOfConversions)player.getActiveLeaderCards()[0]).getConvertedResource().toString()}, selectedResource, mHandler));
                                        insertResource(chosenRes);
                                    }
                                    break;
                                case "discard":
                                    try {
                                        mHandler.sendMessageToClient("White marble discarded");
                                    }catch (EndingGameException ex){
                                        throw new EndingGameException();
                                    }
                                    }
                        } catch (EndingGameException ex) {
                           throw new EndingGameException();
                        }

                    }
                }
                else {
                    try {
                        mHandler.sendMessageToClient("faith increased");
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
        Resource[] costArray = player.getConnectedGame().getCardsMarket().getCard(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1])).getCostArray();
        CheckCommand.checkDiscount(player, costArray, mHandler);
        mHandler.sendMessageToClient("you can buy the selected card, please choose the resources to use");
        do {
            String chosenResource;
            chosenResource =  mHandler.readClientMessage();
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
                                mHandler.sendMessageToClient("This resource isn't available in your Strongbox");
                            }
                            break; //break for each
                        }
                        mHandler.sendMessageToClient("This resource isn't required");
                    }
                    break; //break case "strongbox"
                default:
                    mHandler.sendMessageToClient("select a valid resource location (Warehouse Depot, Extra Depot or Strongbox");
                    break;
            }
        }
        while (Arrays.equals(costArray, new Resource[costArray.length]));
        int position;
        do {
            mHandler.sendMessageToClient("Select a position to insert the card in");
            position = CheckCommand.checkNumber(mHandler.readClientMessage(), mHandler);
        } while (position<1 || position > 3);
        player.buyProductionCard(Integer.parseInt(parameters[0]),Integer.parseInt(parameters[1]), position);
        mHandler.sendMessageToClient("Card inserted in the selected slot");
    }

    private void activateProduction() throws EndingGameException {
        mHandler.sendMessageToClient("You can activate this production, select where you want to take the resources from");
        Resource[] requiredRes = player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getRequiredRes();
        do {
            String chosenResource =  mHandler.readClientMessage();
            switch (chosenResource.split(" ")[1].toLowerCase()) {
                case "warehousedepot":
                    useWarehouseResource(requiredRes, chosenResource);
                    break;

                case "extradepot":
                    Resource resExtra = checkResource(chosenResource.split(" ")[0].toLowerCase()); //resExtra is the resource chosen by the client
                    useExtraDepotResource(requiredRes, resExtra);
                    break;

                default:
                    mHandler.sendMessageToClient("Select a valid resource location (Warehouse Depot or Extra Depot)");
                    break;
            }
        } while(Arrays.equals(requiredRes, new Resource[requiredRes.length]));
        for (Resource r : player.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()[Integer.parseInt(parameters[0])].getGivenRes())
            player.getPersonalBoard().getStrongbox().insertNewResource(r);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
        mHandler.sendMessageToClient("Production activated and finished");
    }

    private void buyFromMarket() throws EndingGameException {
        MarketMarble[] marbles;
        int chosenLine = Integer.parseInt(parameters[1])-1;
        marbles = player.buyResourceFromMarket(chosenLine, parameters[0]);
        readMarbles(marbles);
        mHandler.sendMessageToClient("Resources bought");
    }

    private void standardProduction() throws EndingGameException {
        mHandler.sendMessageToClient("You can activate the standard production, select where you want to take the resources from");
        int givenRes = 0;
        do {

            String chosenResource =  mHandler.readClientMessage();
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
                    mHandler.sendMessageToClient("Select a valid resource location (Warehouse Depot or Extra Depot)");
                    break;
            }
        } while (givenRes<2);
        mHandler.sendMessageToClient("Now choose a resource to get from the production");
        do {
            String choice =  mHandler.readClientMessage();
            try {
                Resource res = new Resource(choice.toLowerCase());
                insertResource(res);
                break;
            } catch (IllegalArgumentException e) {
                mHandler.sendMessageToClient("select a valid resource");
            }
        } while (true);
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
        mHandler.sendMessageToClient("Standard production finished");
    }

    private void leaderProduction() throws EndingGameException{
        LeaderOfProductions chosenCard = (LeaderOfProductions)player.getActiveLeaderCards()[Integer.parseInt(parameters[0])];
        mHandler.sendMessageToClient("You can activate this production");
        Resource[] requiredRes = new Resource[1];
        requiredRes[0] = chosenCard.getRequiredResource();
        do {
            mHandler.sendMessageToClient("Select where you want to take the required resource from");
            String selectedDepot = mHandler.readClientMessage();
            selectedDepot = CheckCommand.commandChecker(new String[]{"warehouse depot", "extra depot"}, selectedDepot, mHandler);
            if (selectedDepot.equals("warehousedepot")) {
                useWarehouseResource(requiredRes, chosenCard.getRequiredResource().toString());
            }
            else
                useExtraDepotResource(requiredRes, chosenCard.getRequiredResource());
        } while (requiredRes[0]!=null);
        mHandler.sendMessageToClient("Faith increased, select now the resource you want");
        player.increaseFaith(1);
        String chosenResource = CheckCommand.commandChecker(new String[] {"Coin", "Stone", "Servant", "Shield"}, mHandler.readClientMessage(), mHandler);
        Resource resource = new Resource(chosenResource);
        player.getPersonalBoard().getStrongbox().insertNewResource(resource);
        mHandler.sendMessageToClient("Production activated!");
        EventManager.notifyListener(EventType.PERSONALBOARD, player.getPersonalBoard());
    }

}
