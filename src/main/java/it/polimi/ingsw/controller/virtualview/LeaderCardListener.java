package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.leader.LeaderCard;

import java.util.ArrayList;

public class LeaderCardListener extends Listener {


    public LeaderCardListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 653, null));
    }

    @Override
    public void notifyChange(Object update, String nickname) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 653, nickname));
    }

    @Override
    public void notifyChange(Object update, String nickname, int code) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, code, nickname));
    }
}
