package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VaticanReportTest {

    File fileTest = new File("src/main/java/it/polimi/ingsw/vatReport.json");





    @Test
    void getReportsLength() {
        int[] arrayTest = {4, 4, 4};
        try{
            VaticanReport vaticanReportTest = GSON.vaticanReportParser(fileTest);
            assertArrayEquals(arrayTest, vaticanReportTest.getReportsLength());
        } catch(IOException e){
            e.printStackTrace();
        }


    }

    @Test
    void getActivationPosition() {

        int[] arrayTest = {8, 16, 24};

        try{
            VaticanReport vaticanReportTest = GSON.vaticanReportParser(fileTest);
            assertArrayEquals(arrayTest, vaticanReportTest.getActivationPosition());
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    @Test
    void getPopeFavourTile() {
        int[] arrayTest = {2, 3, 4};

        try{
            VaticanReport vaticanReportTest = GSON.vaticanReportParser(fileTest);
            assertArrayEquals(arrayTest, vaticanReportTest.getPopeFavourTile());
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    @Test
    void getActivatedEvent() {
    }

    @Test
    void getNumberOfReports() {
    }
}