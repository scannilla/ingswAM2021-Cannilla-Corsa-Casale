package it.polimi.ingsw;

public class Player {
    /**
     * This attribute represents this player's personal board
     */
    private PersonalBoard personalboard;
    /**
     * This attribute represents if a player is active or not
     */
    private boolean active;
    /**
     * This attribute represents player's leader cards
     */
    private LeaderCard[] leadercards;
    /**
     * This attribute represents player's nickname
     */
    private String nickname;
    /**
     * This method create a new player initializing all his attribute
     * @param personalboard PersonalBoard
     * @param active boolean
     * @param leadercards LeaderCard[]
     * @param nickname String
     */
    public Player(PersonalBoard personalboard, boolean active, LeaderCard[] leadercards, String nickname) { //player constructor
        this.personalboard = personalboard;
        this.active = active;
        this.leadercards = leadercards;
        this.nickname = nickname;
    }
    /**
     * This method returns player's personal board with all its attributes
     * @return personalboard
     */
    public PersonalBoard getPersonalboard() { //getter of personal board
        return personalboard;
    }
    /**
     * This method returns true if player is active, false instead
     * @return active
     */
    public boolean isActive() { //returns if the player is active or not
        return active;
    }
}
