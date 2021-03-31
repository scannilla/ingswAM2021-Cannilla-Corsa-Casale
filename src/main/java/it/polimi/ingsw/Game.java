package it.polimi.ingsw;

public class Game {
    /**
     * This attribute represents the number of player in the game (1<n<4)
     */
    private final int numberOfPlayers;
    /**
     * This attribute represents the list of players who is playing in the game
     */
    private final Player[] players;
    /**
     * This attribute represents full deck of 48 production cards
     */
    private ProductionCard[] productionCardsDeck;
    /**
     * This attribute represents full deck of 16 leader cards
     */
    private LeaderCard[] leaderCardsDeck;
    /**
     * This attribute represents pile of action tokens
     */
    private ActionToken[] actionTokensPile;
    private final MarketStructure market = new MarketStructure();
    //TODO create market structure and its working
    /**
     * This method creates a new Game and initializes its parameters (numberofplayers and array players)
     * @param numberofplayers int
     * @param players Player[]
     */
    public Game(int numberofplayers, Player[] players) {
        this.numberOfPlayers = numberofplayers;
        this.players = players;
    }
    /**
     * This method initializes the game to the start state
     */
    public void initialSet() {//TODO sets the game for the start
    /* create prod card deck, create leader card deck, if numberoofplayers==1 create action token pile */
        market.initializeMarket();
    }
    /**
     * This method gives to each player 4 leader cards
     */
    public void giveLeaderCards() { //TODO gives to each player 4 leader cards

    }
    /**
     * This method returns deck of Production Cards
     * @return ProductionCard[] productionCardsDeck
     */
    public ProductionCard[] getProductionCardsDeck() {
        return productionCardsDeck;
    }
    /**
     * This method returns deck of Leader cards
     * @return leaderCardsDeck
     */
    public LeaderCard[] getLeaderCardsDeck() {
        return leaderCardsDeck;
    }
    /**
     * This method returns pile of Action Tokens
     * @return actionTokensPile
     */
    public ActionToken[] getActionTokensPile() {
        return actionTokensPile;
    }
}
