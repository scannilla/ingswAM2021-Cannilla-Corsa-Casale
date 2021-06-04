package it.polimi.ingsw.resources;

import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;

import java.io.Serializable;

public class WarehouseDepot implements Serializable {
    /**
     * This attribute represents the depot with all its resources
     * depot[0] = first row of depot (1 resource)
     * depot[1] = second row of depot (2 resources)
     * depot[2] = third row of depot (3 resources)
     */
    private final Resource[][] depot;
    /**
     * This method creates a new Warehouse Depot initialized to 0
     */
    public WarehouseDepot() {
        depot = new Resource[3][];
        depot[0] = new Resource[1];
        depot[1] = new Resource[2];
        depot[2] = new Resource[3];
    }

    /**
     * insert a new resource in the WarehouseDepot
     * @param resource Resource
     */
    public void insertNewResource(Resource resource, int column) throws IllegalArgumentException {

        String outOfBounds = "Unable to insert the resource.";
        String full = "This line is already full";
        String wrong = "This line already has a different resource";

        for(int i=0; i<3; i++)
            if (i!=column)
                if(checkResource(i)==resource)
                    throw new IllegalArgumentException(outOfBounds);

        if (depot[column][0] != null && depot[column][0] != resource){
            throw new IllegalArgumentException(wrong);
        }

        if (column == 0){
            if (depot[0][0] != null){
                throw new IllegalArgumentException(full);
            } else {
                depot[0][0] = resource;
            }
        }

        if (column == 1){
            if (depot[1][0] == resource && depot[1][1] == null){
                depot[1][1] = resource;
            } else if (depot[1][0] == null){
                depot[1][0] = resource;
            } else {
                throw new IllegalArgumentException(full);
            }
        }

        if (column == 2) {
            if(depot[2][2]!=null)
                throw new IllegalArgumentException(full);
            int i=0;
            while(depot[2][i]!=null && i<3)
                i++;
            depot[2][i]=resource;
        }


    }

    /**
     * return the resource that is in this column
     * @param column int
     * @return depit[column][0]
     */
    public Resource checkResource(int column) {
        if(depot[column][0]!=null)
            return depot[column][0];
        else return null;
    }

    /**
     * modify Warehouse Depot to optimize slots
     * @param column int
     * @return changed
     * @throws IllegalArgumentException
     */

    /**
     * use a resource from depot
     * @param resource Resource
     * @throws IllegalArgumentException e
     */
    public void useResource(Resource resource) throws IllegalArgumentException{
        int column=-1;
        int i;
        for(i=0; i<3; i++) {
            if (resource == checkResource(i))
                column = i;
        }
        if(column==-1)
            throw new IllegalArgumentException();
        i=column;
        while(depot[column][i]==null) {
            i--;
        }
        depot[column][i] = null;
        if (i==0) {
            if (column==1) {
                depot [1][0] = depot [0][0];
                depot [0][0] = null;
            }
            if (column==2) {
                for(int j=0; i<2; i++) {
                    if(depot[1][j]!=null) {
                        depot[2][j] = depot[1][i];
                        depot[1][j] = null;
                    }
                }
            }
        }

    }

    /**
     * return an array with all amount of al Resource in the Warehouse Depot
     * @return ResourceCounter.resCount(allResources) <-- this is an array
     */
    public int[] getDepotResourceAmount() {
        Resource[] allResources = new Resource[6];

        System.arraycopy(depot[0], 0, allResources, 0, 1);
        System.arraycopy(depot[1], 0, allResources, 1, 2);
        System.arraycopy(depot[2], 0, allResources, 3, 3);

        return ResourceCounter.resCount(allResources);

    }

    /**
     * moves resource from line1 to line2
     * @param line1 int
     * @param line2 int
     * @throws IllegalArgumentException e
     */
    public void moveResources(int line1, int line2) throws IllegalArgumentException {

        Resource[] swapResource = new Resource[3];
        int[] resourceAmount = getDepotResourceAmount();
        int typeResourceLine1 = depot[line1][0].getResType();
        int typeResourceLine2 = depot[line2][0].getResType();


        if (line1>line2) {
            if (resourceAmount[typeResourceLine1] > resourceAmount[typeResourceLine2])
                throw new IllegalArgumentException("Unable to move resource");
            for (int i=0; i<line2; i++) {
                swapResource[i] = depot[line1][i];
                depot[line1][i] = depot[line2][i];
                depot[line2][i] = swapResource[i];
            }
        } else if (line1<line2){
            if(resourceAmount[typeResourceLine1]<resourceAmount[typeResourceLine2]){
                throw new IllegalArgumentException("Unable to move resource");
            }
            for (int i=0; i<line1; i++){
                swapResource[i] = depot[line1][i];
                depot[line1][i] = depot[line2][i];
                depot[line2][i] = swapResource[i];
            }
        }
    }

    /**
     * this method tells if the amount of resources is enough
     * if return<0 you haven't enough amount of resource
     * if =0 you have exactly the amount of resource
     * if >0 you have more resource of the amount
     * @return values[i]-amount int
     */
    public int isEnoughWarehouse(Resource resource, int amount){

        int[] values = getDepotResourceAmount();
        int i = resource.getResType();

        return (values[i] - amount);
    }

    public Resource[][] getDepot() {
        return depot;
    }
}
