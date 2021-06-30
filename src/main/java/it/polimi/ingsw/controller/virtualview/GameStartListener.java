package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

public class GameStartListener extends Listener{

    public GameStartListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendMessageToClient("All players connected", 204);
    }
}
