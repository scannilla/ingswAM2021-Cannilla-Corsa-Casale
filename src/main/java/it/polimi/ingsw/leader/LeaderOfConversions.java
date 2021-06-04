package it.polimi.ingsw.leader;

import it.polimi.ingsw.resources.Resource;


public class LeaderOfConversions extends LeaderCard {
    /**
     * White Marble = convertedResource
     */
    private Resource convertedResource;

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
