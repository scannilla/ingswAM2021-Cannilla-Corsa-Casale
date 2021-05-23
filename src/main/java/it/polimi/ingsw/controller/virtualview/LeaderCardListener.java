package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.leader.LeaderCard;

import java.util.ArrayList;

public class LeaderCardListener extends Listener {

    @Override
    public void notifyChange(Object update) {
        ArrayList<LeaderCard> cards = (ArrayList<LeaderCard>)update;
    }
}
