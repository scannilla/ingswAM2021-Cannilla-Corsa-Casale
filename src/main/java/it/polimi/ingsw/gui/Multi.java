package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;


public class Multi extends JPanel implements ActionListener {

    private final JButton one;
    private final JButton two;
    private final JButton three;
    private final JButton four;
    private final JButton back;
    private final ClientMessageHandler handler;

    public Multi(ClientMessageHandler handler) {
        this.handler = handler;
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        back = new JButton("Go Back");
        back.setBounds(590, 600, 200, 50);
        one.setBounds(150, 120, 50, 50);
        two.setBounds(200, 120, 50, 50);
        three.setBounds(250, 120, 50, 50);
        four.setBounds(300, 120, 50, 50);
        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(back);
        back.addActionListener(this);
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
        Main.frame.revalidate();
        if (e.getSource() == back) {
            Main.frame.remove(this);
            Main.frame.add(new Intro(handler));
            Main.frame.revalidate();
            Main.frame.repaint();
        } else if (e.getSource() == two) {
            try {
                handler.sendMessageToServer("two", 1);
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException ex) {
                //TODO disconnect
            }
            if (received.getMessage().equals("ok")) {
                Main.frame.remove(this);
                Main.frame.add(new WaitingRoom(2, handler));
                Main.frame.revalidate();
                Main.frame.repaint();
            } else if (received.getMessage().equals("ko")){
                String error = received.getMessage();
                Main.frame.add(new Error(error));
            }
        } else if (e.getSource() == three) {
            try {
                handler.sendMessageToServer("three", 1);
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            if (received.getMessage().equals("ok")) {
                Main.frame.remove(this);
                Main.frame.add(new WaitingRoom(3, handler));
                Main.frame.revalidate();
            } else if (received.getMessage().equals("ko")){
                String error = received.getMessage();
                Main.frame.add(new Error(error));
            }
        } else if (e.getSource() == four) {

            try {
                handler.sendMessageToServer("four", 1);
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            if (received.getMessage().equals("ok")) {
                Main.frame.remove(this);
                Main.frame.add(new WaitingRoom(4, handler));
                Main.frame.revalidate();
            } else if (received.getMessage().equals("ko")) {
                String error = received.getMessage();
                Main.frame.add(new Error(error));
            }
        } else if (e.getSource() == one) {
            try {
                handler.sendMessageToServer("one", 1);
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                //TODO disconnect
            }
            if (received.getMessage().equals("ok")) {
                Main.frame.remove(this);
                Main.frame.add(new WaitingRoom(1, handler));
                Main.frame.revalidate();
                Main.frame.repaint();
            }


        }
    }
}
