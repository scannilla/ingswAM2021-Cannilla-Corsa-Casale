package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.LeaderCard;

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
                int resIndex = disc.getResType();
                switch (resIndex) {
                    case 0:
                        System.out.print(Color.ANSI_YELLOW.escape() + "C" + resetEscape);
                        break;
                    case 1:
                        System.out.print(Color.ANSI_GREY.escape() + "R" + resetEscape);
                        break;
                    case 2:
                        System.out.print(Color.ANSI_PURPLE.escape() + "S" + resetEscape);
                        break;
                    case 3:
                        System.out.print(Color.ANSI_BLUE.escape() + "H" + resetEscape);
                        break;
                }
            }
            case 1: {
                Resource dep = ((LeaderOfDepots) arrayCards[index]).getResource();
                int depIndex = dep.getResType();
                switch (depIndex) {
                    case 0:
                        System.out.print(Color.ANSI_YELLOW.escape() + "C" + resetEscape);
                        break;
                    case 1:
                        System.out.print(Color.ANSI_GREY.escape() + "R" + resetEscape);
                        break;
                    case 2:
                        System.out.print(Color.ANSI_PURPLE.escape() + "S" + resetEscape);
                        break;
                    case 3:
                        System.out.print(Color.ANSI_BLUE.escape() + "H" + resetEscape);
                        break;
                }
            }
            case 2: {
                Resource conv = ((LeaderOfConversions) arrayCards[index]).getConvertedResource();
                int convIndex = conv.getResType();
                switch (convIndex) {
                    case 0:
                        System.out.print(Color.ANSI_YELLOW.escape() + "C" + resetEscape);
                        break;
                    case 1:
                        System.out.print(Color.ANSI_GREY.escape() + "R" + resetEscape);
                        break;
                    case 2:
                        System.out.print(Color.ANSI_PURPLE.escape() + "S" + resetEscape);
                        break;
                    case 3:
                        System.out.print(Color.ANSI_BLUE.escape() + "H" + resetEscape);
                        break;
                }
            }
            case 3: {
                Resource prod = ((LeaderOfProductions) arrayCards[index]).getRequiredResource();
                int prodIndex = prod.getResType();
                switch (prodIndex) {
                    case 0:
                        System.out.print(Color.ANSI_YELLOW.escape() + "C" + resetEscape);
                        break;
                    case 1:
                        System.out.print(Color.ANSI_GREY.escape() + "R" + resetEscape);
                        break;
                    case 2:
                        System.out.print(Color.ANSI_PURPLE.escape() + "S" + resetEscape);
                        break;
                    case 3:
                        System.out.print(Color.ANSI_BLUE.escape() + "H" + resetEscape);
                        break;
                }
            }
        }
    }
}
