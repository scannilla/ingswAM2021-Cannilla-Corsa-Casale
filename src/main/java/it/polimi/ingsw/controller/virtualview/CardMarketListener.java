package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.production.ProductionCardsMarket;

public class CardMarketListener extends Listener {

    public CardMarketListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 651, null));
    }

    @Override
    public void notifyChange(Object update, String nickname) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 651, nickname));
    }
}
