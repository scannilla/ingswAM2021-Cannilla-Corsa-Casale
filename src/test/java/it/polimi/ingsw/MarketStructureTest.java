package it.polimi.ingsw;

import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.marbles.MarketStructure;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class MarketStructureTest {


    InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/marbles.json");
    MarketStructure marketStructureTest;
    @Test
    void initializeMarket() {

        try {
            marketStructureTest = GSON.marketStructureParser(fileTest);
            marketStructureTest.initializeMarket();
            assertNotNull(marketStructureTest);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    @Test
    void selectLine() {


        try {
            marketStructureTest = GSON.marketStructureParser(fileTest);
            marketStructureTest.initializeMarket();
            assertNotNull(marketStructureTest);
        } catch (IOException e){
            e.printStackTrace();
        }

        MarketMarble[] marketMarblesTest1 = marketStructureTest.selectLine(1);
        MarketMarble[] marketMarblesTest2 = marketStructureTest.selectLine(0);
        MarketMarble[] marketMarblesTest3 = marketStructureTest.selectLine(2);

        for (MarketMarble marble : marketMarblesTest1) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }
        for (MarketMarble marble : marketMarblesTest2) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }
        for (MarketMarble marble : marketMarblesTest3) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }
    }

    @Test
    void selectColumn() {

        try {
            marketStructureTest = GSON.marketStructureParser(fileTest);
            marketStructureTest.initializeMarket();
            assertNotNull(marketStructureTest);
        } catch (IOException e){
            e.printStackTrace();
        }

        MarketMarble[] marketMarblesTest1 = marketStructureTest.selectColumn(0);
        MarketMarble[] marketMarblesTest2 = marketStructureTest.selectColumn(1);
        MarketMarble[] marketMarblesTest3 = marketStructureTest.selectColumn(2);
        MarketMarble[] marketMarblesTest4 = marketStructureTest.selectColumn(3);

        assertEquals(3, marketMarblesTest1.length);

        for (MarketMarble marble : marketMarblesTest1) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }
        for (MarketMarble marble : marketMarblesTest2) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }
        for (MarketMarble marble : marketMarblesTest3) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }
        for (MarketMarble marble : marketMarblesTest4) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }



    }

    }
