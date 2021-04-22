package it.polimi.ingsw.tokens;



public class ActionToken {

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
}
