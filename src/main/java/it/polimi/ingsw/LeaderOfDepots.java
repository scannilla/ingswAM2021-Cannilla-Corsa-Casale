package it.polimi.ingsw;

import java.lang.ref.ReferenceQueue;

public class LeaderOfDepots extends LeaderCard{
    /**
     * resource that can be added to extraDepot
     */
    private final Resource extraResource;
    private final Resource[] extraDepot;

    /**
     * constructor of this Leader Card
     * @param requiredRes int[]
     * @param requiredCard int[][]
     * @param wp int
     * @param extraResource Resource
     */
    public LeaderOfDepots(int[] requiredRes, int[][] requiredCard, int wp, Resource extraResource) {
        super(requiredRes, requiredCard, wp);
        this.extraResource = extraResource;
        extraDepot = new Resource[2];
        extraDepot[0] = null; extraDepot[1] = null;
    }

    /**
     * getter ability of this Leader Card (1 = extraDepot)
     * @return 1
     */
    private int getAbility() {
        return 1;
    }

    /**
     * getter resource of extraDepot
     * @return extraResource
     */
    public Resource getResource() {
        return extraResource;
    }

    /**
     * getter of extra depot
     * @return extraDepot
     */
    public Resource[] getExtraDepot() {
        return extraDepot;
    }

    /**
     * add one element of extraResource in extraDepot
     */
    public void addNewResource(){
        if (extraDepot[0] == null)
            extraDepot[0] = extraResource;
        else extraDepot[1] = extraResource;
    }

    /**
     * use one element of extraDepot
     */
    public void useResource(){
        if (extraDepot[1] != null)
            extraDepot[1] = null;
        else extraDepot[0] = null;
    }
}
