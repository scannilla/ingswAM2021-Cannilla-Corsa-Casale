package it.polimi.ingsw;

public abstract class LeaderCard {
    /**
     * represents required resources to buy this Leader Card
     */
    private final int[] requiredRes;
    /**
     * represents required cards to buy this leader card
     */
    private final int[][] requiredCard;
    /**
     * represents this Leader Card win points
     */
    private final int wp;

    /**
     * constructor of this Leader Card
     * @param requiredRes int[]
     * @param requiredCard int[][]
     * @param wp int
     */
    public LeaderCard(int[] requiredRes, int[][] requiredCard, int wp) {
        this.requiredRes = requiredRes;
        this.requiredCard = requiredCard;
        this.wp = wp;
    }

    /**
     * getter required resources to buy this Leader Card
     * @return requiredRes
     */
    public int[] getRequiredRes() {
        return requiredRes;
    }

    /**
     * getter Required Cards to buy this Leader Card
     * @return requiredCard
     */
    public int[][] getRequiredCard() {
        return requiredCard;
    }

    /**
     * getter win points of this Leader Card
     * @return wp
     */
    public int getWp() {
        return wp;
    }
}