package it.polimi.ingsw.cli;

import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;

/**
 * this class draws the active leader cards
 */
public class ActiveLeaderCardsDraw {
    /**
     * draws the active leader cards
     * @param cardsArray
     */
    public static void drawActiveLeaderCards(LeaderCard[] cardsArray){
        String resetEscape = Color.ANSI_RESET.escape();
        if (cardsArray.length==2) {
            if (cardsArray[0] == null) {
                System.out.print(resetEscape + "NO LEADER CARDS TO DISPLAY");
                System.out.print("\n");
                return;
            }
            if (cardsArray[1] == null) {
                System.out.print("\n");
                System.out.print(resetEscape + "FIRST CARD");
                System.out.print("\n");
                System.out.print(resetEscape + "Required Resources: ");
                drawReqRes(cardsArray, 0);
                System.out.print("\n");
                System.out.print(resetEscape + "Required Cards: ");
                drawReqCards(cardsArray, 0);
                System.out.print("\n");
                System.out.print(resetEscape + "Ability: ");
                abilityPrinter(cardsArray, 0);
                System.out.print("\n");
                System.out.print(resetEscape + "Leader Resource: ");
                drawRes(cardsArray, 0);
                System.out.print("\n");
                int WP = cardsArray[0].getWp();
                System.out.print(resetEscape + "Win Points: " + WP);
                System.out.print("\n");
                drawExtraDepot(cardsArray[0]);
                return;
            }
            else {
                System.out.print("\n");
                System.out.print(resetEscape + "FIRST CARD");
                System.out.print("\n");
                System.out.print(resetEscape + "Required Resources: ");
                drawReqRes(cardsArray, 0);
                System.out.print("\n");
                System.out.print(resetEscape + "Required Cards: ");
                drawReqCards(cardsArray, 0);
                System.out.print("\n");
                System.out.print(resetEscape + "Ability: ");
                abilityPrinter(cardsArray, 0);
                System.out.print("\n");
                System.out.print(resetEscape + "Leader Resource: ");
                drawRes(cardsArray, 0);
                System.out.print("\n");
                int WP = cardsArray[0].getWp();
                System.out.print(resetEscape + "Win Points: " + WP);
                System.out.print("\n");
                drawExtraDepot(cardsArray[0]);
                System.out.print("\n");
                System.out.print(resetEscape + "SECOND CARD");
                System.out.print("\n");
                System.out.print(resetEscape + "Required Resources: ");
                drawReqRes(cardsArray, 1);
                System.out.print("\n");
                System.out.print(resetEscape + "Required Cards: ");
                drawReqCards(cardsArray, 1);
                System.out.print("\n");
                System.out.print(resetEscape + "Ability: ");
                abilityPrinter(cardsArray, 1);
                System.out.print("\n");
                System.out.print(resetEscape + "Leader Resource: ");
                drawRes(cardsArray, 1);
                System.out.print("\n");
                int WP2 = cardsArray[1].getWp();
                System.out.print(resetEscape + "Win Points: " + WP2);
                System.out.print("\n");
                drawExtraDepot(cardsArray[1]);
            }
        }

        if (cardsArray.length==4){
            System.out.print("\n");
            System.out.print(resetEscape + "FIRST CARD");
            System.out.print("\n");
            System.out.print(resetEscape + "Required Resources: ");
            drawReqRes(cardsArray, 0);
            System.out.print("\n");
            System.out.print(resetEscape + "Required Cards: ");
            drawReqCards(cardsArray, 0);
            System.out.print("\n");
            System.out.print(resetEscape + "Ability: ");
            abilityPrinter(cardsArray, 0);
            System.out.print("\n");
            System.out.print(resetEscape + "Leader Resource: ");
            drawRes(cardsArray, 0);
            System.out.print("\n");
            int WP = cardsArray[0].getWp();
            System.out.print(resetEscape + "Win Points: " + WP);
            System.out.print("\n");
            drawExtraDepot(cardsArray[0]);
            System.out.print("\n");
            System.out.print(resetEscape + "SECOND CARD");
            System.out.print("\n");
            System.out.print(resetEscape + "Required Resources: ");
            drawReqRes(cardsArray, 1);
            System.out.print("\n");
            System.out.print(resetEscape + "Required Cards: ");
            drawReqCards(cardsArray, 1);
            System.out.print("\n");
            System.out.print(resetEscape + "Ability: ");
            abilityPrinter(cardsArray, 1);
            System.out.print("\n");
            System.out.print(resetEscape + "Leader Resource: ");
            drawRes(cardsArray, 1);
            System.out.print("\n");
            int WP2 = cardsArray[1].getWp();
            System.out.print(resetEscape + "Win Points: " + WP2);
            System.out.print("\n");
            drawExtraDepot(cardsArray[1]);
            System.out.print("\n");
            System.out.print(resetEscape + "THIRD CARD");
            System.out.print("\n");
            System.out.print(resetEscape + "Required Resources: ");
            drawReqRes(cardsArray, 2);
            System.out.print("\n");
            System.out.print(resetEscape + "Required Cards: ");
            drawReqCards(cardsArray, 2);
            System.out.print("\n");
            System.out.print(resetEscape + "Ability: ");
            abilityPrinter(cardsArray, 2);
            System.out.print("\n");
            System.out.print(resetEscape + "Leader Resource: ");
            drawRes(cardsArray, 2);
            System.out.print("\n");
            int WP3 = cardsArray[2].getWp();
            System.out.print(resetEscape + "Win Points: " + WP3);
            System.out.print("\n");
            drawExtraDepot(cardsArray[2]);
            System.out.print("\n");
            System.out.print(resetEscape + "FOURTH CARD");
            System.out.print("\n");
            System.out.print(resetEscape + "Required Resources: ");
            drawReqRes(cardsArray, 3);
            System.out.print("\n");
            System.out.print(resetEscape + "Required Cards: ");
            drawReqCards(cardsArray, 3);
            System.out.print("\n");
            System.out.print(resetEscape + "Ability: ");
            abilityPrinter(cardsArray, 3);
            System.out.print("\n");
            System.out.print(resetEscape + "Leader Resource: ");
            drawRes(cardsArray, 3);
            System.out.print("\n");
            int WP4 = cardsArray[3].getWp();
            System.out.print(resetEscape + "Win Points: " + WP4);
            System.out.print("\n");
            drawExtraDepot(cardsArray[3]);
        }
    }

