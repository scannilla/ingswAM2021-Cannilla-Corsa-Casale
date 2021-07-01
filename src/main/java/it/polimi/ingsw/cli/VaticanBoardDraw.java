package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.VaticanReport;

/**
 * this class draws the vatican board
 */
public class VaticanBoardDraw {
    /**
     * draws the vatican road
     * @param vaticanReport
     * @param personalBoard
     */
    public static void drawVaticanRoad(VaticanReport vaticanReport, PersonalBoard personalBoard) {
        String blueEscape = Color.ANSI_BLUE.escape();
        int[] activationPosition = vaticanReport.getActivationPosition();
        int[] vaticanLength = vaticanReport.getReportsLength();
        int[] winPoints = vaticanReport.getWinPoints();
        int pos = personalBoard.getPosition();
        drawColouredLine(activationPosition, vaticanLength);
        drawSecondLine(winPoints, activationPosition, vaticanLength);
        drawThirdLine(activationPosition, vaticanLength, pos);
        drawFourthLine(activationPosition, vaticanLength);
        drawColouredLine(activationPosition, vaticanLength);
        System.out.print("\n");
    }

    /**
     * draws the second line
     * @param winPoints
     * @param vatican
     * @param vaticanLength
     */
    public static void drawSecondLine(int[] winPoints, int[] vatican, int[] vaticanLength){
        String blueEscape = Color.ANSI_BLUE.escape();
        int[] slotStart = new int[3];
        int wp=0;
        for (int i=0; i < 3; i++){
            slotStart[i]=vatican[i]-vaticanLength[i]+1;
        }
        int counter = 0;
        for (int i = 0; i < slotStart[0]-1; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "     " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            if (winPoints[wp]<10) {
                System.out.print(Color.ANSI_BLUE.escape() + " |" + blueEscape);
            }
            wp++;
        }
        counter++;

        for (int i = slotStart[0]; i < vatican[0]; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "     " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_YELLOW.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            if (winPoints[wp]<10) {
                System.out.print(Color.ANSI_YELLOW.escape() + " |" + blueEscape);
            }
            else System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            wp++;
        }
        counter++;

        for (int i = vatican[0]+1; i < slotStart[1]-1; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "     " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            if (winPoints[wp]<10) {
                System.out.print(Color.ANSI_BLUE.escape() + " |" + blueEscape);
            }
            else System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            wp++;
        }
        counter++;

        for (int i = slotStart[1]; i < vatican[1]; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "     " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_YELLOW.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            if (winPoints[wp]<10) {
                System.out.print(Color.ANSI_YELLOW.escape() + " |" + blueEscape);
            }
            else System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            wp++;
        }
        counter++;

        for (int i = vatican[1]+1; i < slotStart[2]-1; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "     " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            if (winPoints[wp]<10) {
                System.out.print(Color.ANSI_BLUE.escape() + " |" + blueEscape);
            }
            else System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            wp++;
        }
        counter++;

        for (int i = slotStart[2]; i < vatican[2]; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "     " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_YELLOW.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            if (winPoints[wp]<10) {
                System.out.print(Color.ANSI_YELLOW.escape() + " |" + blueEscape);
            }
            else System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            wp++;
        }
        counter++;
        for (int i = vatican[2]+1; i < 24; i++){
            if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "     " + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            }
            else {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
                if (i<10)
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
                if (winPoints[wp]<10) {
                    System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
                }
                wp++;
            }
            counter++;
        }
        /*if (counter!=3&&counter!=6&&counter!=9&&counter!=12&&counter!=15&&counter!=18&&counter!=21&&counter!=24){
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "     |" + blueEscape);
        }
        else {
            System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            System.out.print(Color.ANSI_GREEN.escape() + counter + blueEscape);
            if (counter<10)
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
            System.out.print(Color.ANSI_YELLOW.escape() + winPoints[wp] + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + " |" + blueEscape);
            wp++;
        }
        counter++; */

