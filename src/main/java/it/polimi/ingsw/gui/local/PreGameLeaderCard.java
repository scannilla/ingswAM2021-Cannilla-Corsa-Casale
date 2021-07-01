package it.polimi.ingsw.gui.local;


import it.polimi.ingsw.controller.EndingGameException;

import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.multi.WaitingTurn;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class PreGameLeaderCard extends JPanel implements ActionListener {

    private final JButton leaderOne, leaderTwo, leaderThree, leaderFour;
    private final SPClientMessageHandler handler;

    public PreGameLeaderCard(SPClientMessageHandler handler) {
        this.handler = handler;
        leaderOne = new JButton("Select->");
        leaderTwo = new JButton("Select->");
        leaderThree = new JButton("Select->");
        leaderFour = new JButton("Select->");
        leaderOne.setBounds(5, 50, 90, 50);
        leaderTwo.setBounds(705, 50, 90, 50);
        leaderThree.setBounds(5, 400, 90, 50);
        leaderFour.setBounds(705, 400, 90, 50);
        leaderOne.addActionListener(this);
        leaderTwo.addActionListener(this);
        leaderThree.addActionListener(this);
        leaderFour.addActionListener(this);
        this.add(leaderOne);
        this.add(leaderTwo);
        this.add(leaderThree);
        this.add(leaderFour);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);

    }

    public void paint(Graphics g) {
        Font f = new Font("Times New Roman", Font.BOLD, 16);
        LeaderCard[] toChooseLeaderCard = Data.instanceCreator().getToChooseLeaderCards();
        g.setFont(f);
        g.drawString("Choose two of the four leader cards available", 10, 10);
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g) {
        LeaderCard[] toChooseLeaderCard = Data.instanceCreator().getToChooseLeaderCards();
        ClassLoader cl = this.getClass().getClassLoader();
        int wp1 = toChooseLeaderCard[0].getWp();
        int wp2 = toChooseLeaderCard[1].getWp();
        int wp3 = toChooseLeaderCard[2].getWp();
        int wp4 = toChooseLeaderCard[3].getWp();
        int ability1 = toChooseLeaderCard[0].getAbility();
        int ability2 = toChooseLeaderCard[1].getAbility();
        int ability3 = toChooseLeaderCard[2].getAbility();
        int ability4 = toChooseLeaderCard[3].getAbility();
        Resource[] reqRes1 = toChooseLeaderCard[0].getRequiredRes();
        Resource[] reqRes2 = toChooseLeaderCard[1].getRequiredRes();
        Resource[] reqRes3 = toChooseLeaderCard[2].getRequiredRes();
        Resource[] reqRes4 = toChooseLeaderCard[3].getRequiredRes();
        int[] reqRess1 = new int[4];
        int[] reqRess2 = new int[4];
        int[] reqRess3 = new int[4];
        int[] reqRess4 = new int[4];
        if (reqRes1.length!=0) {
            reqRess1 = ResourceCounter.resCount(toChooseLeaderCard[0].getRequiredRes());
        }
        if (reqRes1.length!=0) {
            reqRess2 = ResourceCounter.resCount(toChooseLeaderCard[1].getRequiredRes());
        }
        if (reqRes1.length!=0) {
            reqRess3 = ResourceCounter.resCount(toChooseLeaderCard[2].getRequiredRes());
        }
        if (reqRes1.length!=0) {
            reqRess4 = ResourceCounter.resCount(toChooseLeaderCard[3].getRequiredRes());
        }
        int[] reqType1 = toChooseLeaderCard[0].getRequiredType();
        int[] reqType2 = toChooseLeaderCard[1].getRequiredType();
        int[] reqType3 = toChooseLeaderCard[2].getRequiredType();
        int[] reqType4 = toChooseLeaderCard[3].getRequiredType();
        int[] reqLevel1 = toChooseLeaderCard[0].getRequiredLevel();
        int[] reqLevel2 = toChooseLeaderCard[1].getRequiredLevel();
        int[] reqLevel3 = toChooseLeaderCard[2].getRequiredLevel();
        int[] reqLevel4 = toChooseLeaderCard[3].getRequiredLevel();
        int[] reqCards1 = new int[12];
        int[] reqCards2 = new int[12];
        int[] reqCards3 = new int[12];
        int[] reqCards4 = new int[12];
        if (reqType1.length != 0 && reqLevel1.length != 0) {
            reqCards1 = requiredCardCounter(reqType1, reqLevel1);
            g.drawString("L1", 110, 75);
            g.drawString("L1", 185, 75);
            g.drawString("L1", 260, 75);
            g.drawString("L1", 335, 75);
            g.drawString("L2", 110, 125);
            g.drawString("L2", 185, 125);
            g.drawString("L2", 260, 125);
            g.drawString("L2", 335, 125);
            g.drawString("L3", 110, 175);
            g.drawString("L3", 185, 175);
            g.drawString("L3", 260, 175);
            g.drawString("L3", 335, 175);
        }
        if (reqType2.length != 0 && reqLevel2.length != 0) {
            reqCards2 = requiredCardCounter(reqType2, reqLevel2);
            g.drawString("L1", 410, 75);
            g.drawString("L1", 485, 75);
            g.drawString("L1", 560, 75);
            g.drawString("L1", 635, 75);
            g.drawString("L2", 410, 125);
            g.drawString("L2", 485, 125);
            g.drawString("L2", 560, 125);
            g.drawString("L2", 635, 125);
            g.drawString("L3", 410, 175);
            g.drawString("L3", 485, 175);
            g.drawString("L3", 560, 175);
            g.drawString("L3", 635, 175);
        }
        if (reqType3.length != 0 && reqLevel3.length != 0) {
            reqCards3 = requiredCardCounter(reqType3, reqLevel3);
            g.drawString("L1", 110, 425);
            g.drawString("L1", 185, 425);
            g.drawString("L1", 260, 425);
            g.drawString("L1", 335, 425);
            g.drawString("L2", 110, 475);
            g.drawString("L2", 185, 475);
            g.drawString("L2", 260, 475);
            g.drawString("L2", 335, 475);
            g.drawString("L3", 110, 525);
            g.drawString("L3", 185, 525);
            g.drawString("L3", 260, 525);
            g.drawString("L3", 335, 525);
        }
        if (reqType4.length != 0 && reqLevel4.length != 0) {
            reqCards4 = requiredCardCounter(reqType4, reqLevel4);
            g.drawString("L1", 410, 425);
            g.drawString("L1", 485, 425);
            g.drawString("L1", 560, 425);
            g.drawString("L1", 635, 425);
            g.drawString("L2", 410, 475);
            g.drawString("L2", 485, 475);
            g.drawString("L2", 560, 475);
            g.drawString("L2", 635, 475);
            g.drawString("L3", 410, 525);
            g.drawString("L3", 485, 525);
            g.drawString("L3", 560, 525);
            g.drawString("L3", 635, 525);
        }
        InputStream url;
        BufferedImage img;
        url = cl.getResourceAsStream("coin2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqRes1.length!=0) {
            g.drawImage(img, 125, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess1[0]), 100, 125);
        }
        if (reqRes2.length!=0) {
            g.drawImage(img, 425, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess2[0]), 400, 125);
        }
        if (reqRes3.length!=0) {
            g.drawImage(img, 125, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess3[0]), 100, 525);
        }
        if (reqRes4.length!=0) {
            g.drawImage(img, 425, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess4[0]), 400, 525);
        }
        url = cl.getResourceAsStream("stone2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqRes1.length!=0) {
            g.drawImage(img, 200, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess1[1]), 100, 125);
        }
        if (reqRes2.length!=0) {
            g.drawImage(img, 500, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess2[1]), 400, 125);
        }
        if (reqRes3.length!=0) {
            g.drawImage(img, 200, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess3[1]), 100, 525);
        }
        if (reqRes4.length!=0) {
            g.drawImage(img, 500, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess4[1]), 400, 525);
        }
        url = cl.getResourceAsStream("servant2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqRes1.length!=0) {
            g.drawImage(img, 275, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess1[2]), 100, 125);
        }
        if (reqRes2.length!=0) {
            g.drawImage(img, 575, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess2[2]), 400, 125);
        }
        if (reqRes3.length!=0) {
            g.drawImage(img, 275, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess3[2]), 100, 525);
        }
        if (reqRes4.length!=0) {
            g.drawImage(img, 575, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess4[2]), 400, 525);
        }
        url = cl.getResourceAsStream("shield2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqRes1.length!=0) {
            g.drawImage(img, 350, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess1[3]), 100, 125);
        }
        if (reqRes2.length!=0) {
            g.drawImage(img, 650, 100, 50, 50, null);
            g.drawString(String.valueOf(reqRess2[3]), 400, 125);
        }
        if (reqRes3.length!=0) {
            g.drawImage(img, 350, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess3[3]), 100, 525);
        }
        if (reqRes4.length!=0) {
            g.drawImage(img, 650, 500, 50, 50, null);
            g.drawString(String.valueOf(reqRess4[3]), 400, 525);
        }
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 1.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqType1.length != 0 && reqLevel1.length != 0) {
            g.drawImage(img, 125, 50, 50, 50, null);
            g.drawImage(img, 125, 100, 50, 50, null);
            g.drawImage(img, 125, 150, 50, 50, null);
        }
        if (reqType2.length != 0 && reqLevel2.length != 0) {
            g.drawImage(img, 425, 50, 50, 50, null);
            g.drawImage(img, 425, 100, 50, 50, null);
            g.drawImage(img, 425, 150, 50, 50, null);
        }
        if (reqType3.length != 0 && reqLevel3.length != 0) {
            g.drawImage(img, 125, 400, 50, 50, null);
            g.drawImage(img, 125, 450, 50, 50, null);
            g.drawImage(img, 125, 500, 50, 50, null);
        }
        if (reqType4.length != 0 && reqLevel4.length != 0) {
            g.drawImage(img, 425, 400, 50, 50, null);
            g.drawImage(img, 425, 450, 50, 50, null);
            g.drawImage(img, 425, 500, 50, 50, null);
        }
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqType1.length != 0 && reqLevel1.length != 0) {
            g.drawImage(img, 200, 50, 50, 50, null);
            g.drawImage(img, 200, 100, 50, 50, null);
            g.drawImage(img, 200, 150, 50, 50, null);
        }
        if (reqType2.length != 0 && reqLevel2.length != 0) {
            g.drawImage(img, 500, 50, 50, 50, null);
            g.drawImage(img, 500, 100, 50, 50, null);
            g.drawImage(img, 500, 150, 50, 50, null);
        }
        if (reqType3.length != 0 && reqLevel3.length != 0) {
            g.drawImage(img, 200, 400, 50, 50, null);
            g.drawImage(img, 200, 450, 50, 50, null);
            g.drawImage(img, 200, 500, 50, 50, null);
        }
        if (reqType4.length != 0 && reqLevel4.length != 0) {
            g.drawImage(img, 500, 400, 50, 50, null);
            g.drawImage(img, 500, 450, 50, 50, null);
            g.drawImage(img, 500, 500, 50, 50, null);
        }
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 3.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqType1.length != 0 && reqLevel1.length != 0) {
            g.drawImage(img, 275, 50, 50, 50, null);
            g.drawImage(img, 275, 100, 50, 50, null);
            g.drawImage(img, 275, 150, 50, 50, null);
        }
        if (reqType2.length != 0 && reqLevel2.length != 0) {
            g.drawImage(img, 575, 50, 50, 50, null);
            g.drawImage(img, 575, 100, 50, 50, null);
            g.drawImage(img, 575, 150, 50, 50, null);
        }
        if (reqType3.length != 0 && reqLevel3.length != 0) {
            g.drawImage(img, 275, 400, 50, 50, null);
            g.drawImage(img, 275, 450, 50, 50, null);
            g.drawImage(img, 275, 500, 50, 50, null);
        }
        if (reqType4.length != 0 && reqLevel4.length != 0) {
            g.drawImage(img, 575, 400, 50, 50, null);
            g.drawImage(img, 575, 450, 50, 50, null);
            g.drawImage(img, 575, 500, 50, 50, null);
        }
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 4.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqType1.length != 0 && reqLevel1.length != 0) {
            g.drawImage(img, 350, 50, 50, 50, null);
            g.drawImage(img, 350, 100, 50, 50, null);
            g.drawImage(img, 350, 150, 50, 50, null);
        }
        if (reqType2.length != 0 && reqLevel2.length != 0) {
            g.drawImage(img, 650, 50, 50, 50, null);
            g.drawImage(img, 650, 100, 50, 50, null);
            g.drawImage(img, 650, 150, 50, 50, null);
        }
        if (reqType3.length != 0 && reqLevel3.length != 0) {
            g.drawImage(img, 350, 400, 50, 50, null);
            g.drawImage(img, 350, 450, 50, 50, null);
            g.drawImage(img, 350, 500, 50, 50, null);
        }
        if (reqType4.length != 0 && reqLevel4.length != 0) {
            g.drawImage(img, 650, 400, 50, 50, null);
            g.drawImage(img, 650, 450, 50, 50, null);
            g.drawImage(img, 650, 500, 50, 50, null);
        }

        if (reqType1.length != 0 && reqLevel1.length != 0) {
            g.drawString(String.valueOf(reqCards1[0]), 100, 75);
            g.drawString(String.valueOf(reqCards1[1]), 100, 125);
            g.drawString(String.valueOf(reqCards1[2]), 100, 175);

            g.drawString(String.valueOf(reqCards1[3]), 175, 75);
            g.drawString(String.valueOf(reqCards1[4]), 175, 125);
            g.drawString(String.valueOf(reqCards1[5]), 175, 175);

            g.drawString(String.valueOf(reqCards1[6]), 250, 75);
            g.drawString(String.valueOf(reqCards1[7]), 250, 125);
            g.drawString(String.valueOf(reqCards1[8]), 250, 175);

            g.drawString(String.valueOf(reqCards1[9]), 325, 75);
            g.drawString(String.valueOf(reqCards1[10]), 325, 125);
            g.drawString(String.valueOf(reqCards1[11]), 325, 175);
        }
        if (reqType2.length != 0 && reqLevel2.length != 0) {
            g.drawString(String.valueOf(reqCards2[0]), 400, 75);
            g.drawString(String.valueOf(reqCards2[1]), 400, 125);
            g.drawString(String.valueOf(reqCards2[2]), 400, 175);

            g.drawString(String.valueOf(reqCards2[3]), 475, 75);
            g.drawString(String.valueOf(reqCards2[4]), 475, 125);
            g.drawString(String.valueOf(reqCards2[5]), 475, 175);

            g.drawString(String.valueOf(reqCards2[6]), 550, 75);
            g.drawString(String.valueOf(reqCards2[7]), 550, 125);
            g.drawString(String.valueOf(reqCards2[8]), 550, 175);

            g.drawString(String.valueOf(reqCards2[9]), 625, 75);
            g.drawString(String.valueOf(reqCards2[10]), 625, 125);
            g.drawString(String.valueOf(reqCards2[11]), 625, 175);
        }
        if (reqType3.length != 0 && reqLevel3.length != 0) {
            g.drawString(String.valueOf(reqCards3[0]), 100, 425);
            g.drawString(String.valueOf(reqCards3[1]), 100, 475);
            g.drawString(String.valueOf(reqCards3[2]), 100, 525);

            g.drawString(String.valueOf(reqCards3[3]), 175, 425);
            g.drawString(String.valueOf(reqCards3[4]), 175, 475);
            g.drawString(String.valueOf(reqCards3[5]), 175, 525);

            g.drawString(String.valueOf(reqCards3[6]), 250, 425);
            g.drawString(String.valueOf(reqCards3[7]), 250, 475);
            g.drawString(String.valueOf(reqCards3[8]), 250, 525);

            g.drawString(String.valueOf(reqCards3[9]), 325, 425);
            g.drawString(String.valueOf(reqCards3[10]), 325, 475);
            g.drawString(String.valueOf(reqCards3[11]), 325, 525);
        }
        if (reqType4.length != 0 && reqLevel4.length != 0) {
            g.drawString(String.valueOf(reqCards4[0]), 400, 425);
            g.drawString(String.valueOf(reqCards4[1]), 400, 475);
            g.drawString(String.valueOf(reqCards4[2]), 400, 525);

            g.drawString(String.valueOf(reqCards4[3]), 475, 425);
            g.drawString(String.valueOf(reqCards4[4]), 475, 475);
            g.drawString(String.valueOf(reqCards4[5]), 475, 525);

            g.drawString(String.valueOf(reqCards4[6]), 550, 425);
            g.drawString(String.valueOf(reqCards4[7]), 550, 475);
            g.drawString(String.valueOf(reqCards4[8]), 550, 525);

            g.drawString(String.valueOf(reqCards4[9]), 625, 425);
            g.drawString(String.valueOf(reqCards4[10]), 625, 475);
            g.drawString(String.valueOf(reqCards4[11]), 625, 525);
        }
        if (reqRes1.length!=0) {
            g.drawString(String.valueOf(reqRes1[0]), 100, 225);
            g.drawString(String.valueOf(reqRes1[1]), 175, 225);
            g.drawString(String.valueOf(reqRes1[2]), 250, 225);
            g.drawString(String.valueOf(reqRes1[3]), 325, 225);
        }
        if (reqRes2.length!=0) {
            g.drawString(String.valueOf(reqRes2[0]), 400, 225);
            g.drawString(String.valueOf(reqRes2[1]), 475, 225);
            g.drawString(String.valueOf(reqRes2[2]), 550, 225);
            g.drawString(String.valueOf(reqRes2[3]), 625, 225);
        }
        if (reqRes3.length!=0) {
            g.drawString(String.valueOf(reqRes3[0]), 100, 575);
            g.drawString(String.valueOf(reqRes3[1]), 175, 575);
            g.drawString(String.valueOf(reqRes3[2]), 250, 575);
            g.drawString(String.valueOf(reqRes3[3]), 325, 575);
        }
        if (reqRes4.length!=0) {
            g.drawString(String.valueOf(reqRes4[0]), 400, 575);
            g.drawString(String.valueOf(reqRes4[1]), 475, 575);
            g.drawString(String.valueOf(reqRes4[2]), 550, 575);
            g.drawString(String.valueOf(reqRes4[3]), 625, 575);
        }

        switch (switchRes(ability1, toChooseLeaderCard[0])) {
            case 1:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
        }

        switch (switchRes(ability2, toChooseLeaderCard[1])) {
            case 1:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
        }

        switch (switchRes(ability3, toChooseLeaderCard[2])) {
            case 1:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
        }

        switch (switchRes(ability4, toChooseLeaderCard[3])) {
            case 1:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
        }
        g.drawString("WP: ", 100, 250);
        g.drawString("WP: ", 400, 250);
        g.drawString("WP: ", 100, 600);
        g.drawString("WP: ", 400, 600);
        g.drawString(String.valueOf(wp1), 230, 250);
        g.drawString(String.valueOf(wp2), 530, 250);
        g.drawString(String.valueOf(wp3), 230, 600);
        g.drawString(String.valueOf(wp4), 530, 600);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leaderOne) {
            try {
                handler.sendMessageToServer("1", 161);
            } catch (EndingGameException ex) {
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }

        } else if (e.getSource() == leaderTwo) {

            try {
                handler.sendMessageToServer("2", 161);
            } catch (EndingGameException ex) {
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if (e.getSource() == leaderThree) {
            try {
                handler.sendMessageToServer("3", 161);
            } catch (EndingGameException ex) {
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if (e.getSource() == leaderFour) {
            try {
                handler.sendMessageToServer("4", 161);
            } catch (EndingGameException ex) {
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }

        }
    }

    public int[] requiredCardCounter (int[] reqType, int[] reqLevel){
        int[] reqCards = new int[12];
        for(int i = 0; i<reqType.length; i++){
            if (reqType[i]==1){
                switch(reqLevel[i]){
                    case 1: reqCards[0]++;
                        break;
                    case 2: reqCards[1]++;
                        break;
                    case 3: reqCards[2]++;
                        break;
                }
            }
            if (reqType[i]==2){
                switch(reqLevel[i]){
                    case 1: reqCards[3]++;
                        break;
                    case 2: reqCards[4]++;
                        break;
                    case 3: reqCards[5]++;
                        break;
                }
            }
            if (reqType[i]==3){
                switch(reqLevel[i]){
                    case 1: reqCards[6]++;
                        break;
                    case 2: reqCards[7]++;
                        break;
                    case 3: reqCards[8]++;
                        break;
                }
            }
            if (reqType[i]==4){
                switch(reqLevel[i]){
                    case 1: reqCards[9]++;
                        break;
                    case 2: reqCards[10]++;
                        break;
                    case 3: reqCards[11]++;
                        break;
                }
            }
        }

        return reqCards;
    }

    public int switchRes(int ability, LeaderCard leaderCard) {
        int num = 22;
        int res;
        switch (ability) {
            case 0: res = ((LeaderOfDiscounts) leaderCard).getDiscountedRes().getResType();
                if (res == 0)
                    num = 1;
                if (res == 1)
                    num = 2;
                if (res == 2)
                    num = 3;
                if (res == 3)
                    num = 4;
                break;
            case 1: res = ((LeaderOfDepots) leaderCard).getResource().getResType();
                if (res == 0)
                    num = 5;
                if (res == 1)
                    num = 6;
                if (res == 2)
                    num = 7;
                if (res == 3)
                    num = 8;
                break;
            case 2: res = ((LeaderOfConversions) leaderCard).getConvertedResource().getResType();
                if (res == 0)
                    num = 9;
                if (res == 1)
                    num = 10;
                if (res == 2)
                    num = 11;
                if (res == 3)
                    num = 12;
                break;
            case 3: res = ((LeaderOfProductions) leaderCard).getRequiredResource().getResType();
                if (res == 0)
                    num = 13;
                if (res == 1)
                    num = 14;
                if (res == 2)
                    num = 15;
                if (res == 3)
                    num = 16;
                break;
        }
        return num;
    }
}
