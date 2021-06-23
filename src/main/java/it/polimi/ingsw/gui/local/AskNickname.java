package it.polimi.ingsw.gui.local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.singleplayer.LocalSinglePlayer;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;

public class AskNickname extends JPanel implements ActionListener {

    private final JTextField nickname;
    private final SPClientMessageHandler handler;


    public AskNickname(SPClientMessageHandler handler){
        this.handler = handler;
        nickname = new JTextField("Insert nickname here");
        nickname.setBounds(300, 300, 200, 50);
        nickname.setEditable(true);
        nickname.addActionListener(this);
        this.add(nickname);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g){
        g.drawString("Insert a nickname (max 16 chars)", 300, 100);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == nickname){
            try {
                handler.sendMessageToServer(nickname.getText());
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new PreGameLeaderCard(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();

        }

    }
}



