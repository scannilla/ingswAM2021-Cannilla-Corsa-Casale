package it.polimi.ingsw.cli;

import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.Strongbox;

public class StrongboxDraw {

    //private Strongbox strongboxDrawn;

    public void drawStrongbox(Strongbox strongboxDrawn){
        String resetEscape = Color.ANSI_RESET.escape();
        int strongboxCoins = strongboxDrawn.getCoins();
        int strongboxServants = strongboxDrawn.getServants();
        int strongboxStones = strongboxDrawn.getStones();
        int strongboxShields = strongboxDrawn.getShields();
        int counter = 15;
        int numberOfRows = (strongboxCoins+strongboxServants+strongboxShields+strongboxStones)%15;
        while (numberOfRows != 0){
            System.out.print(resetEscape + "-------------------------------------------------------------");
            System.out.print("\n");
            while(strongboxCoins!=0&&counter!=0){
                System.out.print(resetEscape + "| ");
                System.out.print(Color.ANSI_YELLOW.escape() + "C " + resetEscape);
                strongboxCoins--;
                counter--;
            }
        }
    }

    public void drawResource(int coins, int servants, int stones, int shields){
        if(coins==0)
            if(servants==0)
                if(stones==0)
                    if(shields==0){
                        System.out.print(" ");
                    }
                else
                    System.out.print(Color.ANSI_BLUE.escape() + "H ");

    }

}
