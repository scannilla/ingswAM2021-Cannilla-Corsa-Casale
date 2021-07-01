package it.polimi.ingsw.cli;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.WarehouseDepot;

/**
 * this class draws the warehouse depot
 */
public class WarehouseDepotDraw {

    /**
     * This method draws Warehouse Depot in CLI
     *
     * @param firstLine  Resource[]
     * @param secondLine Resource[]
     * @param thirdLine  Resource[]
     */
    public static void drawWarehouseDepot(Resource[] firstLine, Resource[] secondLine, Resource[] thirdLine) {
        String whiteEscape = Color.ANSI_RESET.escape();
        System.out.print("\n");
        System.out.print(whiteEscape + "WAREHOUSE DEPOT");
        System.out.print("\n");
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "         ^" + whiteEscape);
        System.out.print("\n");

        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "        /" + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + " \\" + whiteEscape);
        System.out.print("\n");

        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "       /" + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + " " + whiteEscape);
        if (firstLine[0] != null)
            draw(firstLine[0]);
        else
            System.out.print(" " + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + " \\" + whiteEscape);
        System.out.print("\n");

        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "      /" + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "-----\\" + whiteEscape);
        System.out.print("\n");

        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "     / " + whiteEscape);
        if (secondLine[0] != null)
            draw(secondLine[0]);
        else
            System.out.print(" " + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "   " + whiteEscape);
        if (secondLine[1] != null)
            draw(secondLine[1]);
        else
            System.out.print(" " + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + " \\" + whiteEscape);
        System.out.print("\n");

        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "    /" + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "--" + "-------\\" + whiteEscape);
        System.out.print("\n");

        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "   / " + whiteEscape);
        if (thirdLine[0] != null)
            draw(thirdLine[0]);
        else
            System.out.print(" " + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "   " + whiteEscape);
        if (thirdLine[1] != null)
            draw(thirdLine[1]);
        else
            System.out.print(" " + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "   " + whiteEscape);
        if (thirdLine[2] != null)
            draw(thirdLine[2]);
        else
            System.out.print(" " + whiteEscape);
        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + " \\" + whiteEscape);
        System.out.print("\n");


        System.out.print(Color.ANSI_BRIGHTWHITE.escape() + "  /_____________\\\n\n" + whiteEscape);
    }

    /**
     * This method draws Warehouse Depot using drawWarehouseDepot(Resource[] firstLine, Resource[] secondLine, Resource[] thirdLine)
     *
     * @param personalBoard PersonalBoard
     */
    public static void drawWarehouseDepot(PersonalBoard personalBoard) {
        WarehouseDepot depot = personalBoard.getWarehouseDepot();
        drawWarehouseDepot(depot.getDepot()[0], depot.getDepot()[1], depot.getDepot()[2]);
    }

    /**
     * This method draws the Resource passed as parameter
     * @param resource Resource
     */
    private static void draw(Resource resource) {
        String whiteEscape = Color.ANSI_RESET.escape();
        switch (resource.toString()) {
            case "coin":
                System.out.print(Color.ANSI_YELLOW.escape() + "C" + whiteEscape);
                break;
            case "stone":
                System.out.print(Color.ANSI_GREY.escape() + "R" + whiteEscape);
                break;
            case "servant":
                System.out.print(Color.ANSI_PURPLE.escape() + "S" + whiteEscape);
                break;
            case "shield":
                System.out.print(Color.ANSI_BLUE.escape() + "H" + whiteEscape);
                break;
        }
    }
}
