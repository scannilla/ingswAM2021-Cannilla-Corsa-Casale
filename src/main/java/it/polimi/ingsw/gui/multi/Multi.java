package it.polimi.ingsw.gui.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;


public class Multi extends JPanel implements ActionListener {

    private final JButton one;
    private final JButton two;
    private final JButton three;
    private final JButton four;
    private final ClientMessageHandler handler;

    public Multi(ClientMessageHandler handler) {
        this.handler = handler;
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        one.setBounds(150, 120, 50, 50);
        two.setBounds(200, 120, 50, 50);
        three.setBounds(250, 120, 50, 50);
        four.setBounds(300, 120, 50, 50);
        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        g.drawString("Insert number of player (1-4)", 200, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message received = null;
        if (e.getSource() == two) {
            try {
                handler.sendMessageToServer("2", 140 );
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException ex) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            if (received.getMessage().equals("ok")) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new WaitingRoom(handler));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            } else if (received.getMessage().equals("ko")){

            }
        } else if (e.getSource() == three) {
            try {
                handler.sendMessageToServer("3", 140);
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            if (received.getMessage().equals("ok")) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new WaitingRoom(handler));
                MainGUI.frame.revalidate();
            } else if (received.getMessage().equals("ko")){

            }
        } else if (e.getSource() == four) {

            try {
                handler.sendMessageToServer("4", 140);
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            if (received.getMessage().equals("ok")) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new WaitingRoom(handler));
                MainGUI.frame.revalidate();
            } else if (received.getMessage().equals("ko")) {

            }
        } else if (e.getSource() == one) {
            try {
                handler.sendMessageToServer("1", 140);
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            if (received.getMessage().equals("ok")) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new WaitingRoom(handler));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }


        }
    }
}
