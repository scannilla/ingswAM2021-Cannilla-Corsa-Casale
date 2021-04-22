package it.polimi.ingsw;

import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.WarehouseDepot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class WarehouseDepotTest {

    @Test
    void getDepotResourceAmount() {
        Resource coinsTest = new Resource(0);
        Resource stonesTest = new Resource(1);
        Resource shieldTest = new Resource(3);

        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        warehouseDepotTest.insertNewResource(coinsTest, 0);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest,2);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest, 2);
        warehouseDepotTest.insertNewResource(stonesTest, 2);

        int[] amountTest = warehouseDepotTest.getDepotResourceAmount();
        assertEquals(1, amountTest[0]);
        assertEquals(3, amountTest[1]);
        assertEquals(2, amountTest[3]);
    }

    @Test
    void useResource() {

        Resource coinsTest = new Resource(0);
        Resource stonesTest = new Resource(1);
        Resource shieldTest = new Resource(3);

        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        warehouseDepotTest.insertNewResource(coinsTest, 0);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest,2);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest, 2);
        warehouseDepotTest.insertNewResource(stonesTest, 2);

        int[] amountTest = warehouseDepotTest.getDepotResourceAmount();
        assertEquals(1, amountTest[0]);
        assertEquals(3, amountTest[1]);
        assertEquals(2, amountTest[3]);

        warehouseDepotTest.useResource(coinsTest);
        warehouseDepotTest.useResource(shieldTest);
        warehouseDepotTest.useResource(stonesTest);
        int[] amountTest1 = warehouseDepotTest.getDepotResourceAmount();
        assertEquals(0, amountTest1[0]);
        assertEquals(1, amountTest1[3]);
        assertEquals(2, amountTest1[1]);

    }

    @Test
    void insertNewResource() { //normal inserting

        Resource coinsTest = new Resource(0);
        Resource stonesTest = new Resource(1);
        Resource shieldTest = new Resource(3);

        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        warehouseDepotTest.insertNewResource(coinsTest, 0);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest,2);
        warehouseDepotTest.insertNewResource(shieldTest, 1);
        warehouseDepotTest.insertNewResource(stonesTest, 2);
        warehouseDepotTest.insertNewResource(stonesTest, 2);


    }

    @Test
    void insertNewResourceException1() { //exception different resource in the same column

        Resource coinsTest = new Resource(0);
        Resource shieldTest = new Resource(3);

        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        try {
            warehouseDepotTest.insertNewResource(coinsTest, 1);
            warehouseDepotTest.insertNewResource(shieldTest, 1);
        } catch (IllegalArgumentException e) {

            String expectedMessage = "Unable to insert the resource";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void insertNewResourceException2() { //exception different resource in the same column

        Resource coinsTest = new Resource(0);
        Resource shieldTest = new Resource(3);

        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        try {
            warehouseDepotTest.insertNewResource(coinsTest, 0);
            warehouseDepotTest.insertNewResource(shieldTest, 0);
        } catch (IllegalArgumentException e) {

            String expectedMessage = "Unable to insert the resource";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void insertNewResourceException3() { //exception full column and different resource

        Resource coinsTest = new Resource(0);
        Resource shieldTest = new Resource(3);

        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        try {
            warehouseDepotTest.insertNewResource(coinsTest, 1);
            warehouseDepotTest.insertNewResource(coinsTest, 1);
            warehouseDepotTest.insertNewResource(shieldTest, 1);
        } catch (IllegalArgumentException e) {

            String expectedMessage = "Unable to insert the resource";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void insertNewResourceException4() { //exception full column (2)

        Resource coinsTest = new Resource(0);


        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        try {
            warehouseDepotTest.insertNewResource(coinsTest, 2);
            warehouseDepotTest.insertNewResource(coinsTest, 2);
            warehouseDepotTest.insertNewResource(coinsTest, 2);
            warehouseDepotTest.insertNewResource(coinsTest, 2);
        } catch (IllegalArgumentException e) {

            String expectedMessage = "Unable to insert the resource";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void insertNewResourceException5() { //exception full column (0)

        Resource coinsTest = new Resource(0);


        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        try {
            warehouseDepotTest.insertNewResource(coinsTest, 0);
            warehouseDepotTest.insertNewResource(coinsTest, 0);
        } catch (IllegalArgumentException e) {

            String expectedMessage = "Unable to insert the resource";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void insertNewResourceException6() { //exception full column (1)

        Resource coinsTest = new Resource(0);


        WarehouseDepot warehouseDepotTest = new WarehouseDepot();

        try {
            warehouseDepotTest.insertNewResource(coinsTest, 1);
            warehouseDepotTest.insertNewResource(coinsTest, 1);
            warehouseDepotTest.insertNewResource(coinsTest, 1);
        } catch (IllegalArgumentException e) {

            String expectedMessage = "Unable to insert the resource";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

}