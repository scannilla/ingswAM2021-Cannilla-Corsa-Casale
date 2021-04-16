package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceCounterTest {

    Resource[] resourcesTest = new Resource[40];
    Resource coinsTest = new Resource(0);
    Resource stonesTest = new Resource(1);
    Resource servantsTest = new Resource(2);
    Resource shieldsTest = new Resource(3);
    int[] counterRes = new int[4];

    @Test
    void resCount() {
        for(int i=0; i<40; i++){
            int k=i%4;
            switch (k) {
                case 0: resourcesTest[i] = coinsTest;
                        break;
                case 1: resourcesTest[i] = stonesTest;
                        break;
                case 2: resourcesTest[i] = servantsTest;
                        break;
                case 3: resourcesTest[i] = shieldsTest;
                        break;
            }


        }

        counterRes = ResourceCounter.resCount(resourcesTest);

        for(int i=0; i<4; i++){
            assertEquals(10, counterRes[i]);
        }

    }

    @Test
    void resCountNull() {
        for(int i=0; i<40; i++){
            int k=i%4;
            switch (k) {
                case 0: resourcesTest[i] = coinsTest;
                    break;
                case 1: resourcesTest[i] = stonesTest;
                    break;
                case 2: resourcesTest[i] = servantsTest;
                    break;
                case 3: resourcesTest[i] = shieldsTest;
                    break;
            }
            resourcesTest[0] = null;
            resourcesTest[1] = null;
            resourcesTest[2] = null;
            resourcesTest[3] = null;

        }

        counterRes = ResourceCounter.resCount(resourcesTest);

        for(int i=0; i<4; i++){
            assertEquals(9, counterRes[i]);
        }

    }

}