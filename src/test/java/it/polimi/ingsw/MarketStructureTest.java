package it.polimi.ingsw;

import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.marbles.MarketStructure;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

class MarketStructureTest {
    File fileTest = new File("src/main/java/it/polimi/ingsw/marbles/marbles.json");
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

        MarketMarble[] marketMarblesTest = marketStructureTest.selectLine(1);
        for (MarketMarble marble : marketMarblesTest) {
            assertNotNull(marble);
            System.out.println(marble.getColor());
        }

    }

    @Test
    void selectColumn() {
    }
}