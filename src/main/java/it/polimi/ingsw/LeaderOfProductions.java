package it.polimi.ingsw;

public class LeaderOfProductions extends LeaderCard{

    private Resource requiredResource;

    public LeaderOfProductions(int[] requiredRes, int[][] requiredCard, int wp, Resource requiredResource) {
        super(requiredRes, requiredCard, wp);
        this.requiredResource = requiredResource;
    }

    public int getAbility() {
        return 3;
    }

    public Resource getRequiredResource() {
        return requiredResource;
    }
}
