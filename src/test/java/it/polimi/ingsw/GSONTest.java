package it.polimi.ingsw;

import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.tokens.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;


class GSONTest {




    @Test
    void productionCardParserTest(){
        InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/prodcards.json");

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
    void leaderCardParserTest() {
        InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/allLeaderCards.json");

        try {
            int i=0;
            LeaderCardsDeck leaderCardsDeckTest = GSON.leaderCardParser(fileTest);

            for (LeaderCard leaderCardTest : leaderCardsDeckTest.getLeaderCardsDeck()){
                assertNotNull(leaderCardTest);
            }
            for (LeaderCard leaderCard : leaderCardsDeckTest.getLeaderCardsDeck()) {
                if (i == 3) {
                    assertEquals(5, leaderCard.getWp());
                }
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @Test
    void actionTokensPileParserTest() {
        InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/actiontokens.json");

        try {
            ActionTokenPile actionTokenPileTest = GSON.actionTokensPileParser(fileTest);
            actionTokenPileTest.createPile();
            for(int i=0; i<20; i++) {
                assertNotNull(actionTokenPileTest.popToken());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void marketStructureParserTest() {

        InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/marbles.json");

        try{
            MarketStructure marketStructureTest = GSON.marketStructureParser(fileTest);
            assertEquals(0, marketStructureTest.getAllMarbles()[0].getColor());
            assertEquals(0, marketStructureTest.getAllMarbles()[1].getColor());
            assertEquals(0, marketStructureTest.getAllMarbles()[2].getColor());
            assertEquals(0, marketStructureTest.getAllMarbles()[3].getColor());
            assertEquals(1, marketStructureTest.getAllMarbles()[4].getColor());
            assertEquals(1, marketStructureTest.getAllMarbles()[5].getColor());
            assertEquals(2, marketStructureTest.getAllMarbles()[6].getColor());
            assertEquals(2, marketStructureTest.getAllMarbles()[7].getColor());
            assertEquals(3, marketStructureTest.getAllMarbles()[8].getColor());
            assertEquals(3, marketStructureTest.getAllMarbles()[9].getColor());
            assertEquals(4, marketStructureTest.getAllMarbles()[10].getColor());
            assertEquals(4, marketStructureTest.getAllMarbles()[11].getColor());
            assertEquals(5, marketStructureTest.getAllMarbles()[12].getColor());


        } catch (IOException e){
            e.printStackTrace();
        }

    }


    @Test
    void vaticanReportParserTest(){

        InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/vatReport.json");


        try{
            VaticanReport vaticanReportTest = GSON.vaticanReportParser(fileTest);
            assertEquals(4, vaticanReportTest.getReportsLength()[0]);
            assertEquals(5, vaticanReportTest.getReportsLength()[1]);
            assertEquals(6, vaticanReportTest.getReportsLength()[2]);
            assertEquals(8, vaticanReportTest.getActivationPosition()[0]);
            assertEquals(16, vaticanReportTest.getActivationPosition()[1]);
            assertEquals(24, vaticanReportTest.getActivationPosition()[2]);
            assertEquals(2, vaticanReportTest.getPopeFavourTile()[0]);
            assertEquals(3, vaticanReportTest.getPopeFavourTile()[1]);
            assertEquals(4, vaticanReportTest.getPopeFavourTile()[2]);

        } catch (IOException e){
            e.printStackTrace();
        }


    }

}