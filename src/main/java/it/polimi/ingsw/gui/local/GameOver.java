package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel implements ActionListener {

    private int players;
    private final JButton exit;
    private Game game;
        public GameOver(int players, Game game){
            this.players = game.getNumberOfPlayers();
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
        g.drawString("GAME OVER", 350, 50);
        g.drawString("You are this position", 350, 100);
        switch (players){

                case 1:
                    g.drawString("1st nickname - total winpoints", 350, 110);
                    g.drawString("2nd nickname - total winpoints", 350, 120);
                case 2:
                    g.drawString("1st nickname - total winpoints", 350, 110);
                    g.drawString("2nd nickname - total winpoints", 350, 120);
                case 3:
                    g.drawString("1st nickname - total winpoints", 350, 110);
                    g.drawString("2nd nickname - total winpoints", 350, 120);
                    g.drawString("3rd nickname - total winpoints", 350, 130);
                case 4:
                    g.drawString("1st nickname - total winpoints", 350, 110);
                    g.drawString("2nd nickname - total winpoints", 350, 120);
                    g.drawString("3rd nickname - total winpoints", 350, 130);
                    g.drawString("4th nickname - total winpoints", 350, 140);
        }
    }






    @Override
    public void actionPerformed(ActionEvent e) {

            if(e.getSource() == exit){
                MainGUI.frame.remove(this);
                //Main.frame.add(new Intro());
                MainGUI.frame.revalidate();
            }
    }
}
