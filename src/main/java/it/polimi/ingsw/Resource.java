package it.polimi.ingsw;

public class Resource {
    /**
     * This attribute represents type of this Resource
     */
    private final int type;
    /**
     * This method creates a Resource with this type
     * @param type int
     */
    public Resource(int type) {
        this.type = type;
    }
    /**
     * This method returns type of this Resource
     * @return int type
     */
    public int getType() {
        return type;
    }
}
