package it.polimi.ingsw;

public class Player {
    /**
     * This attribute represents this player's personal board
     */
    private PersonalBoard personalBoard;
    /**
     * This attribute represents if a player is active or not
     */
    private boolean active;
    /**
     * This attribute represents player's leader cards
     */
    private final LeaderCard[] leaderCards = new LeaderCard[2];

    private final LeaderCard[] activeLeaderCards = new LeaderCard[2];
    /**
     * This attribute represents player's nickname
     */
    private String nickname;
    /**
     * This method create a new player initializing all his attribute
     * @param nickname String
     */
    public Player(String nickname) { //player constructor
        this.personalBoard = new PersonalBoard();
        this.active = false;
        this.nickname = nickname;
    }
    /**
     * This method returns player's personal board with all its attributes
     * @return personalboard
     */
    public PersonalBoard getPersonalBoard() { //getter of personal board
        return personalBoard;
    }
    /**
     * This method returns true if player is active, false instead
     * @return active
     */
    public boolean isActive() { //returns if the player is active or not
        return active;
    }
}
