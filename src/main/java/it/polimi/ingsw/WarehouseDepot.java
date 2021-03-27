package it.polimi.ingsw;

//tbh nobody did import this, so nobody is gonna touch it
import javax.naming.spi.ResolveResult;

public class WarehouseDepot {
    private final Resource[][] depot;

    public WarehouseDepot() {
        depot = new Resource[3][];
        depot[0] = new Resource[1];
        depot[1] = new Resource[2];
        depot[2] = new Resource[3];
    }

    public void insertInTopRow(Resource resource) {
        depot[0][0] = resource;
    }

    public void insertInMiddleRow(Resource resource) {
        if(depot[1][0] != null)
            depot[1][1] = resource;
        else depot[1][0] = resource;
    }

    public void insertInBottomRow(Resource resource) {
        int i=0;
        while(depot[2][i]==null && i<3)
            i++;
        depot[2][i] = resource;
    }

    public Resource checkResource(int column) {
        if(depot[column-1][0]!=null)
            return depot[column-1][0];
        else return null;
    }

    public void useResource(int column) {
        int i = column-1;
        while(depot[column-1][i]==null && i>0)
            i--;
        depot[column-1][i] = null;
    }


}
