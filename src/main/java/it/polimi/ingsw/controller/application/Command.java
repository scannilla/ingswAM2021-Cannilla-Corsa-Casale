package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.LeaderOfDepots;
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
                return moveResources();

            case "discardleadercard":
                return discardLeaderCard();

            case "standardproduction":
                return standardProduction();
            case "endturn":
                return "end";

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
        int chosenLine;
        try{
            chosenLine = parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return "Not a number";
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
        int chosenPosition;
        try {
            chosenPosition = Integer.parseInt(parameters[0]);
        } catch (NumberFormatException e) {
            return "Not a number";
        }
        if (chosenPosition < 1 || chosenPosition > 3){
            return "choose a valid position";
        }
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



}
