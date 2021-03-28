package it.polimi.ingsw;

public class ActionToken {

    /**
     * This attribute represents the type of action that the player can activate
     */
    private final int action;

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
