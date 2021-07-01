package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.controller.singleplayer.LocalSinglePlayer;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.Intro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Local extends JPanel implements ActionListener {

    private final JButton startGame;
    private final SPClientMessageHandler handler;

    public Local(SPClientMessageHandler handler){
        this.handler = handler;
        startGame = new JButton("Start Game");
        startGame.setBounds(50, 300, 100, 50);
        startGame.addActionListener(this);
        this.add(startGame);

        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);


    }

    public void paint(Graphics g){
        g.drawString("You chose to play local", 100, 50);
        g.drawString("Your enemy is Lorenzo il Magnifico", 100, 100);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGame) {
            MainGUI.changePanel(new AskNickname(handler));
        }
    }
}
