package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.leader.LeaderOfConversions;
import it.polimi.ingsw.leader.LeaderOfDiscounts;
import it.polimi.ingsw.leader.LeaderOfProductions;
import it.polimi.ingsw.resources.ResourceCounter;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ShowLeaderCard extends JPanel implements ActionListener {

    private LeaderCard[] leaderCards;
    private final JButton back;
    private final ClientMessageHandler handler;
    private final boolean fromTurn;

    public ShowLeaderCard(ClientMessageHandler handler, boolean fromTurn){
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.setBounds(500, 500, 100, 50);
        back.addActionListener(this);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(800, 800);
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g){
        leaderCards = Data.instanceCreator().getLeaderCards();
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g){
        drawLeaderCard(g, leaderCards);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back && fromTurn){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else if (e.getSource() == back && !fromTurn){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new WaitingTurn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
    }

    private void drawLeaderCard(Graphics g, LeaderCard[] leaderCards){
        int[] requiredRes = new int[0];
        for (LeaderCard leaderCard : leaderCards) {
            if (leaderCard.getRequiredRes().length != 0) {
                requiredRes = ResourceCounter.resCount(leaderCard.getRequiredRes());
            }
        }
        drawRequiredRes(g, requiredRes);
        int[] requiredType = new int[0];
        for (LeaderCard leaderCard : leaderCards){
            if (leaderCard.getRequiredType().length != 0){
                requiredType = leaderCard.getRequiredType();
            }
        }
        drawRequiredType(g, requiredType);
        int[] requiredLevel = new int[0];
        for (LeaderCard leaderCard : leaderCards){
            if(leaderCard.getRequiredLevel().length != 0){
                requiredLevel = leaderCard.getRequiredLevel();
            }
        }
        drawRequiredLevel(g, requiredLevel);

        int[] ability = new int[2];
        for (int i = 0; i < 2; i++){
            ability[i] = leaderCards[i].getAbility();
        }
        drawAbility(g, ability);

        int[] wp = new int[2];
        for (int i = 0; i<2; i++){
            wp[i] = leaderCards[i].getWp();
        }

        drawWp(g, wp);
    }

    private void drawRequiredRes(Graphics g, int[] requiredRes) {
        int x = 0;
        int y = 0;
        ClassLoader cl = this.getClass().getClassLoader();
        BufferedImage img;
        InputStream url;
        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x, 440 + y, null);
                    g.drawString("x" + requiredRes[i], 55 + x + 20, 500 + y);
                    x += 150;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x, 500 + y, null);
                    g.drawString("x" + requiredRes[i], 55 + x + 20, 500 + y);
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x + 20, 500 + y, null);
                    g.drawString("x" + requiredRes[i], 55 + x + 20, 500 + y);
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x + 20, 500 + y, null);
                    g.drawString("x" + requiredRes[i], 55 + x + 20, 500 + y);
                    break;
                default:
                    break;
            }

            if (i % 3 == 0 && i != 0) {
                x = 0;
                y = y + 250;
            }
        }
    }
    private void drawRequiredType(Graphics g, int[] requiredType) {
        int x = 0;
        int y = 0;
        ClassLoader cl = this.getClass().getClassLoader();
        BufferedImage img;
        InputStream url;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    url = cl.getResourceAsStream("type 1.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x, 440 + y, null);
                    g.drawString("x" + requiredType[i], 55 + x + 20, 500 + y);
                    x += 150;
                    break;
                case 1:
                    url = cl.getResourceAsStream("type 2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x, 500 + y, null);
                    g.drawString("x" + requiredType[i], 55 + x + 20, 500 + y);
                    break;
                case 2:
                    url = cl.getResourceAsStream("type 3.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x + 20, 500 + y, null);
                    g.drawString("x" + requiredType[i], 55 + x + 20, 500 + y);
                    break;
                case 3:
                    url = cl.getResourceAsStream("type 4.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x + 20, 500 + y, null);
                    g.drawString("x" + requiredType[i], 55 + x + 20, 500 + y);
                    break;
                default:
                    break;
            }

            if (i % 3 == 0 && i != 0) {
                x = 0;
                y = y + 250;
            }
        }
    }

    private void drawRequiredLevel(Graphics g, int[] requiredLevel) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < requiredLevel.length; i++) {
            g.drawString("Level:" + requiredLevel[i], 55 + x, 440 + y);
            x += 150;
            if (i % 3 == 0 && i != 0) {
                x = 0;
                y = y + 250;
            }
        }
    }

    private void drawAbility(Graphics g, int[] ability){
        for (int i = 0; i < 2; i++){
            switch (ability[i]){
                case 0:
                    g.drawString("Discount", 50, 50);
                    switch(((LeaderOfDiscounts)leaderCards[i]).getDiscountedRes().getResType()) {
                        case 0:
                            g.drawString("Resource discounted: coin", 50, 50);
                            break;
                        case 1:
                            g.drawString("Resource discounted: stone", 50, 50);
                            break;
                        case 2:
                            g.drawString("Resource discounted: servant", 50, 50);
                            break;
                        case 3:
                            g.drawString("Resource discounted: shield", 50, 50);
                            break;
                    }
                case 1:
                    g.drawString("Depots", 50 , 50);
                    switch(((LeaderOfDiscounts)leaderCards[i]).getDiscountedRes().getResType()){
                        case 0:
                            g.drawString("Resource depot: coin", 50, 50);
                            break;
                        case 1:
                            g.drawString("Resource depot: stone", 50, 50);
                            break;
                        case 2:
                            g.drawString("Resource depot: servant", 50, 50);
                            break;
                        case 3:
                            g.drawString("Resource depot: shield", 50, 50);
                            break;
                    }
                    break;
                case 2:
                    g.drawString("Conversions", 50, 50);
                    switch(((LeaderOfConversions)leaderCards[i]).getConvertedResource().getResType()){
                        case 0:
                            g.drawString("Resource converted: coin", 50, 50);
                            break;
                        case 1:
                            g.drawString("Resource converted: stone", 50, 50);
                            break;
                        case 2:
                            g.drawString("Resource converted: servant", 50, 50);
                            break;
                        case 3:
                            g.drawString("Resource converted: shield", 50, 50);
                            break;

                    }
                    break;
                case 3:
                    g.drawString("Production", 50, 50);
                    switch(((LeaderOfProductions)leaderCards[i]).getRequiredResource().getResType()){
                        case 0:
                            g.drawString("Resource required for production: coin", 50, 50);
                            break;
                        case 1:
                            g.drawString("Resource required for production: stone", 50, 50);
                            break;
                        case 2:
                            g.drawString("Resource required for production: servant", 50, 50);
                            break;
                        case 3:
                            g.drawString("Resource required for production: shield", 50, 50);
                            break;

                    }
                    break;
                default:
                    break;
            }
        }
    }
    private void drawWp(Graphics g, int[] wp){
        for (int i = 0; i < 2; i++) {
            g.drawString("Win Points:" + wp[i], 50, 50);
        }
    }
}


