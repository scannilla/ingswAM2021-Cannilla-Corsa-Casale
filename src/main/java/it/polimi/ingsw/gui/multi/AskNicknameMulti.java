package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.controller.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskNicknameMulti extends JPanel implements ActionListener {

    private final JTextField nickname;
    private final ClientMessageHandler handler;


    public AskNicknameMulti(ClientMessageHandler handler){
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
                handler.sendMessageToServer(nickname.getText(), 171);
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            Message received = null;
            try{
                received = handler.readMessage();
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            if(received.getCode() == 310) {
                Data.instanceCreator().setNickname(received.getNickname());
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Multi(handler));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }

    }
}



