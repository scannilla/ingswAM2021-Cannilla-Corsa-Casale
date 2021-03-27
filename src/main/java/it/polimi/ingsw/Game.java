package it.polimi.ingsw;

public class Game {
    private final int numberOfPlayers;
    private final Player[] players;
    private ProductionCard[] productionCardsDeck;
    private LeaderCard[] leaderCardsDeck;
    private ActionToken[] actionTokensPile;
    //TODO create market structure and its working

    public Game(int numberofplayers, Player[] players) {
        this.numberOfPlayers = numberofplayers;
        this.players = players;
    }

    public void initialSet() {//TODO sets the game for the start
    /* create prod card deck, create leader card deck, if numberoofplayers==1 create action token pile */
    }

    public void giveLeaderCards() { //TODO gives to each player 4 leader cards

    }

    public ProductionCard[] getProductionCardsDeck() {
        return productionCardsDeck;
    }

    public LeaderCard[] getLeaderCardsDeck() {
        return leaderCardsDeck;
    }

    public ActionToken[] getActionTokensPile() {
        return actionTokensPile;
    }
}
