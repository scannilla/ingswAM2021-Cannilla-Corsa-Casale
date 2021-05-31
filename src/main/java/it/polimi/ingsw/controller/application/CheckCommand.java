package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;


import java.net.Socket;

public class CheckCommand {
    /**
     * This method checks if player has a Leader Card of Discount to use and asks player if he wants to activate this card
     * @param player Player
     * @param costArray Resource[]
     * @throws EndingGameException e
     */
    public static void checkDiscount(Player player, Socket clientSocket, Resource[] costArray) throws EndingGameException {
        Resource discountedRes ;
        String response = "";
        if (player.getActiveLeaderCards()[0] != null && player.getActiveLeaderCards()[0] instanceof LeaderOfDiscounts) {
            do {
                try {
                    MessageHandler.sendMessageToClient("Do you want to use Discount ability? [yes/no]", clientSocket);
                    response = MessageHandler.readClientMessage(clientSocket);
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
                        MessageHandler.sendMessageToClient("Do you want to use Discount ability? [yes/no]", clientSocket);
                        response = MessageHandler.readClientMessage(clientSocket);
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

    /**
     * This method checks if player inserted a valid number
     * @param string String
     * @return number (-1 error)
     */
    public static int checkNumber(Socket clientSocket, String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            try {
                MessageHandler.sendMessageToClient("Insert a valid number",clientSocket);
                String newNumber = MessageHandler.readClientMessage(clientSocket);
                return checkNumber(clientSocket, newNumber);
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
     * @param clientSocket Socket
     * @return givenCommand (correctCommand if error)
     * @throws EndingGameException e
     */
    public static String commandChecker (String[] acceptedCommands, String givenCommand, MessageHandler mHandler) throws EndingGameException {
        String allCommands = "Please insert one of the following commands: ";
        for(String s : acceptedCommands) {
            if (givenCommand.equalsIgnoreCase(s.replace(" ", "")))
                return givenCommand;
            allCommands = allCommands.concat(" " + s + ",");
        }
        String correctCommand = null;
        try {
            MessageHandler.sendMessageToClient(allCommands, clientSocket);
            correctCommand = commandChecker(acceptedCommands, MessageHandler.readClientMessage(clientSocket), clientSocket);
        } catch (EndingGameException e) {
            throw new EndingGameException();
        }
        return correctCommand;
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
