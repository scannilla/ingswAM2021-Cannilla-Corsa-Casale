package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.VaticanReport;

/**
 * this class draws a personal board (warehouse, strongbox and production card slots)
 */
public class PersonalBoardDraw {

    /**
     * draws the personal board
     * @param pb
     */
    public static void drawPB(PersonalBoard pb, VaticanReport report) {
        WarehouseDepotDraw.drawWarehouseDepot(pb);
        StrongboxDraw.drawStrongbox(pb);
        ProdCardSlotDraw.drawActiveProdCards(pb);
        VaticanBoardDraw.drawVaticanRoad(report, pb);
    }
}
