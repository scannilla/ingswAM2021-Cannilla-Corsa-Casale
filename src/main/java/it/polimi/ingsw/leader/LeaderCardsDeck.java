package it.polimi.ingsw.leader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderCardsDeck implements Serializable {

    /**
     * Array that represents all Leader Cards
     */
    private ArrayList<LeaderCard> leaderCardsDeck = new ArrayList<>();

    /**
     * array that represents leader of conversion cards
     */
    private final ArrayList<LeaderOfConversions> leaderOfConversionsDeck = new ArrayList<>();

    /**
     * array that represents leader of depots card
     */
    private final ArrayList<LeaderOfDepots> leaderOfDepotsDeck = new ArrayList<>();

    /**
     * array that represents leader of discount cards
     */
    private final ArrayList<LeaderOfDiscounts> leaderOfDiscountsDeck = new ArrayList<>();

    /**
     * array that represents leader of production cards
     */
    private final ArrayList<LeaderOfProductions> leaderOfProductionsDeck = new ArrayList<>();

    /**
     * getter for LeaderCardsDeck
     * @return </LeaderCard> ArrayList
     */
    public ArrayList<LeaderCard> getLeaderCardsDeck() {
        return leaderCardsDeck;
    }

    /**
     * setter for LeaderCardsDeck
     * @param </LeaderCard> ArrayList
     */
    public void setLeaderCardsDeck(ArrayList<LeaderCard> leaderCardsDeck) {
        this.leaderCardsDeck = leaderCardsDeck;
    }

    /**
     * getter for LeaderOfConversionsDeck
     * @return </LeaderOfConversions> ArrayList
     */
    public ArrayList<LeaderOfConversions> getLeaderOfConversionsDeck() {
        return leaderOfConversionsDeck;
    }

    /**
     * getter for LeaderOfDepotsDeck
     * @return </LeaderOfDepots> ArrayList
     */
    public ArrayList<LeaderOfDepots> getLeaderOfDepotsDeck() {
        return leaderOfDepotsDeck;
    }

    /**
     * getter for LeaderOfDiscountsDeck
     * @return </LeaderOfDiscounts> ArrayList
     */
    public ArrayList<LeaderOfDiscounts> getLeaderOfDiscountsDeck() {
        return leaderOfDiscountsDeck;
    }

    /**
     * getter for LeaderOfProductionsDeck
     * @return </LeaderOfProductions> ArrayList
     */
    public ArrayList<LeaderOfProductions> getLeaderOfProductionsDeck() {
        return leaderOfProductionsDeck;
    }

    /**
     * this method shuffles all leader cards
     */
    public void shuffleDeck() {
        Collections.shuffle(leaderCardsDeck);
    }

    /**
     * this method takes a card from LeaderCardsDeck
     * @return leadesCardsDeck.remove(0) LeaderCard
     */
    private LeaderCard popCard() {
        return leaderCardsDeck.remove(0);
    }

    /**
     * this method takes 4 card from LeaderCardsDeck
     * @return </LeaderCard> ArrayList
     */
    public ArrayList<LeaderCard> pick4cards () {
        ArrayList<LeaderCard> cards = new ArrayList<>();
        for (int i=0; i<4; i++)
            cards.add(this.popCard());
        return cards;
    }
}
