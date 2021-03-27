package it.polimi.ingsw;

public class LeaderOfConversions extends LeaderCard{

    private Resource convertedResource;

    public LeaderOfConversions(int[] requiredRes, int[][] requiredCard, int wp, Resource convertedResource) {
        super(requiredRes, requiredCard, wp);
        this.convertedResource = convertedResource;
    }

    public int getAbility() {
        return 2;
    }

    public Resource getConvertedResource() {
        return convertedResource;
    }
}
