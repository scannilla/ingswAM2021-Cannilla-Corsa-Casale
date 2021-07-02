package it.polimi.ingsw.gui.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;

public class GameOver extends JPanel implements ActionListener {


    private final JButton exit;
        public GameOver(){
            exit = new JButton("exit");
            exit.setBounds(650, 650, 100, 50);
            exit.addActionListener(this);
            this.add(exit);
            this.setLayout(null);
            this.setVisible(true);
            this.setBackground(Color.white);
            this.setSize(800,800);
        }


    public void paint(Graphics g){
        g.drawString("GAME OVER", 150, 50);
        ArrayList<String> strings = new ArrayList<>(Data.instanceCreator().getLeaderBoard().getMap().keySet());
        ArrayList<Integer> wp = new ArrayList<>(Data.instanceCreator().getLeaderBoard().getMap().values());
        drawLeaderboard(strings, wp, g);
    }




    public static void drawLeaderboard(ArrayList<String> leaderboardString, ArrayList<Integer> leaderBoardInt, Graphics g){
        int i=0;
        for(String p : leaderboardString) {
            if(i==0) {
                g.drawString("First player: " + p + " Points: " + leaderBoardInt.get(i), 200, 100);
            }
            if(i==1) {
                g.drawString("Second player: " + p + " Points: " + leaderBoardInt.get(i), 200, 200);
                System.out.print("\n");
            }
            if(i==2) {
                g.drawString("Third player: " + p + " Points: " + leaderBoardInt.get(i), 200, 300);

            }
            if(i==3) {
                g.drawString("Fourth player: " + p + " Points: " + leaderBoardInt.get(i), 200, 400);
            }
            i++;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

            if(e.getSource() == exit){
               MainGUI.changePanel(new Intro("endgame", 0));
            }
    }
}
