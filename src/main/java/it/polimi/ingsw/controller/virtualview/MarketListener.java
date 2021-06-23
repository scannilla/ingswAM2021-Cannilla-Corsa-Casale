package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.cli.MarketDraw;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.marbles.MarketStructure;

public class MarketListener extends Listener{

    public MarketListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 650, null));
    }
}
