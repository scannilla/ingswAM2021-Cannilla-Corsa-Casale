package it.polimi.ingsw;

import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.tokens.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.File;



class GSONTest {




    @Test
    void productionCardParserTest(){

        File fileTest = new File("src/main/java/it/polimi/ingsw/production/prodcards.json");

        try{
            ProductionCardsDeck productionCardsDeckTest = GSON.productionCardParser(fileTest);

            for(int i=0; i<4; i++) {
                assertEquals(3, productionCardsDeckTest.getProductionCardsDeck()[47].getCostArray()[i].getResType());
                assertEquals(2, productionCardsDeckTest.getProductionCardsDeck()[47].getCostArray()[i+4].getResType());
            }
            for(int i=0; i<48; i++) {
                assertNotNull(productionCardsDeckTest.getProductionCardsDeck()[i]);
                assertNotEquals(0, productionCardsDeckTest.getProductionCardsDeck()[i].getLevel());
                assertNotEquals(0, productionCardsDeckTest.getProductionCardsDeck()[i].getType());

            }
        } catch (IOException e){
            e.printStackTrace();
        }



    }



    @Test
    void leaderCardParser() {
    }

    @Test
    void actionTokensPileParser() {
    }

    @Test
    void marketStructureParser() {
    }
}