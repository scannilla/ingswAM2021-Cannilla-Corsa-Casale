package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.VaticanReport;


public class PersonalBoardDraw {

    public static void drawPB(PersonalBoard pb) {
        WarehouseDepotDraw.drawWarehouseDepot(pb);
        StrongboxDraw.drawStrongbox(pb);
        ProdCardSlotDraw.drawActiveProdCards(pb);
        VaticanBoardDraw.drawVaticanRoad(report, pb);
    }
}
