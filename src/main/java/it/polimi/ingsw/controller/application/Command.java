package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
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
    public String executeCommand() {
        switch (command) {
            case "buyproductioncard":
                return buyProductionCard();

            case "activateleadercard":
                return activateLeaderCard();

            case "buyresource":
                return buyResources();

            case "cardproduction":
                return cardProduction();

            case "activateleaderability":
                //only certain abilities can be activated
                break;

            case "moveresources":
                String result = moveResources();
                EventManager.notifyListener(EventType.PERSONALBOARD, commandPlayer.getPersonalBoard());
                return result;

            case "discardleadercard":
                return discardLeaderCard();

            case "standardproduction":
                return standardProduction();

            case "endturn":
                return "end";

            case "leaderproduction":
                return leaderProduction();

            case "view":
                return view();

            default:
                return "no such command";
        }
        return "generic error";
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
    private String buyProductionCard() {
        int row, column;
        if(commandPlayer.isActionDone())
            return "Action already done";
        try {
            row = parseInt(parameters[0]);
            column = parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if(row<0 || row>3 || column<0 || column>2)
            return "index out of bounds";
        if(commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[row][column]==null)
            return "there are no cards left on the selected pile";
        int level = commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[row][column].getLevel();
        if (level==1 || level ==2){
            int count = 0;
            for(int i=0; i<3; i++) {
                if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, level) != null)
                    count++;
            }
            if (count == 3)
                return "No more space";
        }
        ProductionCard productionCardSold = commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[row][column];
        Resource[] priceResources = productionCardSold.getCostArray();
        int[] price = ResourceCounter.resCount(priceResources);
        for (int i=0; i<4; i++) {
            if (price[i] > commandPlayer.getPersonalBoard().getWarehouseDepot().isEnoughWarehouse(priceResources[i], price[i]) +
                    commandPlayer.getPersonalBoard().getStrongbox().isEnough(priceResources[i], price[i])) {
                return "Not able to buy this Production Card";
            }
        }

        if(level > 1) {
            if (level == 2) {
                for (int i = 0; i < 3; i++) {
                    if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, 1) != null)
                        return "$buy";
                }
                return "No level 1 card active";
            } else {
                for (int i = 0; i < 3; i++) {
                    if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, 2) != null)
                        return "$buy";
                }
                return "No level 2 card active";
            }
        }
        else
            return "$buy";
    }

    /**
     * This method allows a player (if he is allowed to) to activate a Leader Card from his two Leader Cards
     * @return commandString String
     */
    private String activateLeaderCard() {

        int chosenCard;
        try {
            chosenCard = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if(chosenCard>=1 && chosenCard<=2) {
            try {
                commandPlayer.activateLeaderCard(commandPlayer.getLeaderCards()[chosenCard - 1]);
                return "Card activated";
            } catch (IllegalArgumentException e) {
                return "Unable to activate this card";
            }
        }
        else
            return "index out of bounds";
    }

    /**
     * This method allow a player (if he is allowed to) to buy resources from the Market Structure
     * @return commandString String
     */
    private String buyResources() {
        if(commandPlayer.isActionDone())
            return "Action already done";
        int chosenLine;
        try{
            chosenLine = parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return "Not a number";
        } catch (NullPointerException e1) {
            return "No parameters";
        }
        if(parameters[0].equals("column")) {
            if (chosenLine<1 || chosenLine>4)
                return "Index out of bounds";
            else return "$market";
        }
        else if(parameters[0].equals("line")) {
            if (chosenLine < 1 || chosenLine > 3)
                return "Index out of bounds";
            else return "$market";
        }
        else return "Select either a column or a line";
    }

    /**
     * This method allow a player (if he is allowed to) to activate a Production Card from his Production Card Slot
     * @return commandString String
     */
    private String cardProduction() {
        if(commandPlayer.getProductionsActivated()==3)
            return "You have already activated all productions";
        int chosenPosition;
        try {
            chosenPosition = Integer.parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if (chosenPosition < 1 || chosenPosition > 3){
            return "choose a valid position";
        }
        if(commandPlayer.getPersonalBoard().getProdCardSlot().getProductionActivated()[chosenPosition-1])
            return "You have already activated this production";
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
                return "You have not enough resource to activate this production";
            }
        }
        commandPlayer.setActionDone(true);
        commandPlayer.getPersonalBoard().getProdCardSlot().setProductionActivated(chosenPosition);
        commandPlayer.setProductionsActivated(commandPlayer.getProductionsActivated()+1);
        return "$production " + chosenPosition;
    }

    /**
     * This method allow a player (if he is allowed to) to switch resources from his Warehouse Depot from line1 to line2 and vice versa
     * @return commandString String
     */
    private String moveResources() {

        int line1, line2;
        try{
            line1 = parseInt(parameters[0]);
            line2 = parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if((line1<1 || line1>3) || (line2<1 || line2>3) || line1==line2)
            return "Choose valid warehouse lines to switch ";
        else {
            try {
                commandPlayer.getPersonalBoard().getWarehouseDepot().moveResources(line1 - 1, line2 - 1);
                return "Resources switched successfully";
            } catch (IllegalArgumentException e) {
                return "Can't switch these lines";
            }
        }
    }

    /**
     * This method allow a player (if he is allowed to) to discard one of his Leader Card
     * @return commandString String
     */
    private String discardLeaderCard() {
        int chosenDiscardedCard;
        try {
            chosenDiscardedCard = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if (chosenDiscardedCard != 1 && chosenDiscardedCard != 2)
            return "Select a valid card";
        try {
            commandPlayer.discardLeaderCard(commandPlayer.getLeaderCards()[chosenDiscardedCard - 1]);
            return ("Card number " + parameters[0] + " discarded");
        } catch (IllegalArgumentException e) {
            return "Chosen card can't be discarded";
        }
    }

    /**
     * This method allow a player (if he is allowed to) to activate standard production from his Personal Board
     * @return commandString String
     */
    private String standardProduction() {
        int counter = 0;
        int [] resCounter;
        int cardPosition = CheckCommand.leaderCardChecker("depot", commandPlayer);
        for(int i : commandPlayer.getPersonalBoard().getWarehouseDepot().getDepotResourceAmount())
            counter += i;
        if (counter >= 2)
            return "$standard";
        else if (cardPosition > 0) {
            if(cardPosition == 3) {
                resCounter = ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[0]).getExtraDepot());
                for (int i=0; i<4; i++)
                    resCounter[i] += ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[1]).getExtraDepot())[i];
                if (Arrays.stream(resCounter).reduce(0, Integer::sum) + counter>=2)
                    return "$standard";
            }
            else if (cardPosition == 1 || cardPosition == 2) {
                resCounter = ResourceCounter.resCount(((LeaderOfDepots)commandPlayer.getActiveLeaderCards()[cardPosition-1]).getExtraDepot());
                if (Arrays.stream(resCounter).reduce(0, Integer::sum) + counter>=2)
                    return "$standard";
            }
        }
        return "Not enough resources to activate standard production";
    }

    private String leaderProduction() {
        boolean found = false;
        int chosenCard;
        try {
            chosenCard = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if(chosenCard!=1 && chosenCard!=2)
            return "Index out of bounds";
        if(commandPlayer.getActiveLeaderCards()[chosenCard-1]==null || commandPlayer.getActiveLeaderCards()[chosenCard-1].getAbility()!=3)
            return "Not a valid leader of productions card";
        if(commandPlayer.getLeaderProductionActivated()[chosenCard-1])
            return "You have already activated this production during this turn";
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
            commandPlayer.setLeaderProductionActivated(chosenCard-1);
            return "$leaderprod";

        }
        else
            return "You don't have the required resource to activate this production";
    }

    private String view() {
        String[] viewMethods = {"card market", "market", "personal board", "leader cards"};
        if(!CheckCommand.commandChecker(viewMethods, parameters[0]))
            return "not a valid parameter";
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
                    return "Not a valid player";
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
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getLeaderCards(), commandPlayer.getNickname());
                } else if(pl>0 && pl <4) {
                    EventManager.notifyListener(EventType.LEADERCARD, commandPlayer.getConnectedGame().getPlayers().get(pl).getActiveLeaderCards());
                } else
                    return "Not a valid player";

        }
        return "printed";
    }

}
