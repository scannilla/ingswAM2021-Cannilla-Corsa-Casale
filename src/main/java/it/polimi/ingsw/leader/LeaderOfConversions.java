package it.polimi.ingsw.leader;

import it.polimi.ingsw.resources.Resource;

public class LeaderOfConversions extends LeaderCard {
    /**
     * White Marble = convertedResource
     */
    private Resource convertedResource;

    /**
     * constructor Leader Card with ability Conversion
     * requiredRes = resources to activate this Leader Card
     * requiredCard = Card to activate this Leader Card
     * wp = win points
     * @param requiredRes int[]
     * @param requiredCard int[][]
     * @param wp int
     * @param convertedResource Resource
     */
    public LeaderOfConversions(int[] requiredRes, int[][] requiredCard, int wp, Resource convertedResource) {
        super(requiredRes, requiredCard, wp);
        this.convertedResource = convertedResource;
    }

    /**
     * getter type ability of this Leader Card (2 = conversion)
     * @return 2
     */
    public int getAbility() {
        return 2;
    }

    /**
     * getter resource of conversion
     * White Marble = convertedResource
     * @return convertedResource
     */
    public Resource getConvertedResource() {
        return convertedResource;
    }
}
