package it.polimi.ingsw;

import javax.naming.spi.ResolveResult;

public class WarehouseDepot {
    private int[][] depot;

    public WarehouseDepot() {
        depot = new int[3][3];
    }

    public void setTopRow(Resource resource) {
        depot[0][0] = resource.getType();
    }

    //TODO check exceptions if amount>2
    public void setMiddleRow(Resource resource, int amount) {
        depot[1][0] = resource.getType();
        if (amount == 2)
            depot[1][1] = resource.getType();
        else
            depot[1][1] = 0;
    }

    public void setBottomRow(Resource resource, int amount) {
        for (int i = 0; i<amount; i++) {
            depot[2][i] = resource.getType();
        }
        for(int i=0; i<3-amount; i++)
            depot[2][2-i] = 0;
    }

    //TODD getters
}