    /**
     * draws the ability
     * @param cardsArray
     * @param index
     */
    public static void abilityPrinter(LeaderCard[] cardsArray, int index){
        switch (cardsArray[index].getAbility()){
            case 0: System.out.print(Color.ANSI_BLUE.escape() + "Leader of discounts");
                break;
            case 1: System.out.print(Color.ANSI_BLUE.escape() + "Leader of depots");
                break;
            case 2: System.out.print(Color.ANSI_BLUE.escape() + "Leader of conversions");
                break;
            case 3: System.out.print(Color.ANSI_BLUE.escape() + "Leader of productions");
                break;
        }

    }

    /**
     * draws a coloured resource
     * @param cardsArray
     * @param index
     */
    public static void drawRes(LeaderCard[] cardsArray, int index) {
        String resetEscape = Color.ANSI_RESET.escape();
        switch (cardsArray[index].getAbility()) {
            case 0: {
                Resource disc = ((LeaderOfDiscounts) cardsArray[index]).getDiscountedRes();
                switchCases(resetEscape, disc);
                break;
            }
            case 1: {
                Resource dep = ((LeaderOfDepots) cardsArray[index]).getResource();
                switchCases(resetEscape, dep);
                break;
            }
            case 2: {
                Resource conv = ((LeaderOfConversions) cardsArray[index]).getConvertedResource();
                switchCases(resetEscape, conv);
                break;
            }
            case 3: {
                Resource prod = ((LeaderOfProductions) cardsArray[index]).getRequiredResource();
                switchCases(resetEscape, prod);
                break;
            }
        }
    }

