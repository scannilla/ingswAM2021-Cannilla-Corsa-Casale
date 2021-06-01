package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;

public class ActiveLeaderCardsDraw {
    public static void drawActiveLeaderCards(Player player ){
        String resetEscape = Color.ANSI_RESET.escape();
        LeaderCard[] cardsArray = new LeaderCard[3];
        cardsArray=player.getActiveLeaderCards();
        System.out.print(resetEscape + "--------------------------------------------");
        System.out.print(resetEscape + "|Level: 3    |WP: 2       |Ability: CON");
    }

    public static void abilityPrinter(Player player, int index){
        LeaderCard[] arrayCards = player.getActiveLeaderCards();
        switch (arrayCards[index].getAbility()){
            case 0:
                Resource disc= ((LeaderOfDiscounts)arrayCards[index]).getDiscountedRes();
                System.out.print(Color.ANSI_BLUE.escape() + "DIS");
                break;
            case 1: System.out.print(Color.ANSI_BLUE.escape() + "DEP");
                break;
            case 2: System.out.print(Color.ANSI_BLUE.escape() + "CON");
                break;
            case 3: System.out.print(Color.ANSI_BLUE.escape() + "PRO");
                break;
        }

    }

    public static void drawRes(Player player, int index) {
        String resetEscape = Color.ANSI_RESET.escape();
        LeaderCard[] arrayCards = player.getActiveLeaderCards();
        switch (arrayCards[index].getAbility()) {
            case 0: {
                Resource disc = ((LeaderOfDiscounts) arrayCards[index]).getDiscountedRes();
                switchCases(resetEscape, disc);
            }
            case 1: {
                Resource dep = ((LeaderOfDepots) arrayCards[index]).getResource();
                switchCases(resetEscape, dep);
            }
            case 2: {
                Resource conv = ((LeaderOfConversions) arrayCards[index]).getConvertedResource();
                switchCases(resetEscape, conv);
            }
            case 3: {
                Resource prod = ((LeaderOfProductions) arrayCards[index]).getRequiredResource();
                switchCases(resetEscape, prod);
            }
        }
    }
}
