package it.polimi.ingsw.cli;

import it.polimi.ingsw.production.ProdCardSlot;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;

public class ProdCardSlotDraw {
    public static void drawActiveProdCards(ProdCardSlot activeProdCard){
        String resetEscape = Color.ANSI_RESET.escape();
        ProductionCard[] activeCards = activeProdCard.getTopCards();
        System.out.print(resetEscape + "\n");
        int level1 = activeCards[0].getLevel();
        int type1 = activeCards[0].getType();
        int wp1 = activeCards[0].getWp();
        Resource[] costArray1 = activeCards[0].getCostArray();
        int[] costArrayInt1 = new int[10];
        int index1 = 0;
        for (Resource j: costArray1){
            costArrayInt1[index1] = costArray1[index1].getResType();
            index1++;
        }

        Resource[] reqRes1 = activeCards[0].getRequiredRes();
        int[] reqResInt1 = new int[15];
        int index2 = 0;
        for (Resource j: reqRes1){
            reqResInt1[index2] = reqRes1[index2].getResType();
            index2++;
        }

        Resource[] givenRes1 = activeCards[0].getGivenRes();
        int[] givenResInt1 = new int[10];
        int index3 = 0;
        for (Resource j: givenRes1){
            reqResInt1[index3] = givenRes1[index3].getResType();
            index3++;
        }
        int givenFaithPoints1 = activeCards[0].getGivenFaithPoints();

        int level2 = activeCards[1].getLevel();
        int type2 = activeCards[1].getType();
        int wp2 = activeCards[1].getWp();
        Resource[] costArray2 = activeCards[1].getCostArray();
        int[] costArrayInt2 = new int[10];
        int index4 = 0;
        for (Resource j: costArray2){
            costArrayInt2[index4] = costArray2[index4].getResType();
            index4++;
        }

        Resource[] reqRes2 = activeCards[1].getRequiredRes();
        int[] reqResInt2 = new int[15];
        int index5 = 0;
        for (Resource j: reqRes2){
            reqResInt2[index5] = reqRes2[index5].getResType();
            index5++;
        }

        Resource[] givenRes2 = activeCards[1].getGivenRes();
        int[] givenResInt2 = new int[10];
        int index6 = 0;
        for (Resource j: givenRes2){
            reqResInt1[index6] = givenRes2[index6].getResType();
            index6++;
        }
        int givenFaithPoints2 = activeCards[1].getGivenFaithPoints();

        int level3 = activeCards[2].getLevel();
        int type3 = activeCards[2].getType();
        int wp3 = activeCards[2].getWp();
        Resource[] costArray3 = activeCards[2].getCostArray();
        int[] costArrayInt3 = new int[10];
        int index7 = 0;
        for (Resource j: costArray3){
            costArrayInt3[index7] = costArray3[index7].getResType();
            index7++;
        }

        Resource[] reqRes3 = activeCards[2].getRequiredRes();
        int[] reqResInt3 = new int[15];
        int index8 = 0;
        for (Resource j: reqRes3){
            reqResInt3[index8] = reqRes3[index8].getResType();
            index8++;
        }

        Resource[] givenRes3 = activeCards[2].getGivenRes();
        int[] givenResInt3 = new int[10];
        int index9 = 0;
        for (Resource j: givenRes3){
            reqResInt3[index9] = givenRes3[index9].getResType();
            index9++;
        }
        int givenFaithPoints3 = activeCards[2].getGivenFaithPoints();

        System.out.print(resetEscape + "FIRST SLOT");
        System.out.print(resetEscape + "Level: " + level1);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Type: " + type1);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Win Points: " + wp1);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Cost Array: ");
        for (int k=0; k<costArray1.length; k++){
            drawRes(costArrayInt1[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Required Resources: ");
        for (int k=0; k<reqRes1.length; k++){
            drawRes(reqResInt1[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Given Resources: ");
        for (int k=0; k<givenRes1.length; k++){
            drawRes(givenResInt1[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Given Faith Points: " + givenFaithPoints1);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "\n");

        System.out.print(resetEscape + "SECOND SLOT");
        System.out.print(resetEscape + "Level: " + level2);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Type: " + type2);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Win Points: " + wp2);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Cost Array: ");
        for (int k=0; k<costArray2.length; k++){
            drawRes(costArrayInt2[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Required Resources: ");
        for (int k=0; k<reqRes2.length; k++){
            drawRes(reqResInt2[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Given Resources: ");
        for (int k=0; k<givenRes2.length; k++){
            drawRes(givenResInt2[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Given Faith Points: " + givenFaithPoints2);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "\n");

        System.out.print(resetEscape + "THIRD SLOT");
        System.out.print(resetEscape + "Level: " + level3);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Type: " + type3);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Win Points: " + wp3);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Cost Array: ");
        for (int k=0; k<costArray3.length; k++){
            drawRes(costArrayInt3[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Required Resources: ");
        for (int k=0; k<reqRes3.length; k++){
            drawRes(reqResInt3[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Given Resources: ");
        for (int k=0; k<givenRes3.length; k++){
            drawRes(givenResInt3[k]);
        }
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "Given Faith Points: " + givenFaithPoints3);
        System.out.print(resetEscape + "\n");
        System.out.print(resetEscape + "\n");
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
