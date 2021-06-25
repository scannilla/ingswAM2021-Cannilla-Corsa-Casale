package it.polimi.ingsw.cli;

public class TokenDraw {
      public void drawToken (int action){
            switch (action){
                  case 0:
                      System.out.print(Color.ANSI_GREEN.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_GREEN.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_GREEN.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_GREEN.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_GREEN.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      break;
                  case 1:
                      System.out.print(Color.ANSI_YELLOW.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_YELLOW.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_YELLOW.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_YELLOW.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_YELLOW.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      break;
                  case 2:
                      System.out.print(Color.ANSI_BLUE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_BLUE.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_BLUE.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_BLUE.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_BLUE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      break;
                  case 3:
                      System.out.print(Color.ANSI_PURPLE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_PURPLE.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_PURPLE.escape() + "|  -2  |" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_PURPLE.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_PURPLE.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      break;
                  case 4:
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + "|  +1  |" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      break;
                  case 5:
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + " /    \\" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + "|  +2  |" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + " \\    /" + Color.ANSI_RESET.escape());
                      System.out.print(Color.ANSI_RESET.escape() + "  ----  " + Color.ANSI_RESET.escape());
                      break;
            }

      }
}
