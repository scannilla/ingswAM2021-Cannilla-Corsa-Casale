package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.WarehouseDepot;
import org.junit.jupiter.api.Test;

public class WarehouseDepotDrawTest {

    @Test
    void drawWarehouseDepot(){

        Resource coinsTest = new Resource(0);
        Resource stonesTest = new Resource(1);
        Resource shieldTest = new Resource(3);

        PersonalBoard board = new PersonalBoard();
        WarehouseDepot warehouseDepotTest = board.getWarehouseDepot();

        warehouseDepotTest.insertNewResource(coinsTest, 0);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest,2);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest, 2);
        warehouseDepotTest.insertNewResource(stonesTest, 2);

        WarehouseDepotDraw.drawWarehouseDepot(board);
    }
}
