package it.polimi.ingsw.cli;

import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.marbles.MarketStructure;

public class MarketDraw {
    public static void drawMarket(int market[][], int outMarble){
        String whiteEscape = Color.ANSI_BRIGHTWHITE.escape();
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "-----------------------------------------" + whiteEscape);
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "|         |         |         |         |");
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
        System.out.print(Color.ANSI_CYAN.escape() + "!");
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
        System.out.print(Color.ANSI_CYAN.escape() + "!");
        drawFrecciaOriz();
        System.out.print("\n");
        drawVoidLine();
        System.out.print(Color.ANSI_CYAN.escape() + "-----------------------------------------" + whiteEscape);
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "     ^         ^         ^         ^");
        System.out.print("\n");
        System.out.print(Color.ANSI_CYAN.escape() + "     |         |         |         |");
        System.out.print("\n");
    }

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

    public static void drawBall(int resource){
        String whiteEscape = Color.ANSI_BRIGHTWHITE.escape();
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

    private static void drawVoidLine(){
        System.out.print(Color.ANSI_CYAN.escape() + "|         |         |         |         |");
        System.out.print("\n");
    }

    private static void drawEmptyLine(){
        System.out.print(Color.ANSI_CYAN.escape() + "|    ");
    }

    private static void drawFrecciaOriz(){
        System.out.print(Color.ANSI_CYAN.escape() + " <--");
    }
}
