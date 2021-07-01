package it.polimi.ingsw.cli;


import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.resources.Resource;

/**
 * this class draws the production cards market
 */
public class ProdCardsMarketDraw {
    /**
     * draws the production cards market
     * @param prodCards
     */
    public static void drawProdCardsMarket(ProductionCardsMarket prodCards){
        ProductionCard[][] prodCardTensor= prodCards.getTopCards();
        String resetEscape = Color.ANSI_RESET.escape();
        System.out.print("\n");
        System.out.print(resetEscape + "-------------------------------------------------------------------");
        System.out.print("\n");
        for (int i=0; i<4; i++) {
            if (prodCardTensor[0][i]!=null) {
                System.out.print(resetEscape + "|Level: " + prodCardTensor[0][i].getLevel() + "      WP: " + prodCardTensor[0][i].getWp());
                if (prodCardTensor[0][i].getWp()>9)
                    System.out.print(" ");
                else System.out.print("  ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[1][i]!=null) {
                System.out.print(resetEscape + "|Level: " + prodCardTensor[1][i].getLevel() + "      WP: " + prodCardTensor[1][i].getWp());
                if (prodCardTensor[1][i].getWp() > 9)
                    System.out.print(" ");
                else System.out.print("  ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[2][i]!=null) {
            System.out.print(resetEscape + "|Level: " + prodCardTensor[2][i].getLevel() + "      WP: " + prodCardTensor[2][i].getWp());
            if (prodCardTensor[2][i].getWp()>9)
                System.out.print(" |");
            else System.out.print("  |");}
            else System.out.print(resetEscape + "|                     |");
            System.out.print("\n");

            if (prodCardTensor[0][i]!=null) {
                System.out.print(resetEscape + "|Type: " + prodCardTensor[0][i].getType() + "              ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[1][i]!=null) {
                System.out.print(resetEscape + "|Type: " + prodCardTensor[1][i].getType() + "              ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[2][i]!=null) {
                System.out.print(resetEscape + "|Type: " + prodCardTensor[2][i].getType() + "              |");
            }
            else System.out.print(resetEscape + "|                     |");
            System.out.print("\n");

            if (prodCardTensor[0][i]!=null) {
                System.out.print(resetEscape + "|Cost: ");
                drawResCounter(prodCardTensor[0][i].getCostArray());
                System.out.print("   ");
            }
            else System.out.print(resetEscape + "|     EMPTY SLOT      ");
            if (prodCardTensor[1][i]!=null) {
                System.out.print(resetEscape + "|Cost: ");
                drawResCounter(prodCardTensor[1][i].getCostArray());
                System.out.print("   ");
            }
            else System.out.print(resetEscape + "|     EMPTY SLOT      ");
            if (prodCardTensor[2][i]!=null) {
                System.out.print(resetEscape + "|Cost: ");
                drawResCounter(prodCardTensor[2][i].getCostArray());
                System.out.print("   |");
            }
            else System.out.print(resetEscape + "|     EMPTY SLOT      |");
            System.out.print("\n");

            if (prodCardTensor[0][i]!=null) {
                System.out.print(resetEscape + "|Req Res: ");
                drawResCounter(prodCardTensor[0][i].getRequiredRes());
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[1][i]!=null) {
                System.out.print(resetEscape + "|Req Res: ");
                drawResCounter(prodCardTensor[1][i].getRequiredRes());
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[2][i]!=null) {
                System.out.print(resetEscape + "|Req Res: ");
                drawResCounter(prodCardTensor[2][i].getRequiredRes());
                System.out.print(resetEscape + "|");
            }
            else System.out.print(resetEscape + "|                     |");
            System.out.print("\n");

            if (prodCardTensor[0][i]!=null) {
                System.out.print(resetEscape + "|Given: ");
                drawResCounter(prodCardTensor[0][i].getGivenRes());
                System.out.print("  ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[1][i]!=null) {
                System.out.print(resetEscape + "|Given: ");
                drawResCounter(prodCardTensor[1][i].getGivenRes());
                System.out.print("  ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[2][i]!=null) {
                System.out.print(resetEscape + "|Given: ");
                drawResCounter(prodCardTensor[2][i].getGivenRes());
                System.out.print("  |");
            }
            else System.out.print(resetEscape + "|                     ");
            System.out.print("\n");

            if (prodCardTensor[0][i]!=null) {
                System.out.print(resetEscape + "|Given FP: " + prodCardTensor[0][i].getGivenFaithPoints() + "          ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[1][i]!=null) {
                System.out.print(resetEscape + "|Given FP: " + prodCardTensor[1][i].getGivenFaithPoints() + "          ");
            }
            else System.out.print(resetEscape + "|                     ");
            if (prodCardTensor[2][i]!=null) {
                System.out.print(resetEscape + "|Given FP: " + prodCardTensor[2][i].getGivenFaithPoints() + "          |");
            }
            else System.out.print(resetEscape + "|                     ");
            System.out.print("\n");
            System.out.print(resetEscape + "-------------------------------------------------------------------");
            System.out.print("\n");
        }
    }


    /**
     * counts resources and draws them
     * @param resourceArray
     */
    private static void drawResCounter(Resource[] resourceArray) {
        String resetEscape = Color.ANSI_RESET.escape();
        int coins=0;
        int stones=0;
        int servants=0;
        int shields=0;
        for (Resource resource : resourceArray) {
            switch (resource.toString()) {
                case "coin":
                    coins++;
                    break;
                case "stone":
                    stones++;
                    break;
                case "servant":
                    servants++;
                    break;
                case "shield":
                    shields++;
                    break;
            }
        }
        System.out.print(Color.ANSI_YELLOW.escape() + coins + "C " + resetEscape);
        System.out.print(Color.ANSI_GREY.escape() + stones + "R " + resetEscape);
        System.out.print(Color.ANSI_PURPLE.escape() + servants + "S " + resetEscape);
        System.out.print(Color.ANSI_BLUE.escape() + shields + "H " + resetEscape);
    }
}
