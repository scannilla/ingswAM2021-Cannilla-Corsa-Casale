package it.polimi.ingsw.cli;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.resources.WarehouseDepot;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MarketDrawTest {

    @Test
    void drawMarket() {


    }

    @Test
    void draw() {
        File fileTest = new File("src/main/java/it/polimi/ingsw/marbles/marbles.json");
        MarketStructure marketStructureTest;
        try{
            marketStructureTest = GSON.marketStructureParser(fileTest);
            for(int times=0; times<4; times++) {
                marketStructureTest.initializeMarket();
                MarketDraw.draw(marketStructureTest);
            }
        } catch (IOException e){
            return;
        }

    }

    @Test
    void drawBall() {
    }
}