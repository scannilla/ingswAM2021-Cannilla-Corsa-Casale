package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.leader.LeaderCard;

public class ActiveLeaderCardsDraw {
    public static void drawActiveLeaderCards(Player player ){
        LeaderCard[] cardsArray = new LeaderCard[3];
        cardsArray=player.getActiveLeaderCards();
    }
}
