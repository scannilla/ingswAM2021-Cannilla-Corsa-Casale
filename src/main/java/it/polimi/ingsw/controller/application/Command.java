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
}
