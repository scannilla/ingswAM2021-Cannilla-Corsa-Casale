package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.networkserver.Response;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.leader.LeaderOfProductions;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;

import java.util.Arrays;


public class Command{

    /**
     * this attribute represent the command sent from the client
     */
    private String command;

    /**
     * this attribute represents the parameters chosen by the client
     */
    private String[] parameters;

    /**
     * this attribute represents the player
     */
    private Player commandPlayer;


    /**
     * this method executes every command sent from the client
     * @return String
     */
    public Response executeCommand() {
        switch (command) {
            case "buyproductioncard":
                return buyProductionCard();

            case "activateleadercard":
                return activateLeaderCard();

            case "buyresource":
                return buyResources();

            case "cardproduction":
                return cardProduction();

            case "moveresources":
                Response result = moveResources();
                EventManager.notifyListener(EventType.PERSONALBOARD, commandPlayer.getPersonalBoard());
                return result;

            case "discardleadercard":
                return discardLeaderCard();

            case "standardproduction":
                return standardProduction();

            case "endturn":
                return new Response("end", 259);

            case "leaderproduction":
                return leaderProduction();

            case "view":
                return view();

            default:
                return new Response("No such command", 401);
        }
    }


    /**
     * setter for the actual player
     * @param player Player
     */
    public void setCommandPlayer(Player player) {
        this.commandPlayer = player;
    }

    /**
     * getter for the parameters sent from the user
     * @return parameters String[]
     */
    public String[] getParameters() {
        return parameters;
    }

    private int parseInt(String parameter) {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * This method allow a player (if he is allowed to) to buy a production card from Cards Market
     * @return commandString String
     */
    private Response buyProductionCard() {
        int row, column;
        if(commandPlayer.isActionDone())
            return new Response("Action already done", 405);
        try {
            row = parseInt(parameters[0]);
            column = parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameters", 408);
        }
        if(row<0 || row>3 || column<0 || column>2)
            return new Response("Index out of bounds", 407);
        if(commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[row][column]==null)
            return new Response("there are no cards left on the selected pile", 410);
        int level = commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[row][column].getLevel();
        if (level==1 || level ==2){
            int count = 0;
            for(int i=0; i<3; i++) {
                if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, level) != null)
                    count++;
            }
            if (count == 3)
                return new Response("No more space", 411);
        }
        ProductionCard productionCardSold = commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[row][column];
        Resource[] priceResources = productionCardSold.getCostArray();
        int[] price = ResourceCounter.resCount(priceResources);
        for (int i=0; i<4; i++) {
            if (price[i] > commandPlayer.getPersonalBoard().getWarehouseDepot().isEnoughWarehouse(priceResources[i], price[i]) +
                    commandPlayer.getPersonalBoard().getStrongbox().isEnough(priceResources[i], price[i])) {
                return new Response("Not able to buy this Production Card", 412);
            }
        }

