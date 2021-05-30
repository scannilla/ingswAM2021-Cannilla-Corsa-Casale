package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.cli.MarketDraw;
import it.polimi.ingsw.marbles.MarketStructure;

public class MarketListener extends Listener{

    @Override
    public void notifyChange(Object update) {
        MarketStructure structure = (MarketStructure)update;
        MarketDraw.draw(structure);
    }
}
