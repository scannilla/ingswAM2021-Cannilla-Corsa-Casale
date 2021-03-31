package it.polimi.ingsw;

public class LeaderOfProductions extends LeaderCard{
    /**
     * required resource to activate the production
     */
    private Resource requiredResource;

    /**
     * constructor of this Leader Card
     * @param requiredRes int[]
     * @param requiredCard int[][]
     * @param wp int
     * @param requiredResource Resource
     */
    public LeaderOfProductions(int[] requiredRes, int[][] requiredCard, int wp, Resource requiredResource) {
        super(requiredRes, requiredCard, wp);
        this.requiredResource = requiredResource;
    }

    /**
     * getter of ability of this Leader Card (3 = production)
     * @return 3
     */
    public int getAbility() {
        return 3;
    }

    /**
     * getter of required resources to activate production
     * @return requiredResource
     */
    public Resource getRequiredResource() {
        return requiredResource;
    }
}
