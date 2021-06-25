package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

public class LeaderboardListener extends Listener{

    public LeaderboardListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 657, null));
    }

    @Override
    public void notifyChange(Object update, String nickname, int code) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, code, null));
    }
}
