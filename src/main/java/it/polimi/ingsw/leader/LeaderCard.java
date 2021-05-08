package it.polimi.ingsw.leader;

import it.polimi.ingsw.resources.Resource;

public abstract class LeaderCard {
    /**
     * represents required resources to buy this Leader Card
     */
    private Resource[] requiredRes;
    /**
     * represents required cards to buy this leader card
     */
    private int[] requiredType;

    private int[] requiredLevel;

    private int ability;
    /**
     * represents this Leader Card win points
     */
    private int wp;


    /**
     * getter win points of this Leader Card
     * @return wp
     */
    public int getWp() {
        return wp;
    }

    /**
     * getter Required Resource to activate this card
     * @return requiredRes
     */
    public Resource[] getRequiredRes() {
        return requiredRes;
    }

    /**
     * getter required type of card to activate this card
     * @return requiredType
     */
    public int[] getRequiredType() {
        return requiredType;
    }

    /**
     * getter Required Level of card to activate this card
     * @return requiredLevel
     */
    public int[] getRequiredLevel() {
        return requiredLevel;
    }

    public int getAbility() {
        return ability;
    }


}