package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;

import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.leader.*;
import it.polimi.ingsw.resources.Resource;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class PreGameLeader extends JPanel implements ActionListener {

    private final JButton leaderOne, leaderTwo, leaderThree, leaderFour;
    private final ClientMessageHandler handler;

    public PreGameLeader(ClientMessageHandler handler) {
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
        g.setFont(f);
        g.drawString("Choose two of the four leader cards available", 0, 0);
        g.drawString("1", 125, 50);
        g.drawString("1", 200, 50);
        g.drawString("1", 275, 50);
        g.drawString("1", 350, 50);
        g.drawString("1", 425, 50);
        g.drawString("1", 500, 50);
        g.drawString("1", 575, 50);
        g.drawString("1", 650, 50);
        g.drawString("1", 125, 400);
        g.drawString("1", 200, 400);
        g.drawString("1", 275, 400);
        g.drawString("1", 350, 400);
        g.drawString("1", 425, 400);
        g.drawString("1", 500, 400);
        g.drawString("1", 575, 400);
        g.drawString("1", 650, 400);

        g.drawString("2", 125, 100);
        g.drawString("2", 200, 100);
        g.drawString("2", 275, 100);
        g.drawString("2", 350, 100);
        g.drawString("2", 425, 100);
        g.drawString("2", 500, 100);
        g.drawString("2", 575, 100);
        g.drawString("2", 650, 100);
        g.drawString("2", 125, 450);
        g.drawString("2", 200, 450);
        g.drawString("2", 275, 450);
        g.drawString("2", 350, 450);
        g.drawString("2", 425, 450);
        g.drawString("2", 500, 450);
        g.drawString("2", 575, 450);
        g.drawString("2", 650, 450);

        g.drawString("3", 125, 150);
        g.drawString("3", 200, 150);
        g.drawString("3", 275, 150);
        g.drawString("3", 350, 150);
        g.drawString("3", 425, 150);
        g.drawString("3", 500, 150);
        g.drawString("3", 575, 150);
        g.drawString("3", 650, 150);
        g.drawString("3", 125, 500);
        g.drawString("3", 200, 500);
        g.drawString("3", 275, 500);
        g.drawString("3", 350, 500);
        g.drawString("3", 425, 500);
        g.drawString("3", 500, 500);
        g.drawString("3", 575, 500);
        g.drawString("3", 650, 500);
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
        int[] reqType1 = toChooseLeaderCard[0].getRequiredType();
        int[] reqType2 = toChooseLeaderCard[1].getRequiredType();
        int[] reqType3 = toChooseLeaderCard[2].getRequiredType();
        int[] reqType4 = toChooseLeaderCard[3].getRequiredType();
        int[] reqLevel1 = toChooseLeaderCard[0].getRequiredLevel();
        int[] reqLevel2 = toChooseLeaderCard[1].getRequiredLevel();
        int[] reqLevel3 = toChooseLeaderCard[2].getRequiredLevel();
        int[] reqLevel4 = toChooseLeaderCard[3].getRequiredLevel();
        int[] reqCards1 = requiredCardCounter(reqType1, reqLevel1);
        int[] reqCards2 = requiredCardCounter(reqType2, reqLevel2);
        int[] reqCards3 = requiredCardCounter(reqType3, reqLevel3);
        int[] reqCards4 = requiredCardCounter(reqType4, reqLevel4);
        InputStream url;
        BufferedImage img;
        url = cl.getResourceAsStream("coin2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 125, 200, 50, 50, null);
        g.drawImage(img, 425, 200, 50, 50, null);
        g.drawImage(img, 125, 600, 50, 50, null);
        g.drawImage(img, 425, 600, 50, 50, null);
        url = cl.getResourceAsStream("stone2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 200, 200, 50, 50, null);
        g.drawImage(img, 500, 200, 50, 50, null);
        g.drawImage(img, 200, 600, 50, 50, null);
        g.drawImage(img, 500, 600, 50, 50, null);
        url = cl.getResourceAsStream("servant2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 275, 200, 50, 50, null);
        g.drawImage(img, 575, 200, 50, 50, null);
        g.drawImage(img, 275, 600, 50, 50, null);
        g.drawImage(img, 575, 600, 50, 50, null);
        url = cl.getResourceAsStream("shield2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 350, 200, 50, 50, null);
        g.drawImage(img, 650, 200, 50, 50, null);
        g.drawImage(img, 350, 600, 50, 50, null);
        g.drawImage(img, 650, 600, 50, 50, null);
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 1.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 125, 50, 50, 50, null);
        g.drawImage(img, 125, 100, 50, 50, null);
        g.drawImage(img, 125, 150, 50, 50, null);
        g.drawImage(img, 425, 50, 50, 50, null);
        g.drawImage(img, 425, 100, 50, 50, null);
        g.drawImage(img, 425, 150, 50, 50, null);
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 200, 50, 50, 50, null);
        g.drawImage(img, 200, 100, 50, 50, null);
        g.drawImage(img, 200, 150, 50, 50, null);
        g.drawImage(img, 500, 50, 50, 50, null);
        g.drawImage(img, 500, 100, 50, 50, null);
        g.drawImage(img, 500, 150, 50, 50, null);
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 3.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 275, 50, 50, 50, null);
        g.drawImage(img, 275, 100, 50, 50, null);
        g.drawImage(img, 275, 150, 50, 50, null);
        g.drawImage(img, 575, 50, 50, 50, null);
        g.drawImage(img, 575, 100, 50, 50, null);
        g.drawImage(img, 575, 150, 50, 50, null);
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 4.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 350, 50, 50, 50, null);
        g.drawImage(img, 350, 100, 50, 50, null);
        g.drawImage(img, 350, 150, 50, 50, null);
        g.drawImage(img, 650, 50, 50, 50, null);
        g.drawImage(img, 650, 100, 50, 50, null);
        g.drawImage(img, 650, 150, 50, 50, null);
        int k = 0;
        for (int i = 0; i < 12; i++) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                    g.drawString(String.valueOf(reqCards1[i]), 100, 50 + k);
                    g.drawString(String.valueOf(reqCards2[i]), 400, 50 + k);
                    g.drawString(String.valueOf(reqCards3[i]), 100, 400 + k);
                    g.drawString(String.valueOf(reqCards4[0]), 400, 400 + k);

                    k += 50;
                    if (i == 2) {
                        k = 0;
                    }
                    break;
                case 3:
                case 4:
                case 5:
                    g.drawString(String.valueOf(reqCards1[i]), 175, 50 + k);
                    g.drawString(String.valueOf(reqCards2[i]), 475, 50 + k);
                    g.drawString(String.valueOf(reqCards3[i]), 100, 400 + k);
                    g.drawString(String.valueOf(reqCards4[i]), 475, 400 + k);

                    k += 50;
                    if (i == 5) {
                        k = 0;
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    g.drawString(String.valueOf(reqCards1[i]), 250, 50 + k);
                    g.drawString(String.valueOf(reqCards2[i]), 550, 50 + k);
                    g.drawString(String.valueOf(reqCards3[i]), 250, 400 + k);
                    g.drawString(String.valueOf(reqCards4[i]), 550, 400 + k);
                    k += 50;
                    if (i == 8) {
                        k = 0;
                    }
                    break;
                case 9:
                case 10:
                case 11:
                    g.drawString(String.valueOf(reqCards1[i]), 325, 50 + k);
                    g.drawString(String.valueOf(reqCards2[i]), 625, 50 + k);
                    g.drawString(String.valueOf(reqCards3[i]), 325, 400 + k);
                    g.drawString(String.valueOf(reqCards4[i]), 625, 400 + k);
                    k += 50;
                    if (i == 11) {
                        k = 0;
                    }
                    break;
            }

        }
        k = 0;
        for (int i = 0; i < 4; i++) {
            g.drawString(String.valueOf(reqRes1[i]), 100 + k, 200);
            g.drawString(String.valueOf(reqRes2[0]), 400 + k, 200);
            g.drawString(String.valueOf(reqRes3[i]), 100 + k, 550);
            g.drawString(String.valueOf(reqRes4[i]), 400 + k, 550);
            k += 50;
        }

        switch (switchRes(ability1, toChooseLeaderCard[0])) {
            case 1:
                url = cl.getResourceAsStream("conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 300, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("prod shield.png");
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
                url = cl.getResourceAsStream("conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 300, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("prod shield.png");
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
                url = cl.getResourceAsStream("conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 650, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("prod shield.png");
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
                url = cl.getResourceAsStream("conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 650, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("prod shield.png");
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
    chooseLeader(e);
    }

    public void chooseLeader(ActionEvent e) {
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

}
