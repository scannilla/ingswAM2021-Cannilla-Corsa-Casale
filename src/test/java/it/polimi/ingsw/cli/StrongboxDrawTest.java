package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.Strongbox;
import it.polimi.ingsw.resources.WarehouseDepot;
import it.polimi.ingsw.cli.StrongboxDraw;
import org.junit.jupiter.api.Test;

public class StrongboxDrawTest {
    @Test
    void drawStrongboxTest() {

        Resource coinsTest = new Resource(0);
        Resource stonesTest = new Resource(1);
        Resource shieldTest = new Resource(3);
        PersonalBoard personalBoardTest = new PersonalBoard();
        Strongbox strongboxTest = personalBoardTest.getStrongbox();

        StrongboxDraw.drawStrongbox(personalBoardTest);
    }
}