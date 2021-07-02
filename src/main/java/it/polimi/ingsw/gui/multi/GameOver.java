package it.polimi.ingsw.gui.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        int i = 1;
       for (Player player : Data.instanceCreator().getLeaderBoard()){
            switch (i) {
                case 1:
                    g.drawString("1st" + player.getNickname() + "total WinPoints" + player.getWp(), 200, 100);
                    i++;
                    break;
                case 2:
                    g.drawString("2nd " + player.getNickname() + "total WinPoints " + player.getWp(), 200, 150);
                    i++;
                    break;
                case 3:
                    g.drawString("3rd " + player.getNickname() + "total WinPoints " + player.getWp(), 200, 200);
                    i++;
                    break;
                case 4:
                    g.drawString("4rd " + player.getNickname() + "total WinPoints " + player.getWp(), 200, 250);
                    i++;
                    break;
            }
        }
    }







    @Override
    public void actionPerformed(ActionEvent e) {

            if(e.getSource() == exit){
               MainGUI.changePanel(new Intro("endgame", 0));
            }
    }
}
