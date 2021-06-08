package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;

public class ActiveLeaderCardsDraw {
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
        }
    }

    public static void abilityPrinter(Player player, int index){
        LeaderCard[] arrayCards = player.getActiveLeaderCards();
        switch (arrayCards[index].getAbility()){
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

    public static void drawRes(Player player, int index) {
        String resetEscape = Color.ANSI_RESET.escape();
        LeaderCard[] arrayCards = player.getActiveLeaderCards();
        switch (arrayCards[index].getAbility()) {
            case 0: {
                Resource disc = ((LeaderOfDiscounts) cardsArray[index]).getDiscountedRes();
                switchCases(resetEscape, disc);
            }
            case 1: {
                Resource dep = ((LeaderOfDepots) cardsArray[index]).getResource();
                switchCases(resetEscape, dep);
            }
            case 2: {
                Resource conv = ((LeaderOfConversions) cardsArray[index]).getConvertedResource();
                switchCases(resetEscape, conv);
            }
            case 3: {
                Resource prod = ((LeaderOfProductions) cardsArray[index]).getRequiredResource();
                switchCases(resetEscape, prod);
            }
        }
    }

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


    public static void drawReqRes(Player player, int index){
        String resetEscape = Color.ANSI_RESET.escape();
        Resource[] reqRes = cardsArray[index].getRequiredRes();
        int[] reqRess = new int[10];
        int coins = 0;
        int stones = 0;
        int servants = 0;
        int shields = 0;
        int j=0;
        for (Resource i : reqRes){
            reqRess[j]=i.getResType();
            j++;
        }
        for (int l=0; l<10; l++){
            switch (reqRess[l]){
                case 0: coins++;
                break;
                case 1: stones++;
                break;
                case 2: servants++;
                break;
                case 3: shields++;
                break;
            }
        }
        if (coins!=0){
            System.out.print(resetEscape + coins);
            System.out.print(Color.ANSI_YELLOW.escape() + "C" + resetEscape);}
        if (stones!=0){
            System.out.print(resetEscape + stones);
            System.out.print(Color.ANSI_GREY.escape() + "R" + resetEscape);}
        if (servants!=0){
            System.out.print(resetEscape + servants);
            System.out.print(Color.ANSI_PURPLE.escape() + "S" + resetEscape);}
        if (shields!=0){
            System.out.print(resetEscape + shields);
            System.out.print(Color.ANSI_BLUE.escape() + "H" + resetEscape);}
    }

    public static void drawReqCards(Player player, int index){
        String resetEscape = Color.ANSI_RESET.escape();
        int[] reqType = cardsArray[index].getRequiredType();
        int[] reqLevel = cardsArray[index].getRequiredLevel();
        for (int i: reqType){
            System.out.print(Color.ANSI_CYAN.escape() + "t" + reqType[i] + "l" + reqLevel[i] + ", " + resetEscape);
        }
    }
}