    /**
     * useful method for drawing resources
     * @param resetEscape
     * @param disc
     */
    private static void switchCases(String resetEscape, Resource disc) {
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

    /**
     * draws required resources
     * @param cardsArray
     * @param index
     */
    public static void drawReqRes(LeaderCard[] cardsArray, int index){
        String resetEscape = Color.ANSI_RESET.escape();
        Resource[] reqRes = cardsArray[index].getRequiredRes();
        int[] reqRess = ResourceCounter.resCount(reqRes);
        if (reqRess[0]!=0){
            System.out.print(resetEscape + reqRess[0]);
            System.out.print(Color.ANSI_YELLOW.escape() + "C" + resetEscape);}
        if (reqRess[1]!=0){
            System.out.print(resetEscape + reqRess[1]);
            System.out.print(Color.ANSI_GREY.escape() + "R" + resetEscape);}
        if (reqRess[2]!=0){
            System.out.print(resetEscape + reqRess[2]);
            System.out.print(Color.ANSI_PURPLE.escape() + "S" + resetEscape);}
        if (reqRess[3]!=0){
            System.out.print(resetEscape + reqRess[3]);
            System.out.print(Color.ANSI_BLUE.escape() + "H" + resetEscape);}
    }

    /**
     * draws required cards
     * @param cardsArray
     * @param index
     */
    public static void drawReqCards(LeaderCard[] cardsArray, int index){
        String resetEscape = Color.ANSI_RESET.escape();
        int[] reqType = cardsArray[index].getRequiredType();
        int[] reqLevel = cardsArray[index].getRequiredLevel();
        if(reqType.length>0) {
            for (int i = 0; i < reqType.length; i++) {
                System.out.print(Color.ANSI_CYAN.escape() + "t" + reqType[i] + "l" + reqLevel[i] + ", " + resetEscape);
            }
        }
    }

    /**
     * draws extra depot
     * @param card
     */
    public static void drawExtraDepot(LeaderCard card){
        String reset = Color.ANSI_RESET.escape();
        if (card.getAbility()==1) {
            Resource res = ((LeaderOfDepots) card).getResource();
            int ress = res.getResType();
            int length = ((LeaderOfDepots) card).getExtraDepot().length;
            System.out.print("\n");
            System.out.print(reset + "Extra Depot: ");
            System.out.print(reset + "\n");
            if (length == 0){
                System.out.print(reset + "---------");
                System.out.print(reset + "\n");
                System.out.print(reset + "|   |   |");
                System.out.print(reset + "\n");
                System.out.print(reset + "---------");
                System.out.print(reset + "\n");
            }
            if (length == 1){
                System.out.print(reset + "---------");
                System.out.print(reset + "\n");
                if (ress == 0){
                    System.out.print(reset + "| " + Color.ANSI_YELLOW.escape() + "C" + reset + " |   |");}
                if (ress == 1){
                    System.out.print(reset + "| " + Color.ANSI_GREY.escape() + "R" + reset + " |   |");}
                if (ress == 2){
                    System.out.print(reset + "| " + Color.ANSI_PURPLE.escape() + "S" + reset + " |   |");}
                if (ress == 3){
                    System.out.print(reset + "| " + Color.ANSI_BLUE.escape() + "H" + reset + " |   |");}
                System.out.print(reset + "\n");
                System.out.print(reset + "---------");
                System.out.print(reset + "\n");
            }
            if (length == 2){
                System.out.print(reset + "---------");
                System.out.print(reset + "\n");
                if (ress==0){
                    System.out.print(reset + "| " + Color.ANSI_YELLOW.escape() + "C" + reset + " | " + Color.ANSI_YELLOW.escape() + "C" + reset + " |");}
                if (ress==1){
                    System.out.print(reset + "| " + Color.ANSI_GREY.escape() + "R" + reset + " | " + Color.ANSI_GREY.escape() + "R" + reset + " |");}
                if (ress==2){
                    System.out.print(reset + "| " + Color.ANSI_PURPLE.escape() + "S" + reset + " | " + Color.ANSI_PURPLE.escape() + "S" + reset + " |");}
                if (ress==3){
                    System.out.print(reset + "| " + Color.ANSI_BLUE.escape() + "H" + reset + " | " + Color.ANSI_BLUE.escape() + "H" + reset + " |");}
                System.out.print(reset + "\n");
                System.out.print(reset + "---------");
                System.out.print(reset + "\n");
            }
        }
    }
}
