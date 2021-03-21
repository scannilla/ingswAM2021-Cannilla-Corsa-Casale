package it.polimi.ingsw;

public class Player {
    private PersonalBoard personalboard;
    private boolean active;
    private LeaderCard[] leadercards;
    private String nickname;

    public Player(PersonalBoard personalboard, boolean active, LeaderCard[] leadercards, String nickname) { //player constructor
        this.personalboard = personalboard;
        this.active = active;
        this.leadercards = leadercards;
        this.nickname = nickname;
    }

    public PersonalBoard getPersonalboard() { //getter of personal board
        return personalboard;
    }

    public boolean isActive() { //returns if the player is active or not
        return active;
    }
}
