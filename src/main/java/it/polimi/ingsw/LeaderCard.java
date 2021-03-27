package it.polimi.ingsw;

public abstract class LeaderCard {

    private final int[] requiredRes;
    private final int[][] requiredCard;
    private final int wp;

    //constructor Leader Card
    public LeaderCard(int[] requiredRes, int[][] requiredCard, int wp) {
        this.requiredRes = requiredRes;
        this.requiredCard = requiredCard;
        this.wp = wp;
    }

    public int[] getRequiredRes() {
        return requiredRes;
    }

    public int[][] getRequiredCard() {
        return requiredCard;
    }

    public int getWp() {
        return wp;
    }
}