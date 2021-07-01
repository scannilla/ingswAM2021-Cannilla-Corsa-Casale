package it.polimi.ingsw.cli;

import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.marbles.MarketStructure;

/**
 * this class draws the market
 */
public class MarketDraw {
    /**
     * draws the market
     * @param market
     * @param outMarble
     */
    public static void drawMarket(int market[][], int outMarble){
        String whiteEscape = Color.ANSI_RESET.escape();
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "-----------------------------------------" + whiteEscape);
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "|         |         |         |         |" + Color.ANSI_RESET.escape());
        System.out.print(whiteEscape + "     OUT: " + whiteEscape);
        drawBall(outMarble);
        System.out.print("\n");
        drawEmptyLine();
        drawBall(market[0][0]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[1][0]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[2][0]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[3][0]);
        System.out.print("    ");
        System.out.print(Color.ANSI_CYAN.escape() + "|");
        drawFrecciaOriz();
        System.out.print("\n");
        drawVoidLine();
        System.out.print(Color.ANSI_CYAN.escape() + "-----------------------------------------" + whiteEscape);
        System.out.print("\n");
        drawVoidLine();
        drawEmptyLine();
        drawBall(market[0][1]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[1][1]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[2][1]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[3][1]);
        System.out.print("    ");
        System.out.print(Color.ANSI_CYAN.escape() + "|");
        drawFrecciaOriz();
        System.out.print("\n");
        drawVoidLine();
        System.out.print(Color.ANSI_CYAN.escape() + "-----------------------------------------" + whiteEscape);
        System.out.print("\n");
        drawVoidLine();
        drawEmptyLine();
        drawBall(market[0][2]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[1][2]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[2][2]);
        System.out.print("    ");
        drawEmptyLine();
        drawBall(market[3][2]);
        System.out.print("    ");
        System.out.print(Color.ANSI_CYAN.escape() + "|" + Color.ANSI_RESET.escape());
        drawFrecciaOriz();
        System.out.print("\n");
        drawVoidLine();
        System.out.print(Color.ANSI_CYAN.escape() + "-----------------------------------------" + whiteEscape);
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "     ^         ^         ^         ^" + Color.ANSI_RESET.escape());
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "     |         |         |         |" + Color.ANSI_RESET.escape());
        System.out.print("\n");
    }

    /**
     * used to draw the market
     * @param structure
     */
    public static void draw(MarketStructure structure){
        MarketMarble[][] marbles = structure.getMarketStructure();
        int[][] res = new int [4][3];
        for (int i = 0; i < 4; i++) {
            int j=0;
            for(MarketMarble m : marbles[i]) {
                res[i][j] = m.getColor();
                j++;
            }
        }
        drawMarket(res, structure.getOutMarble().getColor());
    }

    /**
     * draws a ball
     * @param resource
     */
    public static void drawBall(int resource){
        String whiteEscape = Color.ANSI_RESET.escape();
        switch (resource){
            case 0: System.out.print(Color.ANSI_RESET.escape() + "O" + whiteEscape);
                break;
            case 1: System.out.print(Color.ANSI_GREY.escape() + "O" + whiteEscape);
                break;
            case 2: System.out.print(Color.ANSI_BLUE.escape() + "O" + whiteEscape);
                break;
            case 3: System.out.print(Color.ANSI_YELLOW.escape() + "O" + whiteEscape);
                break;
            case 4: System.out.print(Color.ANSI_PURPLE.escape() + "O" + whiteEscape);
                break;
            case 5: System.out.print(Color.ANSI_RED.escape() + "O" + whiteEscape);
                break;
        }

    }

    /**
     * draws a void line
     */
    private static void drawVoidLine(){
        System.out.print(Color.ANSI_CYAN.escape() + "|         |         |         |         |" + Color.ANSI_RESET.escape());
        System.out.print("\n");
    }

    /**
     * draws an empty line
     */
    private static void drawEmptyLine(){
        System.out.print(Color.ANSI_CYAN.escape() + "|    " + Color.ANSI_RESET.escape());
    }

    /**
     * draws an horizontal arrow
     */
    private static void drawFrecciaOriz(){
        System.out.print(Color.ANSI_CYAN.escape() + " <--" + Color.ANSI_RESET.escape());
    }
}
