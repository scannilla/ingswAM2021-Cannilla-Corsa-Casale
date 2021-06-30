package it.polimi.ingsw.gui.multi;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;


public class PreGameRes extends JPanel implements ActionListener {


    private JButton coinButton, stoneButton, servantButton, shieldButton, goAhead;
    private ClientMessageHandler handler;
    private Message received;

    public PreGameRes(ClientMessageHandler handler) {
        this.handler = handler;
        try {
            received = handler.readMessage();
        } catch (EndingGameException ex) {
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Intro("error", 1));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
        if (!(received.getMessage().equals("You're the first player so you're not gonna receive any resource or faith point"))) {
            coinButton = new JButton("Select");
            stoneButton = new JButton("Select");
            servantButton = new JButton("Select");
            shieldButton = new JButton("Select");
            goAhead = new JButton("Continue");
            coinButton.setBounds(100, 560, 100, 50);
            stoneButton.setBounds(250, 560, 100, 50);
            servantButton.setBounds(400, 560, 100, 50);
            shieldButton.setBounds(550, 560, 100, 50);
            goAhead.setBounds(350, 620, 100, 50);
            coinButton.addActionListener(this);
            stoneButton.addActionListener(this);
            servantButton.addActionListener(this);
            shieldButton.addActionListener(this);
            goAhead.addActionListener(this);
            this.add(goAhead);
            this.add(coinButton);
            this.add(stoneButton);
            this.add(servantButton);
            this.add(shieldButton);
            this.setLayout(null);
            this.setSize(800, 800);
            this.setVisible(true);
            this.setBackground(Color.WHITE);
        }
    }


    public void paint(Graphics g) {
        switch (received.getMessage()) {
            case "You're the first player so you're not gonna receive any resource or faith point":
                g.drawString(received.getMessage(), 100, 50);
                g.drawString("Please wait for other players", 100, 100);
                break;
            case "You're the second player so you can choose a resource to add to your warehouse depot":
            case "You're the third player so you can choose a resource to add to your warehouse depot, increased faith points by one":
            case "You're the fourth player so you can choose two resources to add to your warehouse depot, increased faith points by one":
                g.drawString(received.getMessage(), 100, 50);
                myDrawImagePNG(g);
                break;

        }

    }

    private void myDrawImagePNG(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream urlCoin = cl.getResourceAsStream("coin2.png");
        InputStream urlStone = cl.getResourceAsStream("stone2.png");
        InputStream urlServant = cl.getResourceAsStream("servant2.png");
        InputStream urlShield = cl.getResourceAsStream("shield2.png");
        BufferedImage imgCoin, imgStone, imgServant, imgShield;

        try {
            imgCoin = ImageIO.read(urlCoin);
            imgStone = ImageIO.read(urlStone);
            imgServant = ImageIO.read(urlServant);
            imgShield = ImageIO.read(urlShield);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(imgCoin, 100, 450, 100, 100, null);
        g.drawImage(imgStone, 250, 450, 100, 100, null);
        g.drawImage(imgServant, 400, 450, 100, 100, null);
        g.drawImage(imgShield, 550, 450, 100, 100, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        chooseResource(e);
        MainGUI.frame.remove(this);
        MainGUI.frame.add(new PreGameLeader(handler));
        MainGUI.frame.revalidate();
        MainGUI.frame.repaint();
    }

    public void chooseResource(ActionEvent e){
        int numAction = 0;
        switch(received.getMessage()){
            case "You're the second player so you can choose a resource to add to your warehouse depot":
            case "You're the third player so you can choose a resource to add to your warehouse depot, increased faith points by one":
            case "You're the fourth player so you can choose two resources to add to your warehouse depot, increased faith points by one":

                if(e.getSource()==coinButton){
                        try {
                            handler.sendMessageToServer("coin", 160);
                        } catch(EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    } else if(e.getSource()==stoneButton){
                        try{
                            handler.sendMessageToServer("stone", 160);
                        } catch(EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    } else if(e.getSource()==servantButton){
                        try{
                            handler.sendMessageToServer("servant", 160);
                        } catch (EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    } else if(e.getSource() == shieldButton){
                        try{
                            handler.sendMessageToServer("shield", 160);
                        } catch(EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    }
                numAction++;
                }
            case "You're the fourth player so you can choose two resources to add to your warehouse depot, increased faith points by one":
                while(numAction!=2){
                    if(e.getSource()==coinButton){
                        try {
                            handler.sendMessageToServer("coin", 160);
                        } catch(EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    } else if(e.getSource()==stoneButton){
                        try{
                            handler.sendMessageToServer("stone", 160);
                        } catch(EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    } else if(e.getSource()==servantButton){
                        try{
                            handler.sendMessageToServer("servant", 160);
                        } catch (EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    } else if(e.getSource() == shieldButton){
                        try{
                            handler.sendMessageToServer("shield", 160);
                        } catch(EndingGameException ex){
                            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
                        }
                    }
                    numAction++;
                }
        }

    }




}



