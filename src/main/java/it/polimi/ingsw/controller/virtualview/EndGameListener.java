package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkserver.ServerMain;


public class EndGameListener extends Listener {


    public EndGameListener() {
        super(null);
    }

    @Override
    public void notifyChange(Object update) throws EndingGameException {
        ServerMain.endGame();
    }
}
