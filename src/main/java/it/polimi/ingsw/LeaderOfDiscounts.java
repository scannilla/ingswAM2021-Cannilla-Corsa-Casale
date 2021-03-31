package it.polimi.ingsw;

public class LeaderOfDiscounts extends LeaderCard{

    /**
     * resource that can be excluded from the price of a general card
     */
    private final Resource discountedRes;

    /**
     * constructor of this Leader Card
     * @param requiredRes int[]
     * @param requiredCard int[][]
     * @param wp int
     * @param discountedRes Resource
     */
    public LeaderOfDiscounts(int[] requiredRes, int[][] requiredCard, int wp, Resource discountedRes) {
        super(requiredRes, requiredCard, wp);
        this.discountedRes = discountedRes;
    }

    /**
     * getter ability of this Leader Card (0 = discount)
     * @return 0
     */
    public int getAbility() {
        return 0;
    }

    /**
     * return the resource that can be excluded from the price of a general card
     * @return discountedRes
     */
    public Resource getDiscountedRes() {
        return discountedRes;
    }
}
