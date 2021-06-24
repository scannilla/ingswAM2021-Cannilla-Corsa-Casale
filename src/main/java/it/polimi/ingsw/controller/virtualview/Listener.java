package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

public abstract class Listener {

    protected final MessageHandler mHandler;

    protected Listener(MessageHandler mHandler) {
        this.mHandler = mHandler;
    }

    public void notifyChange(Object update) throws EndingGameException {
    }

    public void notifyChange(Object update, String nickname) throws EndingGameException {
    }

    public void notifyChange(Object update, String nickname, int code) throws EndingGameException {

    }

}
