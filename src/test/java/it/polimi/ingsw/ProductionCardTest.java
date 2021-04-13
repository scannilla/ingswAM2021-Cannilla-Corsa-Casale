package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionCardTest {
    Resource[] testsCostArray = {new Resource(0), new Resource(0), new Resource(1), new Resource(3)};
    Resource[] testsRequiredRes = {new Resource(1), new Resource(2), new Resource(2), new Resource(3)};
    Resource[] testsGivenRes = {new Resource(2), new Resource(2), new Resource(2), new Resource(3)};
    ProductionCard productionCardTest = new ProductionCard(1, 1, 3, testsCostArray, testsRequiredRes, testsGivenRes, 3);

    @Test
    void getType() {
        assertEquals(1, productionCardTest.getType());
    }

    @Test
    void getLevel() {
        assertEquals(1, productionCardTest.getLevel());
    }

    @Test
    void getWp() {
        assertEquals(3, productionCardTest.getWp());
    }

    @Test
    void getCostArray() {
        Resource[] testCostArray;
        testCostArray = productionCardTest.getCostArray();
        assertEquals(1, testCostArray[2].getResType());
        assertEquals(0, testCostArray[1].getResType());
        }

    @Test
    void getRequiredRes() {
        Resource[] testRequiredRes;
        testRequiredRes = productionCardTest.getRequiredRes();
        assertEquals(1, testRequiredRes[0].getResType());
        assertEquals(3, testRequiredRes[3].getResType());
    }

    @Test
    void getGivenRes() {
        Resource[] testGivenRes;
        testGivenRes = productionCardTest.getGivenRes();
        assertEquals(2, testGivenRes[2].getResType());
        assertEquals(3, testGivenRes[3].getResType());
    }
}