package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;


public class CheckCommand {
    /**
     * This method checks if player has a Leader Card of Discount to use and asks player if he wants to activate this card
     * @param player Player
     * @param costArray Resource[]
     * @throws EndingGameException e
     */
    public static void checkDiscount(Player player, Resource[] costArray, MessageHandler mHandler) throws EndingGameException {
        Resource discountedRes ;
        String response;
        if (player.getActiveLeaderCards()[0] != null && player.getActiveLeaderCards()[0] instanceof LeaderOfDiscounts) {
            do {
                try {
                    mHandler.sendMessageToClient("Do you want to use Discount ability? [yes/no]", 150);
                    response =  mHandler.readClientMessage();
                } catch (EndingGameException e) {
                    throw new EndingGameException();
                }
                if (response.equalsIgnoreCase("yes")) {
                    discountedRes = ((LeaderOfDiscounts) player.getActiveLeaderCards()[0]).getDiscountedRes();
                    for (int i = 0; i < costArray.length; i++) {
                        if (discountedRes.equals(costArray[i])) {
                            costArray[i] = null;
                            break;
                        }
                    }
                }
            } while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no"));



            if (player.getActiveLeaderCards()[1] != null && player.getActiveLeaderCards()[1] instanceof LeaderOfDiscounts) {
                do{
                    try {
                        mHandler.sendMessageToClient("Do you want to use Discount ability? [yes/no]", 150);
                        response =  mHandler.readClientMessage();
                    } catch (EndingGameException e) {
                        throw new EndingGameException();
                    }
                    if(response.equalsIgnoreCase("yes")) {
                        discountedRes = ((LeaderOfDiscounts) player.getActiveLeaderCards()[1]).getDiscountedRes();
                        for (int i = 0; i < costArray.length; i++) {
                            if (discountedRes.equals(costArray[i])) {
                                costArray[i] = null;
                                break;
                            }
                        }
                    }
                } while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no"));
            }
        }
    }

    public static void checkDiscount(Player player, Resource[] costArray) {
        Resource discountedRes ;
        if (player.getActiveLeaderCards()[0] != null && player.getActiveLeaderCards()[0] instanceof LeaderOfDiscounts) {
            discountedRes = ((LeaderOfDiscounts) player.getActiveLeaderCards()[0]).getDiscountedRes();
            for (int i = 0; i < costArray.length; i++) {
                if (discountedRes.equals(costArray[i])) {
                    costArray[i] = null;
                    break;
                }
            }
        }
        if (player.getActiveLeaderCards()[1] != null && player.getActiveLeaderCards()[1] instanceof LeaderOfDiscounts) {
            discountedRes = ((LeaderOfDiscounts) player.getActiveLeaderCards()[1]).getDiscountedRes();
            for (int i = 0; i < costArray.length; i++) {
                if (discountedRes.equals(costArray[i])) {
                    costArray[i] = null;
                    break;
                }
            }
        }
    }

    /**
     * This method checks if player inserted a valid number
     * @param string String
     * @return number (-1 error)
     */
    public static int checkNumber(String string, MessageHandler mHandler) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            try {
                mHandler.sendMessageToClient("Insert a valid number", 406);
                String newNumber =  mHandler.readClientMessage();
                return checkNumber(newNumber, mHandler);
            } catch (EndingGameException ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * This methods shows all commands that player can insert and checks if this command is valid
     * @param acceptedCommands String[]
     * @param givenCommand String

     * @return givenCommand (correctCommand if error)
     * @throws EndingGameException e
     */
    public static String commandChecker (String[] acceptedCommands, String givenCommand, MessageHandler mHandler) throws EndingGameException {
        String allCommands = "Please insert one of the following commands: ";
        givenCommand = givenCommand.replace(" ","");
        for(String s : acceptedCommands) {
            if (givenCommand.equalsIgnoreCase(s.replace(" ", "")))
                return givenCommand.toLowerCase();
            allCommands = allCommands.concat(" " + s + ",");
        }
        String correctCommand;
        try {
            mHandler.sendMessageToClient(allCommands, 151);
            correctCommand = commandChecker(acceptedCommands, mHandler.readClientMessage(), mHandler);
        } catch (EndingGameException e) {
            throw new EndingGameException();
        }
        return correctCommand.toLowerCase();
    }

    /**
     * This method checks if this command is valid
     * @param acceptedCommands String[]
     * @param givenCommand String
     * @return true (false if error)
     */
    public static boolean commandChecker (String[] acceptedCommands, String givenCommand) {
        for(String s : acceptedCommands) {
            if (s.replace(" ","").equals(givenCommand))
                return true;
        }
        return false;
    }

    /**
     * This method return:
     * 0 if there aren't any active leader card of this player of this ability
     * 1 if there is one card in active leader card of this player of this ability
     * 3 if there are two card in active leader card of this player of this ability
     * @param ability int
     * @param player Player
     * @return counter
     */
    public static int leaderCardChecker (String ability, Player player, Resource resource) {
        int counter=0;
        switch (ability) {
            case "depot":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfDepots &&
                            ((LeaderOfDepots) player.getActiveLeaderCards()[i]).getResource().equals(resource)){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            case "conversion":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfConversions &&
                            ((LeaderOfConversions) player.getActiveLeaderCards()[i]).getConvertedResource().equals(resource)){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            case "discount":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfDiscounts &&
                            ((LeaderOfDiscounts) player.getActiveLeaderCards()[i]).getDiscountedRes().equals(resource)){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            case "production":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfProductions &&
                            ((LeaderOfProductions) player.getActiveLeaderCards()[i]).getRequiredResource().equals(resource)){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            default:
                return 0;
        }
        return counter;
    }

    /**
     * This method checks if player has this type of Leader Card
     * @param ability String
     * @param player Player
     * @return 0 if player has not this type of Leader Card
     *         1 if player has in first position this type of Leader Card
     *         2 if player has in second position this type of Leader Card
     *         3 if player has in both position this type of Leader Card
     */
    public static int leaderCardChecker (String ability, Player player) {
        int counter=0;
        switch (ability) {
            case "depot":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfDepots){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            case "conversion":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfConversions){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            case "discount":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfDiscounts){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            case "production":
                for(int i = 0; i<2; i++){
                    if(player.getActiveLeaderCards()[i] != null && player.getActiveLeaderCards()[i] instanceof LeaderOfProductions){
                        if(i == 0){
                            counter++;
                        } else {
                            counter += 2;
                        }
                    }
                }
                break;
            default:
                return 0;
        }
        return counter;
    }
}
