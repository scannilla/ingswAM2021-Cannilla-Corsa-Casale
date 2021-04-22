package it.polimi.ingsw.leader;

import it.polimi.ingsw.resources.Resource;

public class LeaderOfDepots extends LeaderCard {
    /**
     * resource that can be added to extraDepot
     */
    protected final Resource extraResource;
    protected final Resource[] extraDepot;

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
    }

    /**
     * getter ability of this Leader Card (1 = extraDepot)
     * @return 1
     */

    public int getAbility() {
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
    public void addNewResource(Resource resource) throws IllegalArgumentException{

        if(resource.getResType() != this.extraResource.getResType()) //resource type added is wrong
            throw new IllegalArgumentException("wrong resource");

        if (extraDepot[0] != null && extraDepot[1] != null) //extra depot full
            throw new IllegalArgumentException("already full");


        if (extraDepot[0] == null)
            extraDepot[0] = extraResource;
        else extraDepot[1] = extraResource;

        }

    /**
     * use one element of extraDepot
     */
    public void useResource(Resource resource) throws IllegalArgumentException{

        if(resource.getResType() != this.extraResource.getResType()) //wrong resource to pop
            throw new IllegalArgumentException("wrong resource");

        if (extraDepot[0] == null && extraDepot[1] == null) //extra depot empty
            throw new IllegalArgumentException("already empty");

        if (extraDepot[1] != null)
            extraDepot[1] = null;
        else extraDepot[0] = null;
    }






}
