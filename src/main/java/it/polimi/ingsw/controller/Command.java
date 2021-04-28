package it.polimi.ingsw.controller;

import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.Player;

import java.util.Locale;

public class Command{

    String command;

    public String executeCommand() {
        switch (command) {
            case "buyproductioncard":
                //ask client where to take resources from
                commandPlayer.buyProductionCard(Integer.parseInt(firstParameter), Integer.parseInt(secondParameter));
                break;
            case "activateleadercard":
                int chosenCard = Integer.parseInt(firstParameter);
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
                int chosenLine = Integer.parseInt(secondParameter);
                if(!firstParameter.equals("column") && !firstParameter.equals("line"))
                    return "select if you want to take resources either from a line or a column";
                if(firstParameter.equals("column")) {
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
                //ask client where to take resources from
            case "activateleaderability":
                //only certain abilities can be activated
            case "moveresources":
                //move resources inside warehouse depot
            case "discardleadercard":
                //discard a selected leader card
            default: return "no such command";
        }
        return "generic error";
    }

    public void setCommandPlayer(Player player) {
        this.commandPlayer = player;
    }


}
