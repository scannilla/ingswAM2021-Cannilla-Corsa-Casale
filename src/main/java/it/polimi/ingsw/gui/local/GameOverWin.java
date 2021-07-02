package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameOverWin extends JPanel implements ActionListener {

private final JButton exit;

    public GameOverWin(){
        exit = new JButton("Exit");
        exit.setBounds(650,650,100,50);
        exit.addActionListener(this);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.white);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == exit){
            MainGUI.changePanel(new Intro("endgame", 0));
        }
    }

    public void paint(Graphics g){
        g.drawString("YOU WIN", 350, 100);
        int i = 1;
        ArrayList<String> leaderBoardsName= new ArrayList<>(Data.instanceCreator().getLeaderBoard().getMap().keySet());
        ArrayList<Integer> wp = new ArrayList<>(Data.instanceCreator().getLeaderBoard().getMap().values());
        for(String name : leaderBoardsName){
            switch(i){
                case 1:
                g.drawString("1st " + name + "total WinPoints " + wp.get(i), 100, 300);
                i++;
                break;
                case 2:
                    g.drawString("2nd" + name + "total WinPoints" + wp.get(i), 100, 350);
                    break;
            }

        }
    }

}
