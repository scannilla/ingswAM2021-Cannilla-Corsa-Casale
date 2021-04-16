package it.polimi.ingsw;

//tbh nobody did import this, so nobody is gonna touch it

public class WarehouseDepot {
    /**
     * This attribute represents the depot with all its resources
     * depot[0] = first row of depot (1 resource)
     * depot[1] = second row of depot (2 resources)
     * depot[2] = third row of depot (3 resources)
     */
    private final Resource[][] depot;


    /**
     * This method creates a new Warehouse Depot initialized to null
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
     * @param column int
     */
    public void insertNewResource(Resource resource, int column) throws IllegalArgumentException {

        String message = "Unable to insert the resource.";

        for(int i=0; i<3; i++)
            if (i!=column)
                if(checkResource(i)==resource)
                    throw new IllegalArgumentException(message);

        if (depot[column][0] != null && depot[column][0] != resource){
            throw new IllegalArgumentException(message);
        }

        if (column == 0){
            if (depot[0][0] != null){
                throw new IllegalArgumentException(message);
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
                throw new IllegalArgumentException(message);
            }
        }

        if (column == 2) {
            if(depot[2][2]!=null)
                throw new IllegalArgumentException(message);
            int i=0;
            while(depot[2][i]!=null && i<3)
                i++;
            depot[2][i]=resource;
        }


    }

    /**
     * return the resource that is in this column
     * @param column int
     * @return depot[column][0]
     */
    private Resource checkResource(int column) {
        if(depot[column][0]!=null)
            return depot[column][0];
        else return null;
    }

    /**
     * modify Warehouse Depot to optimize slots
     * @param column int
     * @return changed
     * @throws IllegalArgumentException IllegalArgumentException
     */
    private int optimizeDepot(int column) throws IllegalArgumentException{
        Resource temp;
        int changed;
        String s = "Optimization Impossible";
        if(column != 0 && column != 1)
            throw new IllegalArgumentException(s);
        if (column==0) {
            if(depot[1][1]==null) {
                temp = depot[0][0];
                depot[0][0] = depot[1][0];
                depot[1][0] = temp;
                changed=1;
            }
            else if(depot[2][1]==null) {
                temp = depot[0][0];
                depot[0][0] = depot[2][0];
                depot[2][0] = temp;
                changed=2;
            }
            else throw new IllegalArgumentException(s);
        }
        else {
            if(depot[2][2]==null) {
                temp = depot[1][0];
                depot[1][0] = depot [2][0];
                if(depot[2][1]!=null)
                    depot [1][1] = depot [2][0];
                depot[2][0] = temp;
                depot[2][1] = temp;
                changed=2;
            }
            else throw new IllegalArgumentException(s);
        }
        return changed;
    }

    /**
     * use a resource from depot
     * @param resource Resource
     * @throws IllegalArgumentException IllegalArgumentException
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
                depot [1][0] = depot [1][1];
                depot [1][1] = null;
            }
            if (column==2) {
                for(int j=0; i<2; i++) {
                    if(depot[2][j]!=null) {
                        depot[2][j+1] = depot[2][i];
                        depot[2][j] = null;
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


}
