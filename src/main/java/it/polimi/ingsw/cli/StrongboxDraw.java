package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.Strongbox;

import java.util.ArrayList;

public class StrongboxDraw {

    //private Strongbox strongboxDrawn;

    public static void drawStrongbox(PersonalBoard personalBoard){
        String resetEscape = Color.ANSI_RESET.escape();
        int resCounter = 0;
        int numberOfRows[] = personalBoard.getStrongbox().getStrongboxResourcesAmount();
        int numberOfRes = numberOfRows[0]+numberOfRows[1]+numberOfRows[2]+numberOfRows[3];
        int numberOfRow = numberOfRes/15;
        numberOfRow++;
        System.out.print(resetEscape + "-------------------------------------------------------------");
        while (numberOfRow != 0){
            int counter = 15;
            System.out.print("\n");
            while (counter!=0) {
                if (resCounter<numberOfRes)
                 {
                 System.out.print(resetEscape + "| ");
                 drawResource(personalBoard, resCounter);
                  counter--;
                  resCounter++;
                 }
                if(resCounter>=numberOfRes){
                    System.out.print(resetEscape + "|   ");
                    counter--;
                }
            }
            System.out.print(resetEscape + "|");
            System.out.print("\n");
            System.out.print(resetEscape + "-------------------------------------------------------------");
            numberOfRow--;
            }
        }


    public static void drawResource(PersonalBoard personalBoards, int pos){
        String resetEscape = Color.ANSI_RESET.escape();
        Strongbox thisStrongbox = personalBoards.getStrongbox();
        int k=0;
        ArrayList<Resource> resources = new ArrayList<>();
        for(int i : thisStrongbox.getStrongboxResourcesAmount()) {
            for (int j = 0; j<i; j++) {
                resources.add(new Resource(k));
            }
            k++;
        }
        if (resources.get(pos).equals(new Resource("coin")))
            System.out.print(Color.ANSI_YELLOW.escape() + "C " + resetEscape);
        if (resources.get(pos).equals(new Resource("stone")))
            System.out.print(Color.ANSI_GREY.escape() + "R " + resetEscape);
        if (resources.get(pos).equals(new Resource("shield")))
            System.out.print(Color.ANSI_BLUE.escape() + "H " + resetEscape);
        if (resources.get(pos).equals(new Resource("servant")))
            System.out.print(Color.ANSI_PURPLE.escape() + "S " + resetEscape);
        if (resources.get(pos).equals(null))
            System.out.print(Color.ANSI_PURPLE.escape() + "  " + resetEscape);

    }

}
