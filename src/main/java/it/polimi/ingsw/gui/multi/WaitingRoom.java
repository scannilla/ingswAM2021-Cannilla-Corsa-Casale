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



private final JButton setupGame;
private final ClientMessageHandler handler;


    public WaitingRoom(ClientMessageHandler handler){
        setupGame = new JButton("Setup Game");
        setupGame.setBounds(550, 600, 150, 50);
        setupGame.addActionListener(this);
        this.add(setupGame);
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
        if (e.getSource() == setupGame) {
            ObjectMessage confirmation = null;
            do {
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
                if (600 < confirmation.getCode() && confirmation.getCode() < 699) {
                    MainGUI.frame.remove(this);
                    MainGUI.frame.add(new PreGameRes(handler));
                    MainGUI.frame.revalidate();
                    MainGUI.frame.repaint();
                }
            }while(confirmation.getCode() == 402);
        }
        }


}
