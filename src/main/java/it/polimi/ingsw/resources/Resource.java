package it.polimi.ingsw.resources;

public class Resource {
    /**
     * This attribute represents type of this Resource
     */
    private final int resType;
    /**
     * This method creates a Resource with this type
     * @param resType int
     */
    public Resource(int resType) {
        this.resType = resType;
    }
    /**
     * This method returns type of this Resource
     * @return int type
     */
    public int getResType() {
        return resType;
    }
}
