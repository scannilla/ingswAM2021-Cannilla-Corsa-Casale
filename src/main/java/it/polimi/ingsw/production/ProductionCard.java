package it.polimi.ingsw.production;

import it.polimi.ingsw.resources.Resource;

import java.io.Serializable;

public class ProductionCard implements Serializable {
    /**
     * This attribute represents the type of this Production Card
     */
    private final int type;
    /**
     * This attribute represents the level of this Production Card
     */
    private final int level;
    /**
     * This attribute represents the win points of this Production Card
     */
    private final int wp; //WinPoints
    /**
     * This attribute represents needed resources to buy a card
     */
    private final Resource[] costArray; //Needed resources to buy card
    /**
     * This attribute represents needed resources to activate production
     *  requiredRes[0] = number of needed coins
     *  requiredRes[1] = number of needed stones
     *  requiredRes[2] = number of needed servants
     *  requiredRes[3] = number of needed shields
     */
    private final Resource[] requiredRes; //Needed resources to start production
    /**
     * This attribute represents created resources from production
     * givenRes[0] = number of coins produced
     * givenRes[1] = number of stones produced
     * givenRes[2] = number of servants produced
     * givenRes[3] = number of shields produced
     */
    private final Resource[] givenRes; //Given resources from production



    private final int givenFaithPoint;
    /**
     * This method creates a new production card with this attribute
     * @param type int
     * @param level int
     * @param wp int
     * @param costArray int[]
     * @param requiredRes int[]
     * @param givenRes int[]
     */
    public ProductionCard(int type, int level, int wp, Resource[] costArray, Resource[] requiredRes, Resource[] givenRes, int givenFaithPoint) {
        this.type = type;
        this.level = level;
        this.wp = wp;
        this.costArray = costArray;
        this.requiredRes = requiredRes;
        this.givenRes = givenRes;
        this.givenFaithPoint = givenFaithPoint;
    }

    /**
     * This method returns the type of this Production Card
     * @return type
     */
    public int getType() {
        return type;
    }
    /**
     * This method returns the level of this Production Card
     * @return level
     */
    public int getLevel() {
        return level;
    }
    /**
     * This method returns win points of this Production Card
     * @return wp
     */
    public int getWp() {
        return wp;
    }
    /**
     * This method returns costs for this Production Card
     * @return costArray
     * costArray[0] = number of needed coins
     * costArray[1] = number of needed stones
     * costArray[2] = number of needed servants
     * costArray[3] = number of needed shields
     */
    public Resource[] getCostArray() {
        return costArray;
    }

    /**
     * This method returns required resources to activate the production of this Production Card
     * @return requiredRes
     *  requiredRes[0] = number of needed coins
     *  requiredRes[1] = number of needed stones
     *  requiredRes[2] = number of needed servants
     *  requiredRes[3] = number of needed shields
     */
    public Resource[] getRequiredRes() {
        return requiredRes;
    }
    /**
     * This method returns resources produced by this Production Card when activated
     * @return givenRes
     * givenRes[0] = number of coins produced
     * givenRes[1] = number of stones produced
     * givenRes[2] = number of servants produced
     * givenRes[3] = number of shields produced
     */
    public Resource[] getGivenRes() {
        return givenRes;
    }

    /**
     * getter fot the given faith points
     * @return givenFaithPoints int
     */
    public int getGivenFaithPoints() {
        return givenFaithPoint;
    }
}
