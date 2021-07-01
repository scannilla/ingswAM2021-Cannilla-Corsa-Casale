package it.polimi.ingsw.cli;

import it.polimi.ingsw.tokens.ActionToken;

/**
 * this class draws single player tokens
 */
public class TokenDraw {
    /**
     * draws a token
     * @param token
     */
      public static void drawToken (ActionToken token){
            switch (token.getAction()){
                  case 0:
                      System.out.print(Color.ANSI_GREEN.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_GREEN.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_GREEN.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_GREEN.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_GREEN.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      break;
                  case 1:
                      System.out.print(Color.ANSI_YELLOW.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_YELLOW.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_YELLOW.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_YELLOW.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_YELLOW.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      break;
                  case 2:
                      System.out.print(Color.ANSI_BLUE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_BLUE.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_BLUE.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_BLUE.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_BLUE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      break;
                  case 3:
                      System.out.print(Color.ANSI_PURPLE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_PURPLE.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_PURPLE.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_PURPLE.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_PURPLE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      break;
                  case 4:
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + "|  +2  |" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      break;
                  case 5:
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + "|  +1  |" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print("\n");
                      break;
            }

      }
}
