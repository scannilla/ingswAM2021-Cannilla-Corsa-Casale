package it.polimi.ingsw.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;

public class Intro extends JPanel implements ActionListener {

    private final JButton local;
    private final JButton multi;
    private final ClientMessageHandler handler;
    public Intro(ClientMessageHandler handler){

        local = new JButton("Play Local");
        multi = new JButton("Play Multi");
        local.setBounds(50, 320, 100, 50);
        multi.setBounds(260, 320, 100, 50);
        multi.addActionListener(this);
        local.addActionListener(this);
        this.add(local);
        this.add(multi);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
        this.handler = handler;

    }

    public void paint(Graphics g){
        g.drawString("Welcome to Master of Renaissance", 100, 50);
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g){
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("homepage.jpeg");
        BufferedImage img;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 100,100, 200,200, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message received = null;
        if(e.getSource() == multi) {

            try {
                handler.sendMessageToServer("create game");
            } catch (EndingGameException endingGameException) {
                endingGameException.printStackTrace();
            }
            try {
                received = handler.readMessage();
            } catch (EndingGameException endingGameException) {
                endingGameException.printStackTrace();
            }
            if (received.equals("ok")){
                Main.frame.remove(this);
                Main.frame.add(new Multi(handler));
                Main.frame.revalidate();
            }
            if (received.equals("ko")){

            }

        } else if (e.getSource() == local){
            Main.frame.remove(this);
            Main.frame.add(new Local());
            Main.frame.revalidate();

        }
    }
}

