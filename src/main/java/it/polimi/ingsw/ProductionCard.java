package it.polimi.ingsw;

public class ProductionCard {
    /**
     * This attribute represents the type of this Production Card
     */
    private int type;
    /**
     * This attribute represents the level of this Production Card
     */
    private int level;
    /**
     * This attribute represents the win points of this Production Card
     */
    private int wp; //WinPoints
    /**
     * This attribute represents needed resources to buy card
     * costArray[0] = number of needed coins
     * costArray[1] = number of needed stones
     * costArray[2] = number of needed servants
     * costArray[3] = number of needed shields
     */
    private int[] costArray; //Needed resources to buy card
    /**
     * This attribute represents needed resources to activate production
     *  requiredRes[0] = number of needed coins
     *  requiredRes[1] = number of needed stones
     *  requiredRes[2] = number of needed servants
     *  requiredRes[3] = number of needed shields
     */
    private int[] requiredRes; //Needed resources to start production
    /**
     * This attribute represents created resources from production
     * givenRes[0] = number of coins produced
     * givenRes[1] = number of stones produced
     * givenRes[2] = number of servants produced
     * givenRes[3] = number of shields produced
     */
    private int[] givenRes; //Given resources from production
    /**
     * This method creates a new production card with this attribute
     * @param type int
     * @param level int
     * @param wp int
     * @param costArray int[]
     * @param requiredRes int[]
     * @param givenRes int[]
     */
    public ProductionCard(int type, int level, int wp, int[] costArray, int[] requiredRes, int[] givenRes) {
        this.type = type;
        this.level = level;
        this.wp = wp;
        this.costArray = costArray;
        this.requiredRes = requiredRes;
        this.givenRes = givenRes;
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
    public int[] getCostArray() {
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
    public int[] getRequiredRes() {
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
    public int[] getGivenRes() {
        return givenRes;
    }



}