        if(level > 1) {
            if (level == 2) {
                for (int i = 0; i < 3; i++) {
                    if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, 1) != null)
                        return new Response("$buy", 250);
                }
                return new Response("No level 1 card active", 413);
            } else {
                for (int i = 0; i < 3; i++) {
                    if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, 2) != null)
                        return new Response("$buy", 250);
                }
                return new Response("No level 2 card active", 414);
            }
        }
        else
            return new Response("$buy", 250);
    }

    /**
     * This method allows a player (if he is allowed to) to activate a Leader Card from his two Leader Cards
     * @return commandString String
     */
    private Response activateLeaderCard() {

        int chosenCard;
        try {
            chosenCard = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameters", 408);
        }
        if(chosenCard>=1 && chosenCard<=2) {
            try {
                commandPlayer.activateLeaderCard(commandPlayer.getLeaderCards()[chosenCard - 1]);
                EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getActiveLeaderCards());
                return new Response("Card activated", 210);
            } catch (IllegalArgumentException e) {
                return new Response(e.getMessage(), 415);
            }
        }
        else
            return new Response("index out of bounds", 407);
    }

    /**
     * This method allow a player (if he is allowed to) to buy resources from the Market Structure
     * @return commandString String
     */
    private Response buyResources() {
        if(commandPlayer.isActionDone())
            return new Response("Action already done", 405);
        int chosenLine;
        try{
            chosenLine = parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameter(s)", 408);
        }
        if(parameters[0].equals("column")) {
            if (chosenLine<1 || chosenLine>4)
                return new Response("Index out of bounds", 407);
            else new Response("$market", 251);
        }
        else if(parameters[0].equals("line")) {
            if (chosenLine < 1 || chosenLine > 3)
                return new Response("Index out of bounds", 407);
            else return new Response("$market", 251);

        }
        return new Response("Select either a column or a line", 416);
    }

    /**
     * This method allow a player (if he is allowed to) to activate a Production Card from his Production Card Slot
     * @return commandString String
     */
    private Response cardProduction() {
        if(commandPlayer.isActionDone() && !commandPlayer.isProductionActivated())
            return new Response("Action already done", 405);
        if(commandPlayer.getProductionsActivated()==3)
            return new Response("You have already activated all productions", 417);
        int chosenPosition;
        try {
            chosenPosition = Integer.parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameter(s)", 408);
        }
        if (chosenPosition < 1 || chosenPosition > 3){
            return new Response("Choose a valid position", 418);
        }
        if(commandPlayer.getPersonalBoard().getProdCardSlot().getProductionActivated()[chosenPosition-1])
            return new Response("You have already activated this production", 418);
        ProductionCard card = commandPlayer.getPersonalBoard().getProdCardSlot().getTopCards()[chosenPosition-1];
        Resource[] costArray = card.getCostArray();
        int[] costAmount = ResourceCounter.resCount(costArray);
        int[] resExtraAmount = {0, 0, 0, 0};
        int[] resWarehouseAmount = commandPlayer.getPersonalBoard().getWarehouseDepot().getDepotResourceAmount();
        int[] resAllAmount = {0, 0, 0, 0};
        int numberOfDepotCards;
        for (int j=0; j<costArray.length; j++){
            numberOfDepotCards = CheckCommand.leaderCardChecker("depot", commandPlayer, costArray[j]);
            if (numberOfDepotCards==1 || numberOfDepotCards == 2){
                resExtraAmount[j] = ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[numberOfDepotCards-1]).getExtraDepot())[j];
            } else if (numberOfDepotCards == 3){
                resExtraAmount[j] = ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[0]).getExtraDepot())[j] +
                        ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[1]).getExtraDepot())[j];
            }
            resAllAmount[j] = resExtraAmount[j] + resWarehouseAmount[j];
        }

        for(int i = 0; i< costAmount.length; i++){
            if (resAllAmount[i]<costAmount[i]){
                return new Response("You have not enough resource to activate this production", 419);
            }
        }
        commandPlayer.setProductionActivated();
        commandPlayer.setActionDone(true);
        commandPlayer.getPersonalBoard().getProdCardSlot().setProductionActivated(chosenPosition);
        commandPlayer.setProductionsActivated(commandPlayer.getProductionsActivated()+1);
        return new Response("$production " + chosenPosition, 252);
    }

    /**
     * This method allow a player (if he is allowed to) to switch resources from his Warehouse Depot from line1 to line2 and vice versa
     * @return commandString String
     */
    private Response moveResources() {

        int line1, line2;
        try{
            line1 = parseInt(parameters[0]);
            line2 = parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameter(s)", 408);
        }
        if((line1<1 || line1>3) || (line2<1 || line2>3) || line1==line2)
            return new Response("Choose valid warehouse lines to switch ", 420);
        else {
            try {
                commandPlayer.getPersonalBoard().getWarehouseDepot().moveResources(line1 - 1, line2 - 1);
                return new Response("Resources switched successfully", 211);
            } catch (IllegalArgumentException e) {
                return new Response("Can't switch these lines",421);
            }
        }
    }

    /**
     * This method allow a player (if he is allowed to) to discard one of his Leader Card
     * @return commandString String
     */
    private Response discardLeaderCard() {
        int chosenDiscardedCard;
        try {
            chosenDiscardedCard = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameter(s)", 408);
        }
        if (chosenDiscardedCard != 1 && chosenDiscardedCard != 2)
            return new Response("Select a valid card", 422);
        try {
            commandPlayer.discardLeaderCard(commandPlayer.getLeaderCards()[chosenDiscardedCard - 1]);
            return new Response("Card number " + parameters[0] + " discarded", 212);
        } catch (IllegalArgumentException e) {
            return new Response("Chosen card can't be discarded", 423);
        }
    }

    /**
     * This method allow a player (if he is allowed to) to activate standard production from his Personal Board
     * @return commandString String
     */
    private Response standardProduction() {
        int counter = 0;
        int [] resCounter;
        int cardPosition = CheckCommand.leaderCardChecker("depot", commandPlayer);
        for(int i : commandPlayer.getPersonalBoard().getWarehouseDepot().getDepotResourceAmount())
            counter += i;
        if (counter >= 2)
            return new Response ("$standard", 253);
        else if (cardPosition > 0) {
            if(cardPosition == 3) {
                resCounter = ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[0]).getExtraDepot());
                for (int i=0; i<4; i++)
                    resCounter[i] += ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[1]).getExtraDepot())[i];
                if (Arrays.stream(resCounter).reduce(0, Integer::sum) + counter>=2)
                    return new Response ("$standard", 253);
            }
            else if (cardPosition == 1 || cardPosition == 2) {
                resCounter = ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[cardPosition-1]).getExtraDepot());
                if (Arrays.stream(resCounter).reduce(0, Integer::sum) + counter>=2)
                    return new Response ("$standard", 253);
            }
        }
        return new Response ("Not enough resources to activate standard production", 424);
    }

    private Response leaderProduction() {
        if(commandPlayer.isActionDone() && !commandPlayer.isProductionActivated())
            return new Response("Action already done", 405);
        boolean found = false;
        int chosenCard;
        try {
            chosenCard = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return new Response("Not a number", 406);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameter(s)", 408);
        }
        if(chosenCard!=1 && chosenCard!=2)
            return new Response("Index out of bounds", 407);
        if(commandPlayer.getActiveLeaderCards()[chosenCard-1]==null || commandPlayer.getActiveLeaderCards()[chosenCard-1].getAbility()!=3)
            return new Response("Not a valid leader of productions card", 425);
        if(commandPlayer.getLeaderProductionActivated()[chosenCard-1])
            return new Response("You have already activated this production during this turn", 426);
        Resource requested = ((LeaderOfProductions)commandPlayer.getActiveLeaderCards()[chosenCard-1]).getRequiredResource();
        for (int i = 0; i < 3; i++) {
            if(requested.equals(commandPlayer.getPersonalBoard().getWarehouseDepot().checkResource(i)))
                found = true;
        }
        if(!found) {
            int depot = CheckCommand.leaderCardChecker("depot", commandPlayer);
            if(depot!=0)
                if(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[depot-1]).getResource().equals(requested) &&
                        ((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[depot-1]).getExtraDepot()[0]!=null)
                    found=true;
        }

        if(found) {
            commandPlayer.setActionDone(true);
            commandPlayer.setProductionActivated();
            commandPlayer.setLeaderProductionActivated(chosenCard-1);
            return new Response("$leaderprod", 254);

        }
        else
            return new Response("You don't have the required resource to activate this production", 427);
    }

    private Response view() {
        String[] viewMethods = {"card market", "market", "personal board", "leader cards"};
        try {
            if (!CheckCommand.commandChecker(viewMethods, parameters[0]))
                return new Response("not a valid parameter", 409);
        } catch (IndexOutOfBoundsException e) {
            return new Response("Missing parameter(s)", 408);
        }
        switch (parameters[0]) {
            case "card market":
            case "cardmarket":
                EventManager.notifyListener(EventType.CARDMARKET, commandPlayer.getConnectedGame().getCardsMarket(), commandPlayer.getNickname());
                break;
            case "market":
                EventManager.notifyListener(EventType.MARKET, commandPlayer.getConnectedGame().getMarket(), commandPlayer.getNickname());
                break;
            case "personal board":
            case "personalboard":
                int player;
                try {
                    player = parseInt(parameters[1]);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    EventManager.notifyListener(EventType.PERSONALBOARD, commandPlayer.getPersonalBoard(), commandPlayer.getNickname());
                    break;
                }
                if(player<1 || player>4)
                    return new Response ("Not a valid player", 409);
                EventManager.notifyListener(EventType.PERSONALBOARD, commandPlayer.getConnectedGame().getPlayers().get(player-1).getPersonalBoard(), commandPlayer.getNickname());
                break;
            case "leader cards":
            case "leadercards":
                int pl;
                try {
                    pl = parseInt(parameters[1]);
                } catch (NumberFormatException | IndexOutOfBoundsException e1) {
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getActiveLeaderCards(), commandPlayer.getNickname());
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getLeaderCards(), commandPlayer.getNickname());
                    break;
                }
                if(pl == commandPlayer.getConnectedGame().getPlayers().indexOf(commandPlayer)) {
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getActiveLeaderCards(), commandPlayer.getNickname());
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getLeaderCards(), commandPlayer.getNickname(), 654);
                } else if(pl>0 && pl <4) {
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getConnectedGame().getPlayers().get(pl).getActiveLeaderCards());
                } else
                    return new Response ("Not a valid player", 409);

        }
        return new Response("Printed", 215);
    }

}
