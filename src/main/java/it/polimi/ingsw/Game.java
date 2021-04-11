package it.polimi.ingsw;


import java.io.File;
import java.io.IOException;

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
    private final ProductionCardsDeck productionCardsDeck = new ProductionCardsDeck();
    /**
     * This attribute represents full deck of 16 leader cards
     */
    private final LeaderCardsDeck leaderCardsDeck = new LeaderCardsDeck();
    /**
     * This attribute represents pile of action tokens
     */
    private ActionToken[] actionTokensPile;
    /**
     * create a new Market Structure
     */
    private final MarketStructure market = new MarketStructure();



    /**
     * This method creates a new Game and initializes its parameters (numberOfPlayers and array players)
     * @param numberOfPlayers int
     * @param players Player[]
     */
    public Game(int numberOfPlayers, Player[] players) {
        this.numberOfPlayers = numberOfPlayers;
        this.players = players;
    }
    /**
     * This method initializes the game to the start state
     */
    public void initialSet() throws IOException {//TODO sets the game for the start
    /* create prod card deck, create leader card deck, if numberOfPlayers==1 create action token pile */
        //GSON.actionTokensPileParser();
        GSON.productionCardParser(new File("src/main/java/it/polimi/ingsw/prodcards.json"));
        //GSON.leaderCardParser(new File(""));
        //GSON.marketStructureParser();
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
    public ProductionCardsDeck getProductionCardsDeck() {
        return productionCardsDeck;
    }
    /**
     * This method returns deck of Leader cards
     * @return leaderCardsDeck
     */
    public LeaderCardsDeck getLeaderCardsDeck() {
        return leaderCardsDeck;
    }
    /**
     * This method returns pile of Action Tokens
     * @return actionTokensPile
     */
    public ActionToken[] getActionTokensPile() {
        return actionTokensPile;
    }

    /**
     * getter of number of players playing this match
     * @return numberOfPlayers
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * getter of player Array playing this match
     * @return players
     */
    public Player[] getPlayers() {
        return players;
    }
}
