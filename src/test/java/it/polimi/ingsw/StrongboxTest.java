package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrongboxTest {

Strongbox strongboxTest = new Strongbox();


    @Test
    void getCoins() {
        strongboxTest.setCoins(2);
        assertEquals(2, strongboxTest.getCoins());
    }

    @Test
    void getStones() {
        strongboxTest.setStones(0);
        assertEquals(0, strongboxTest.getStones());
    }

    @Test
    void getServants() {
        strongboxTest.setServants(0);
        assertEquals(0, strongboxTest.getServants());
    }

    @Test
    void getShields() {
        strongboxTest.setShields(0);
        assertEquals(0, strongboxTest.getShields());
    }

    @Test
    void setCoins() {
        strongboxTest.setCoins(4);
        assertEquals(4, strongboxTest.getCoins());
    }

    @Test
    void setStones() {
        strongboxTest.setStones(4);
        assertEquals(4, strongboxTest.getStones());
    }

    @Test
    void setServants() {
        strongboxTest.setServants(4);
        assertEquals(4, strongboxTest.getServants());
    }

    @Test
    void setShields() {
        strongboxTest.setShields(4);
        assertEquals(4, strongboxTest.getShields());

    }

    @Test
    void isEnough() {
        strongboxTest.setShields(4);
        Resource resourceTest = new Resource(3);
        strongboxTest.isEnough(resourceTest, 4);
        assertEquals(0, strongboxTest.isEnough(resourceTest, 4));


    }

    @Test
    void getStrongboxResourcesAmount(){
            strongboxTest.setCoins(5);
            strongboxTest.setStones(3);
            strongboxTest.setServants(6);
            strongboxTest.setShields(10);

            int[] amountTest;

            amountTest = strongboxTest.getStrongboxResourcesAmount();

            assertEquals(5, amountTest[0]);
        assertEquals(3, amountTest[1]);
        assertEquals(6, amountTest[2]);
        assertEquals(10, amountTest[3]);


    }

}