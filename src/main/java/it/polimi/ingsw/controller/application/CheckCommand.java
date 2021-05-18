package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Locale;

public class CheckCommand {

    public static void checkDiscount(Player player, BufferedReader in, PrintWriter out, Resource[] costArray) {
        Resource discountedRes = null;
        String response = "";
        if (player.getActiveLeaderCards()[0] != null && player.getActiveLeaderCards()[0] instanceof LeaderOfDiscounts) {
            do {
                try {
                    out.println("Do you want to use Discount ability? [yes/no]");
                    response = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
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
                        out.println("Do you want to use Discount ability? [yes/no]");
                        response = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
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


    public static int checkNumber(BufferedReader in, PrintWriter out, String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
                out.println("Insert a valid number");
            try {
                String newNumber = in.readLine();
                return checkNumber(in, out, newNumber);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return -1;
    }

    public static String commandChecker (String[] acceptedCommands, String givenCommand, BufferedReader in, PrintWriter out) throws IOException {
        String allCommands = "Please insert one of the following commands: ";
        for(String s : acceptedCommands) {
            if (givenCommand.equalsIgnoreCase(s.replace(" ", "")))
                return givenCommand;
            allCommands = allCommands.concat(" " + s + ",");
        }
        out.println(allCommands);
        String correctCommand = null;
        try {
            correctCommand = commandChecker(acceptedCommands, in.readLine(), in, out);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1); //TODO inform all players that the game is over and close the connections
        }
        return correctCommand;
    }

    public static boolean commandChecker (String[] acceptedCommands, String givenCommand) {
        for(String s : acceptedCommands) {
            if (s.equals(givenCommand))
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
