package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.production.ProdCardSlot;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;

public class ProdCardSlotDraw {
    public static void drawActiveProdCards(PersonalBoard personalBoard) {
        ProdCardSlot activeProdCard = personalBoard.getProdCardSlot();
        String resetEscape = Color.ANSI_RESET.escape();
        ProductionCard[] activeCards = activeProdCard.getTopCards();
        System.out.print(resetEscape + "\n");
        if (activeCards[0] != null) {

            int level1 = activeCards[0].getLevel();
            int type1 = activeCards[0].getType();
            int wp1 = activeCards[0].getWp();
            Resource[] reqRes1 = activeCards[0].getRequiredRes();
            int[] reqResInt1 = new int[15];
            for (int i=0; i<reqRes1.length; i++){
                reqResInt1[i]=reqRes1[i].getResType();
            }

            Resource[] givenRes1 = activeCards[0].getGivenRes();
            int[] givenResInt1 = new int[15];
            for (int i = 0; i<givenRes1.length; i++) {
                givenResInt1[i] = givenRes1[i].getResType();
            }
            int givenFaithPoints1 = activeCards[0].getGivenFaithPoints();

            System.out.print(resetEscape + "FIRST SLOT");
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Level: " + level1);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Type: " + type1);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Win Points: " + wp1);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Required Resources: ");
            for (int k = 0; k < reqRes1.length; k++) {
                drawRes(reqResInt1[k]);
            }
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Given Resources: ");
            for (int k = 0; k < givenRes1.length; k++) {
                drawRes(givenResInt1[k]);
            }
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Given Faith Points: " + givenFaithPoints1);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "\n");
        }
        if (activeCards[0] == null) {
            System.out.print(resetEscape + "FIRST SLOT");
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Empty");
            System.out.print(resetEscape + "\n");
        }
        if (activeCards[1] != null) {
            int level2 = activeCards[1].getLevel();
            int type2 = activeCards[1].getType();
            int wp2 = activeCards[1].getWp();
            Resource[] reqRes2 = activeCards[1].getRequiredRes();
            int[] reqResInt2 = new int[15];
            for (int i=0; i<reqRes2.length; i++){
                reqResInt2[i]=reqRes2[i].getResType();
            }

            Resource[] givenRes2 = activeCards[1].getGivenRes();
            int[] givenResInt2 = new int[15];
            for (int i = 0; i<givenRes2.length; i++) {
                givenResInt2[i] = givenRes2[i].getResType();
            }
            int givenFaithPoints2 = activeCards[1].getGivenFaithPoints();

            System.out.print(resetEscape + "SECOND SLOT");
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Level: " + level2);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Type: " + type2);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Win Points: " + wp2);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Required Resources: ");
            for (int k = 0; k < reqRes2.length; k++) {
                drawRes(reqResInt2[k]);
            }
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Given Resources: ");
            for (int k = 0; k < givenRes2.length; k++) {
                drawRes(givenResInt2[k]);
            }
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Given Faith Points: " + givenFaithPoints2);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "\n");
        }

        if (activeCards[1] == null) {
            System.out.print(resetEscape + "SECOND SLOT");
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Empty");
            System.out.print(resetEscape + "\n");
        }
        if (activeCards[2] != null) {

            int level3 = activeCards[2].getLevel();
            int type3 = activeCards[2].getType();
            int wp3 = activeCards[2].getWp();
            Resource[] reqRes3 = activeCards[2].getRequiredRes();
            int[] reqResInt3 = new int[15];
            for (int i=0; i<reqRes3.length; i++){
                reqResInt3[i]=reqRes3[i].getResType();
            }

            Resource[] givenRes3 = activeCards[2].getGivenRes();
            int[] givenResInt3 = new int[15];
            for (int i = 0; i<givenRes3.length; i++) {
                givenResInt3[i] = givenRes3[i].getResType();
            }
            int givenFaithPoints3 = activeCards[2].getGivenFaithPoints();

            System.out.print(resetEscape + "THIRD SLOT");
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Level: " + level3);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Type: " + type3);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Win Points: " + wp3);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Required Resources: ");
            for (int k = 0; k < reqRes3.length; k++) {
                drawRes(reqResInt3[k]);
            }
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Given Resources: ");
            for (int k = 0; k < givenRes3.length; k++) {
                drawRes(givenResInt3[k]);
            }
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Given Faith Points: " + givenFaithPoints3);
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "\n");
        }

        if (activeCards[2] == null) {
            System.out.print(resetEscape + "THIRD SLOT");
            System.out.print(resetEscape + "\n");
            System.out.print(resetEscape + "Empty");
            System.out.print(resetEscape + "\n");
        }
    }
    public static void drawRes(int resource){
        String resetEscape = Color.ANSI_RESET.escape();
        switch (resource){
            case 0: System.out.print(Color.ANSI_YELLOW.escape() + "C " + resetEscape);
                 break;
            case 1: System.out.print(Color.ANSI_GREY.escape() + "R " + resetEscape);
                break;
            case 2: System.out.print(Color.ANSI_PURPLE.escape() + "S " + resetEscape);
                break;
            case 3: System.out.print(Color.ANSI_BLUE.escape() + "H " + resetEscape);
                break;
        }
    }
}
