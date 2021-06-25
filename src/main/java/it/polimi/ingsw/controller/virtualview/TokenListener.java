package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

public class TokenListener extends Listener{

    public TokenListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 656, null));
    }
}
