package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;

import java.net.Socket;

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
     * @throws IllegalArgumentException
     */
    public String executeCommand() {
        switch (command) {
            case "buyproductioncard":
                if(Integer.parseInt(parameters[0])<0 || Integer.parseInt(parameters[0])>3 || Integer.parseInt(parameters[1])<0 || Integer.parseInt(parameters[1])>2)
                    return "index out of bounds";
                if(commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[Integer.parseInt(parameters[0])][Integer.parseInt(parameters[1])]==null)
                    return "there are no cards left on the selected pile";
                int level = commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[Integer.parseInt(parameters[0])][Integer.parseInt(parameters[1])].getLevel();
                if (level==1 || level ==2){
                    int count = 0;
                    for(int i=0; i<3; i++) {
                        if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, level) != null)
                            count++;
                    }
                    if (count == 3)
                        return "No more space";
                    }
                ProductionCard productionCardSold = commandPlayer.getConnectedGame().getCardsMarket().getTopCards()[Integer.parseInt(parameters[0])][Integer.parseInt(parameters[1])];
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
                                return "$Found";
                        }
                        return "No level 1 card active";
                    } else {
                        for (int i = 0; i < 3; i++) {
                            if (commandPlayer.getPersonalBoard().getProdCardSlot().getCard(i, 2) != null)
                                return "$Found";
                        }
                        return "No level 2 card active";
                    }
                }
                else
                    return "$buy";


            case "activateleadercard":
                int chosenCard = Integer.parseInt(parameters[0]);
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
                int chosenLine = Integer.parseInt(parameters[1]);
                if(!parameters[0].equals("column") && !parameters[0].equals("line"))
                    return "select if you want to take resources either from a line or a column";

                if(parameters[0].equals("column")) {
                    try {
                        commandPlayer.buyResourceFromMarket(chosenLine + 3);
                        return "resources taken";
                    } catch (IllegalArgumentException e) {
                        return "chosen column out of bounds";
                    }
                }
                else {
                    try {
                        commandPlayer.buyResourceFromMarket(chosenLine);
                        return "resources taken";
                    } catch (IllegalArgumentException e) {
                        return "chosen line out of bounds";
                    }
                }


            case "activateproduction":
                int chosenPosition = Integer.parseInt(parameters[0]);
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
                int line1 = Integer.parseInt(parameters[0]);
                int line2 = Integer.parseInt(parameters[1]);
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
                if(Integer.parseInt(parameters[0])!=1 && Integer.parseInt(parameters[0])!=2)
                    return "Select a valid card";
                try{
                    commandPlayer.discardLeaderCard(commandPlayer.getLeaderCards()[Integer.parseInt(parameters[0])-1]);
                    return ("Card number " + parameters[0] + " discarded");
                } catch (IllegalArgumentException e) {
                    return "Chosen card can't be discarded";
                }


            case "activatestandardproduction":
                try{
                    commandPlayer.activateStandardProduction(Integer.parseInt(parameters[0]));
                    return "Standard Production Activated";
                } catch(IllegalArgumentException e) {
                    return "Can't start production with this resource";
                }

            case "choosecards":
                ;

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
}
