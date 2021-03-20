package it.polimi.ingsw;

public class ProductionCard {

    private int type;
    private int level;
    private int wp; //WinPoints
    private int[] costArray; //Needed resources to buy card
    private int[] requiredRes; //Needed resources to start production
    private int[] givenRes; //Given resources from production

    public ProductionCard(int type, int level, int wp, int[] costArray, int[] requiredRes, int[] givenRes) {
        this.type = type;
        this.level = level;
        this.wp = wp;
        this.costArray = costArray;
        this.requiredRes = requiredRes;
        this.givenRes = givenRes;
    }

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getWp() {
        return wp;
    }

    public int[] getCostArray() {
        return costArray;
    }

    public int[] getRequiredRes() {
        return requiredRes;
    }

    public int[] getGivenRes() {
        return givenRes;
    }



}