        System.out.print(Color.ANSI_RESET.escape() + "\n");
    }

    /**
     * draws a slot of the third line
     * @param posThere
     * @param blueZeroYellowOne
     * @param lastSlot
     */
    public static void drawThirdLineSlot(boolean posThere, boolean blueZeroYellowOne, boolean lastSlot){
        String blueEscape = Color.ANSI_BLUE.escape();
        if (!blueZeroYellowOne){
            System.out.print(Color.ANSI_BLUE.escape() + "|   " + blueEscape);
            if (posThere)
                System.out.print(Color.ANSI_RED.escape() + "P" + blueEscape);
            else
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
        }
        if (blueZeroYellowOne){
            System.out.print(Color.ANSI_YELLOW.escape() + "|   " + blueEscape);
            if (posThere)
                System.out.print(Color.ANSI_RED.escape() + "P" + blueEscape);
            else
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
            System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
        }
        if(lastSlot){
            if(!blueZeroYellowOne)
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            if(blueZeroYellowOne)
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
        }
    }


    /**
     * draws the third line
     * @param vatican
     * @param vaticanLength
     * @param pos
     */
    public static void drawThirdLine(int[] vatican, int[] vaticanLength, int pos){
        String blueEscape = Color.ANSI_BLUE.escape();
        int[] slotStart = new int[3];
        int counter=0;
        for (int i=0; i < 3; i++){
            slotStart[i]=vatican[i]-vaticanLength[i]+1;
        }

        for (int i = 0; i < slotStart[0]-1; i++){
            drawThirdLineSlot(pos==counter, false, false);
            counter++;
        }
        drawThirdLineSlot(pos==counter, false, true);
        counter++;

        for (int i = slotStart[0]; i < vatican[0]; i++){
            drawThirdLineSlot(pos==counter, true, false);
            counter++;
        }
        drawThirdLineSlot(pos==counter, true, true);
        counter++;

        for (int i = vatican[0]+1; i < slotStart[1]-1; i++){
            drawThirdLineSlot(pos==counter, false, false);
            counter++;
        }
        drawThirdLineSlot(pos==counter, false, true);
        counter++;

        for (int i = slotStart[1]; i < vatican[1]; i++){
            drawThirdLineSlot(pos==counter, true, false);
            counter++;
        }
        drawThirdLineSlot(pos==counter, true, true);
        counter++;

        for (int i = vatican[1]+1; i < slotStart[2]-1; i++){
            drawThirdLineSlot(pos==counter, false, false);
            counter++;
        }
        drawThirdLineSlot(pos==counter, false, true);
        counter++;

        for (int i = slotStart[2]; i < vatican[2]; i++){
            drawThirdLineSlot(pos==counter, true, false);
            counter++;
        }
        drawThirdLineSlot(pos==counter, true, true);
        counter++;

        for (int i = vatican[2]+1; i < 24; i++){
            drawThirdLineSlot(pos==counter, false, false);
            counter++;
        }
        //drawThirdLineSlot(pos==counter, false, true);
        counter++;

        System.out.print(Color.ANSI_RESET.escape() + "\n");
    }

    /**
     * draws a slot of the fourth line
     * @param posThere
     * @param blueZeroYellowOne
     * @param lastSlot
     */
    public static void drawFourthLineSlot(boolean posThere, boolean blueZeroYellowOne, boolean lastSlot){
        String blueEscape = Color.ANSI_BLUE.escape();
        if (!blueZeroYellowOne){
            System.out.print(Color.ANSI_BLUE.escape() + "|      " + blueEscape);
            if (posThere)
                System.out.print(Color.ANSI_RED.escape() + "+" + blueEscape);
            else
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
        }
        if (blueZeroYellowOne){
            System.out.print(Color.ANSI_YELLOW.escape() + "|      " + blueEscape);
            if (posThere)
                System.out.print(Color.ANSI_RED.escape() + "+" + blueEscape);
            else
                System.out.print(Color.ANSI_BLUE.escape() + " " + blueEscape);
        }
        if(lastSlot){
            if(!blueZeroYellowOne)
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            if(blueZeroYellowOne)
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
        }
    }

    /**
     * draws the fourth line
     * @param vatican
     * @param vaticanLength
     */
    public static void drawFourthLine(int[] vatican, int[] vaticanLength){
        String blueEscape = Color.ANSI_BLUE.escape();
        int[] slotStart = new int[3];
        for (int i=0; i < 3; i++){
            slotStart[i]=vatican[i]-vaticanLength[i]+1;
        }
        int counter=0;

        for (int i = 0; i < slotStart[0]-1; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, false);
            counter++;
        }
        drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, true);
        counter++;

        for (int i = slotStart[0]; i < vatican[0]; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), true, false);
            counter++;
        }
        drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), true, true);
        counter++;

        for (int i = vatican[0]+1; i < slotStart[1]-1; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, false);
            counter++;
        }
        drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, true);
        counter++;

        for (int i = slotStart[1]; i < vatican[1]; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), true, false);
            counter++;
        }
        drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), true, true);
        counter++;

        for (int i = vatican[1]+1; i < slotStart[2]-1; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, false);
            counter++;
        }
        drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, true);
        counter++;

        for (int i = slotStart[2]; i < vatican[2]; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), true, false);
            counter++;
        }
        drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), true, true);
        counter++;

        for (int i = vatican[2]+1; i < 24; i++){
            drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, false);
            counter++;
        }
        //drawFourthLineSlot((vatican[0]==counter)||(vatican[1]==counter)||(vatican[2]==counter), false, true);
        counter++;

        System.out.print("\n");
    }

    /**
     * draws a coloured line
     * @param vatican
     * @param vaticanLength
     */
    public static void drawColouredLine(int[] vatican, int[] vaticanLength) {
        String blueEscape = Color.ANSI_BLUE.escape();
        int[] slotStart = new int[3];
        for (int i=0; i < 3; i++){
            slotStart[i]=vatican[i]-vaticanLength[i]+1;
        }

        for (int i = 0; i < slotStart[0]-1; i++){
            drawLineSlot(false, false);
        }
        drawLineSlot(false, true);

        for (int i = slotStart[0]; i < vatican[0]; i++){
            drawLineSlot(true, false);
        }
        drawLineSlot(true, true);

        for (int i = vatican[0]+1; i < slotStart[1]-1; i++){
            drawLineSlot(false, false);
        }
        drawLineSlot(false, true);

        for (int i = slotStart[1]; i < vatican[1]; i++){
            drawLineSlot(true, false);
        }
        drawLineSlot(true, true);

        for (int i = vatican[1]+1; i < slotStart[2]-1; i++){
            drawLineSlot(false, false);
        }
        drawLineSlot(false, true);

        for (int i = slotStart[2]; i < vatican[2]; i++){
            drawLineSlot(true, false);
        }
        drawLineSlot(true, true);

        for (int i = vatican[2]+1; i < 24; i++){
            drawLineSlot(false, false);
        }
        //drawLineSlot(false, true);

        System.out.print(Color.ANSI_RESET.escape() + "\n");
    }

    /**
     * draws a slot
     * @param blueZeroYellowOne
     * @param endOfReport
     */
    private static void drawLineSlot( boolean blueZeroYellowOne, boolean endOfReport){
        String blueEscape = Color.ANSI_BLUE.escape();
        if (!blueZeroYellowOne ){
            if (!endOfReport){
                System.out.print(Color.ANSI_BLUE.escape() + "--------" + blueEscape);
            }
            if (endOfReport){
                System.out.print(Color.ANSI_BLUE.escape() + "---------" + blueEscape);
            }
        }

        if (blueZeroYellowOne ){
            if (!endOfReport){
                System.out.print(Color.ANSI_YELLOW.escape() + "--------" + blueEscape);
            }
            if (endOfReport){
                System.out.print(Color.ANSI_YELLOW.escape() + "---------" + blueEscape);
            }
        }
    }

    private static void drawPosSlot(int pos, boolean posThere) {
        String blueEscape = Color.ANSI_BLUE.escape();

        if (((pos >= 0) && (pos < 4)) || ((pos > 8) && (pos < 11)) || (pos == 17)) {
            if (posThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "P" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
            }
            if (!posThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
            }
        }
        if (pos == 4 || pos == 11 || pos == 18) {
            if (posThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "P" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            }

            if (!posThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            }

        }


        if (((pos > 4) && (pos < 8)) || ((pos > 11) && (pos < 16)) || ((pos > 18) && (pos < 24))) {
            if (posThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "P" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
            }
            if (!posThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
            }
        }
        if (pos == 8 || pos == 16 || pos == 24) {
            if (posThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "P" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "   " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            }

            if (!posThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            }

        }

    }

    private static void drawVatSlot(int vatPos, boolean vatPosThere){
        String blueEscape = Color.ANSI_BLUE.escape();

        if (((vatPos >= 0) && (vatPos < 4)) || ((vatPos > 8) && (vatPos < 11)) || (vatPos == 17)) {
            if (vatPosThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "      " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "+" + blueEscape);
            }
            if (!vatPosThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
            }
        }
        if (vatPos == 4 || vatPos == 11 || vatPos == 18) {
            if (vatPosThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "      " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "+" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            }

            if (!vatPosThere) {
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "|" + blueEscape);
            }

        }


        if (((vatPos > 4) && (vatPos < 8)) || ((vatPos > 11) && (vatPos < 16)) || ((vatPos > 18) && (vatPos < 24))) {
            if (vatPosThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "      " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "+" + blueEscape);
            }
            if (!vatPosThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
            }
        }
        if (vatPos == 8 || vatPos == 16 || vatPos == 24) {
            if (vatPosThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "      " + blueEscape);
                System.out.print(Color.ANSI_RED.escape() + "+" + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            }

            if (!vatPosThere) {
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
                System.out.print(Color.ANSI_BLUE.escape() + "       " + blueEscape);
                System.out.print(Color.ANSI_YELLOW.escape() + "|" + blueEscape);
            }

        }
    }
}
