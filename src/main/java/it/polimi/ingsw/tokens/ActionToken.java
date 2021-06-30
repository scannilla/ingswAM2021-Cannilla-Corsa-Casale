package it.polimi.ingsw.tokens;


import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;

import java.io.Serializable;

public class ActionToken implements Serializable {

    /**
     * This attribute represents the type of action that the player can activate
     */
    private final int action; //legend: 0: -2 green, 1: -2 yellow, 2: -2 blue, 3: -2 purple, 4: black cross +2, 5: black cross +1, shuffle

    /**
     * constructor of Action Token initialized to action
     * @param action int
     */
    public ActionToken(int action) {
        this.action = action;
    }
    /**
     * This method get the action from this action token
     * @return int action
     */
    public int getAction() {
        return action;
    }

    public void activateAction(Game game, Player lorenzo) throws EndingGameException {
        switch (this.action) {
            case 0: game.getCardsMarket().deleteCards(0);
                    break;
            case 1: game.getCardsMarket().deleteCards(1);
                    break;
            case 2: game.getCardsMarket().deleteCards(2);
                    break;
            case 3: game.getCardsMarket().deleteCards(3);
                    break;
            case 4: lorenzo.increaseFaith(2);
                    break;
            case 5: lorenzo.increaseFaith(1);
                    break;
        }
        if(action>3)
            EventManager.notifyListener(EventType.PERSONALBOARD, lorenzo.getPersonalBoard());
        else
            EventManager.notifyListener(EventType.CARDMARKET, game.getCardsMarket());
    }
}
