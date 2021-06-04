package it.polimi.ingsw;


import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.leader.LeaderCardsDeck;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.tokens.ActionToken;
import it.polimi.ingsw.tokens.ActionTokenPile;
import it.polimi.ingsw.leader.LeaderCard;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{
    /**
     * This attribute represents the number of player in the game (1<n<4)
     */
    private int numberOfPlayers;
    /**
     * This attribute represents the list of players who is playing in the game
     */
    private final ArrayList<Player> players = new ArrayList<>();
    /**
     * This attribute represents full deck of 48 production cards
     */
    private final ProductionCardsDeck productionCardsDeck = new ProductionCardsDeck();
    /**
     * This attribute represents full deck of 16 leader cards
     */
    private LeaderCardsDeck leaderCardsDeck;
    /**
     * This attribute represents pile of action tokens
     */
    private ActionTokenPile actionTokensPile;
    /**
     * create a new Market Structure
     */
    private MarketStructure market;

    private final VaticanReport vaticanReport = new VaticanReport();

    private final ProductionCardsMarket cardsMarket = new ProductionCardsMarket();

    private ProductionCardsDeck deck;

    private Player activePlayer;

    private Player lorenzo;



    /**
     * This method initializes the game to the start state
     */
    public void initialSet() throws IOException {
        for(Player p: players)
            p.setConnectedGame(this);
    /* create prod card deck, create leader card deck, if numberOfPlayers==1 create action token pile */
        if (numberOfPlayers==1) {
            actionTokensPile = GSON.actionTokensPileParser(new File("src/main/java/it/polimi/ingsw/tokens/actiontokens.json"));
            lorenzo = new Player("Lorenzo The Magnificent");
        }
        deck = GSON.productionCardParser(new File("src/main/java/it/polimi/ingsw/production/prodcards.json"));
        leaderCardsDeck = GSON.leaderCardParser(new File("src/main/java/it/polimi/ingsw/leader/allLeaderCards.json"));
        market = GSON.marketStructureParser(new File("src/main/java/it/polimi/ingsw/marbles/marbles.json"));
        market.initializeMarket();
        cardsMarket.setMarket(deck);
        leaderCardsDeck.shuffleDeck();
        players.get(0).setActive(true);
        activePlayer = players.get(0);
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
    public ActionTokenPile getActionTokensPile() {
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
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * getter of vatican report
     * @return vaticanReport
     */

    public VaticanReport getVaticanReport() {
        return vaticanReport;
    }

    /**
     * this method activates the vatican report
     * @param event int
     */

    public void activateEvent(int event) {
        for (Player p : players) {
            if(p.getPersonalBoard().getPosition()>(this.getVaticanReport().getActivationPosition()[event])-(this.getVaticanReport().getReportsLength()[event]))
                p.setWp(p.getWp()+this.getVaticanReport().getPopeFavourTile()[event]);
        }
        this.getVaticanReport().eventActivated(event);
    }

    /**
     * getter of the market
     * @return market
     */

    public MarketStructure getMarket() {
        return market;
    }

    /**
     * getter of the cards market
     * @return cardsMarket
     */

    public ProductionCardsMarket getCardsMarket() {
        return cardsMarket;
    }

    /**
     * return the next player of current player
     * @param currentPlayer Player
     * @return if the player next to current player is the last return the first player of the list
     *         else return the player next to the current
     * @throws IllegalArgumentException e
     */
    public Player nextPlayer(Player currentPlayer) throws IllegalArgumentException {
        for (Player p : players) {
            if (p==currentPlayer) {
                if (players.indexOf(p) == numberOfPlayers - 1 && numberOfPlayers != 1)
                    return players.get(0);
                else if (numberOfPlayers != 1)
                    return players.get(players.indexOf(p) + 1);
                else
                    //TODO lorenzo's turn
                    return null;
            }
        }
        throw new IllegalArgumentException();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Player previousPlayer(Player player) {
        for (Player p : players) {
            if (p==player) {
                if (players.indexOf(p) == 0 && numberOfPlayers != 1)
                    return players.get(numberOfPlayers-1);
                else if (numberOfPlayers != 1)
                    return players.get(players.indexOf(p) - 1);
                else
                    //TODO lorenzo's turn
                    return null;
            }
        }
        throw new IllegalArgumentException();
    }


    public void endTurn() throws EndingGameException, RuntimeException {
        if(activePlayer.isLast())
            throw new RuntimeException();
        activePlayer.setActive(false);
        if(numberOfPlayers==1) {
            lorenzoTurn();
        }
        else {
            activePlayer = nextPlayer(activePlayer);
        }
        activePlayer.setActive(true);
    }

    public void lorenzoTurn() throws EndingGameException {
        ActionToken token = actionTokensPile.popToken();
        token.activateAction(this, lorenzo);
    }

}
