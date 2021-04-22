package it.polimi.ingsw.leader;

import java.util.ArrayList;

public class LeaderCardsDeck {
    /**
     * Array that represents all Leader Cards
     */
    private ArrayList<LeaderCard> leaderCardsDeck = new ArrayList<>();
    private final ArrayList<LeaderOfConversions> leaderOfConversionsDeck = new ArrayList<>();
    private final ArrayList<LeaderOfDepots> leaderOfDepotsDeck = new ArrayList<>();
    private final ArrayList<LeaderOfDiscounts> leaderOfDiscountsDeck = new ArrayList<>();
    private final ArrayList<LeaderOfProductions> leaderOfProductionsDeck = new ArrayList<>();

    public ArrayList<LeaderCard> getLeaderCardsDeck() {
        return leaderCardsDeck;
    }

    public void setLeaderCardsDeck(ArrayList<LeaderCard> leaderCardsDeck) {
        this.leaderCardsDeck = leaderCardsDeck;
    }

    public ArrayList<LeaderOfConversions> getLeaderOfConversionsDeck() {
        return leaderOfConversionsDeck;
    }

    public ArrayList<LeaderOfDepots> getLeaderOfDepotsDeck() {
        return leaderOfDepotsDeck;
    }

    public ArrayList<LeaderOfDiscounts> getLeaderOfDiscountsDeck() {
        return leaderOfDiscountsDeck;
    }

    public ArrayList<LeaderOfProductions> getLeaderOfProductionsDeck() {
        return leaderOfProductionsDeck;
    }
}
