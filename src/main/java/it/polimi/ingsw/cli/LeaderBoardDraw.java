package it.polimi.ingsw.cli;

import it.polimi.ingsw.Player;

import java.util.ArrayList;

public class LeaderBoardDraw {
    public static void drawLeaderboard(ArrayList<String> leaderboardString, ArrayList<Integer> leaderBoardInt){
        int i=0;
        for(String p : leaderboardString) {
            if(i==0) {
                System.out.print("First player: " + p + " Points: " + leaderBoardInt.get(i));
                System.out.print("\n");
            }
            if(i==1) {
                System.out.print("Second player: " + p + " Points: " + leaderBoardInt.get(i));
                System.out.print("\n");
            }
            if(i==2) {
                System.out.print("Third player: " + p + " Points: " + leaderBoardInt.get(i));
                System.out.print("\n");
            }
            if(i==3) {
                System.out.print("Fourth player: " + p + " Points: " + leaderBoardInt.get(i));
                System.out.print("\n");
            }
            i++;
        }
    }
    public static void drawWin (ArrayList<Integer> leaderBoardInt){
        System.out.print("You win! Total Points: " + leaderBoardInt.get(0));
        System.out.print("\n");
    }
    public static void drawLose (ArrayList<Integer> leaderBoardInt){
        System.out.print("You lose! Total Points: " + leaderBoardInt.get(1));
        System.out.print("\n");
    }
}
