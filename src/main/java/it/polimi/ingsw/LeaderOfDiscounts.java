package it.polimi.ingsw;

public class LeaderOfDiscounts extends LeaderCard{

    private final Resource discountedRes;

    public LeaderOfDiscounts(int[] requiredRes, int[][] requiredCard, int wp, int ability, Resource discountedRes) {
        super(requiredRes, requiredCard, wp);
        this.discountedRes = discountedRes;
    }

    public int getAbility() {
        return 0;
    }

    public Resource getDiscountedRes() {
        return discountedRes;
    }
}
