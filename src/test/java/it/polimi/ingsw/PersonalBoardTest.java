package it.polimi.ingsw;

import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.WarehouseDepot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalBoardTest {
    PersonalBoard personalBoardTest = new PersonalBoard();
    WarehouseDepot warehouseDepotTest = new WarehouseDepot();
    Resource resourceTest = new Resource(3);
    Resource[] testsCostArray = {new Resource(0), new Resource(0), new Resource(1), new Resource(3)};
    Resource[] testsRequiredRes = {new Resource(1), new Resource(2), new Resource(2), new Resource(3)};
    Resource[] testsGivenRes = {new Resource(2), new Resource(2), new Resource(2), new Resource(3)};
    ProductionCard testCard = new ProductionCard(1, 1, 3, testsCostArray, testsRequiredRes, testsGivenRes, 3);


    @Test
    void getPosition() {
        personalBoardTest.setPosition(15);
        assertEquals(15, personalBoardTest.getPosition());
    }

    @Test
    void setPositionTest() {
        personalBoardTest.setPosition(2);
        assertEquals(2, personalBoardTest.getPosition());
    }

    @Test
    void getWarehouseDepotTest() {
        int[] test = {0,0,0,1};
        personalBoardTest.getWarehouseDepot().insertNewResource(new Resource(3), 1);
        assertArrayEquals(test, personalBoardTest.getWarehouseDepot().getDepotResourceAmount());
    }

    @Test
    void getStrongbox() {
        personalBoardTest.getStrongbox().insertNewResource(resourceTest);
        assertEquals(1, personalBoardTest.getStrongbox().getShields());
    }

    @Test
    void getProdCardSlot() {
        personalBoardTest.getProdCardSlot().insertNewCard(testCard, 1);
        assertEquals(1, personalBoardTest.getProdCardSlot().getCard(0, 1).getLevel());
    }
}