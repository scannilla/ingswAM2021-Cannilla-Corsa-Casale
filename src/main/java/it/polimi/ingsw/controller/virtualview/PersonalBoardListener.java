package it.polimi.ingsw.controller.virtualview;

import it.polimi.ingsw.PersonalBoard;

public class PersonalBoardListener extends Listener {

    @Override
    public void notifyChange(Object update) {
        PersonalBoard personalBoard = (PersonalBoard)update;
    }
}
