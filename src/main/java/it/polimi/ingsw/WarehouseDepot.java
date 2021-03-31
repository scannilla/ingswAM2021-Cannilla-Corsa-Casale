package it.polimi.ingsw;

//tbh nobody did import this, so nobody is gonna touch it
import javax.naming.spi.ResolveResult;
import javax.print.attribute.standard.RequestingUserName;

public class WarehouseDepot {
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
    public void insertNewResource(Resource resource) throws IllegalArgumentException {
        int column=-1;
        int optimized;
        String s = "Unable to insert new resource";
        for(int i=2; i>=0; i--) {
            try {checkResource(i);}
            catch (NullPointerException e)
            {
                depot[i][0] = resource;
                column=3;
            }
            finally {
                if(resource==checkResource(i))
                    column = i;
            }
        }
        if(column==-1)
            throw new IllegalArgumentException(s);
        if (column==0) {
            try {
                optimized = optimizeDepot(column);
            }
            catch (Exception e) {
                throw new IllegalArgumentException(s);
            }
            if(optimized==1) {
                depot[1][1] = resource;
            }
            else {
                depot[2][1] = resource;
            }
        }
        else if(column==1) {
            if(depot[1][1]==null)
                depot[1][1] = resource;
            else {
                try {optimizeDepot(column); }
                catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(s);
                }
                depot[2][2]=resource;
            }
        }
    }

    /**
     * return the resource that is in this column
     * @param column int
     * @return depit[column][0]
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


}
