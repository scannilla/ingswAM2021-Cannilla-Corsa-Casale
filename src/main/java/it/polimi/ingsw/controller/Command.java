package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.production.ProductionCardsMarket;
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


            case "activateleadercard":
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



            case "buyresource":
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



            case "activateproduction":
                int chosenPosition;
                try {
                    chosenPosition = parseInt(parameters[0]);
                } catch (NumberFormatException e) {
                    return "Not a number";
                }
                if (chosenPosition < 1 || chosenPosition > 3){
                    return "choose a valid position";
                }
                try {
                    commandPlayer.activateStandardProduction(chosenPosition - 1);
                    return "production activated";
                    } catch (IllegalArgumentException e) {
                        return "production can't be activated";
                    }



            case "activateleaderability":
                //only certain abilities can be activated
                break;


            case "moveresources":
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
                        return "Chosen lines have been switched";
                    } catch (IllegalArgumentException e) {
                        return "Can't switch these lines";
                    }
                }

            case "discardleadercard":
                int chosenDiscardedCard;
                try{
                    chosenDiscardedCard = parseInt(parameters[0]);
                } catch (NumberFormatException e){
                    return "Not a number";
                }
                if(chosenDiscardedCard!=1 && chosenDiscardedCard!=2)
                    return "Select a valid card";
                try{
                    commandPlayer.discardLeaderCard(commandPlayer.getLeaderCards()[chosenDiscardedCard-1]);
                    return ("Card number " + parameters[0] + " discarded");
                } catch (IllegalArgumentException e) {
                    return "Chosen card can't be discarded";
                }



            case "activatecardproduction":
                int chosenProdCard;
                try {
                    chosenProdCard = parseInt(parameters[0]);
                    if (chosenProdCard<1 || chosenProdCard>3)
                        return "Index out of bounds";
                    return "$production";
                } catch (NumberFormatException e) {
                    return "Not a number";
                }

            case "standardproduction":
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
                    else
                        return "Not enough resources in your depots";
                }

            default: return "no such command";
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
}
