package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;

public class PersonalBoardListener extends Listener {

    public PersonalBoardListener(MessageHandler mHandler) {
        super(mHandler);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 652, null));
    }

    @Override
    public void notifyChange(Object update, String nickname) throws EndingGameException {
        mHandler.sendObjectToClient(new ObjectMessage(update, 652, nickname));
    }
}
