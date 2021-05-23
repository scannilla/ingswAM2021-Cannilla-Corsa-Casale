package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.production.ProductionCardsMarket;

public class CardMarketListener extends Listener {

    @Override
    public void notifyChange(Object update) {
        ProductionCardsMarket market = (ProductionCardsMarket)update;
    }
}
