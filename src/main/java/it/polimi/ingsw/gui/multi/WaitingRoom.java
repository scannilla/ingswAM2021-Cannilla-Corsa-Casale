package it.polimi.ingsw.gui.multi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;

public class WaitingRoom extends JPanel implements ActionListener {



private final int numOfPlayer;
private final ClientMessageHandler handler;


    public WaitingRoom(int numOfPlayer, ClientMessageHandler handler){
        this.numOfPlayer = numOfPlayer;
        this.handler = handler;
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);

    }

    public void paint(Graphics g){
            g.drawString("Waiting for other players", 350, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectMessage confirmation = null;
        try {
            handler.sendMessageToServer("setup game", 150);
        } catch (EndingGameException endingGameException) {
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Intro("error", 1));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
        try {
            confirmation = (ObjectMessage) handler.readMessage();
        } catch (EndingGameException endingGameException) {
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Intro("error", 1));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
        if(600<confirmation.getCode() && confirmation.getCode()<699){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new PreGameRes(handler));
            MainGUI.frame.revalidate();
        }

    }


}
