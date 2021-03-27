package it.polimi.ingsw;

import java.lang.ref.ReferenceQueue;

public class LeaderOfDepots extends LeaderCard{

    private final Resource extraResource;
    private final Resource[] extraDepot;

    public LeaderOfDepots(int[] requiredRes, int[][] requiredCard, int wp, Resource extraResource) {
        super(requiredRes, requiredCard, wp);
        this.extraResource = extraResource;
        extraDepot = new Resource[2];
        extraDepot[0] = null; extraDepot[1] = null;
    }

    private int getAbility() {
        return 1;
    }

    public Resource getResource() {
        return extraResource;
    }

    public Resource[] getExtraDepot() {
        return extraDepot;
    }

    public void addNewResource(){
        if (extraDepot[0] == null)
            extraDepot[0] = extraResource;
        else extraDepot[1] = extraResource;
    }

    public void useResource(){
        if (extraDepot[1] != null)
            extraDepot[1] = null;
        else extraDepot[0] = null;
    }
}
