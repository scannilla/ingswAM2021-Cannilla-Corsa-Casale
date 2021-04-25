package it.polimi.ingsw.leader;

import it.polimi.ingsw.resources.Resource;

public class LeaderOfProductions extends LeaderCard {
    /**
     * required resource to activate the production
     */
    private Resource requiredResource;



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
