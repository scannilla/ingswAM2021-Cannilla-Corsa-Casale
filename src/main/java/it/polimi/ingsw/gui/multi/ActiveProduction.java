package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.production.ProdCardSlot;
import it.polimi.ingsw.production.ProductionCard;
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

public class ActiveProduction extends JPanel implements ActionListener {

    private PersonalBoard personalBoard;
    private ProdCardSlot prodCardSlot;
    private final JButton selectStand, selectSlot1, selectSlot2, selectSlot3, back;
    private ClientMessageHandler handler;

    public ActiveProduction(ClientMessageHandler handler) {
        this.handler = handler;
        back = new JButton("Go Back");
        selectStand = new JButton("select");
        selectSlot1 = new JButton("select");
        selectSlot2 = new JButton("select");
        selectSlot3 = new JButton("select");
        selectStand.setBounds(100, 600, 140, 50);
        selectSlot1.setBounds(250, 600, 140, 50);
        selectSlot2.setBounds(400, 600, 140, 50);
        selectSlot3.setBounds(550, 600, 140, 50);
        back.setBounds(650, 660, 100, 50);
        this.add(selectStand);
        this.add(selectSlot1);
        this.add(selectSlot2);
        this.add(selectSlot3);
        this.add(back);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        g.drawString("Select a production power to activate", 300, 50);

        g.drawRect(50, 400, 200, 300);
        g.drawRect(280, 400, 200, 300);
        g.drawRect(510, 400, 200, 300);
        g.drawRect(740, 400, 200, 300);

        myDrawImagePNG(g);

    }

    private void myDrawImagePNG(Graphics g) {
        prodCardSlot = Data.instanceCreator().getPersonalBoard().getProdCardSlot();
        ProductionCard[] topCard = prodCardSlot.getTopCards();
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url;
        BufferedImage img;
        int[] type = new int[3];
        int[] level = new int[3];
        int[] winPoints = new int[3];
        Resource[] reqRes1 = topCard[0].getRequiredRes();
        Resource[] reqRes2 = topCard[1].getRequiredRes();
        Resource[] reqRes3 = topCard[2].getRequiredRes();
        int[] requiredResType1 = ResourceCounter.resCount(reqRes1);
        int[] requiredResType2 = ResourceCounter.resCount(reqRes2);
        int[] requiredResType3 = ResourceCounter.resCount(reqRes3);
        Resource[] givenRes1 = topCard[0].getGivenRes();
        Resource[] givenRes2 = topCard[1].getGivenRes();
        Resource[] givenRes3 = topCard[2].getGivenRes();
        int[] totGivenRes1 = ResourceCounter.resCount(givenRes1);
        int[] totGivenRes2 = ResourceCounter.resCount(givenRes2);
        int[] totGivenRes3 = ResourceCounter.resCount(givenRes3);
        int x = 0;
        int y = 0;
        int j = 0;
        int k = 0;
        int r = 0;
        int p = 0;
        int q = 0;
        for (int i = 0; i < 3; i++) {
            level[i] = topCard[i].getLevel();
        }
        for (int i = 0; i < 3; i++) {
            type[i] = topCard[i].getType();
        }
        for (int i = 0; i<3; i++) {
            winPoints[i] = topCard[i].getWp();
        }
        for (int i = 0; i < 3; i++) {
            switch (type[i]) {
                case 1:
                    url = cl.getResourceAsStream("type 1.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280 + x, 420, null);
                    x = x + 230;
                    break;
                case 2:
                    url = cl.getResourceAsStream("type 2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280 + x, 420, null);
                    x = x + 230;
                    break;
                case 3:
                    url = cl.getResourceAsStream("type 3.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280 + x, 420, null);
                    x = x + 230;
                    break;
                case 4:
                    url = cl.getResourceAsStream("type 4.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280 + x, 420, null);
                    x = x + 230;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < 3; i++) {
            g.drawString("Level:" + level[i], 280 + y, 450);
            y = y + 230;
        }

        for (int i = 0; i < 3; i++) {
            g.drawString("Win Points:" + winPoints[i], 280 + j, 470);
            j = j + 230;
        }
        for (int i = 0; i < 3; i++) {
            g.drawString("Required:", 280 + k, 490);
            k = k + 230;
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280, 500 + r, null);
                    g.drawString("x" + requiredResType1[i], 280, 510 + r);
                    r = r + 20;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280, 500 + r, null);
                    g.drawString("x" + requiredResType1[i], 280, 510 + r);
                    r = r + 20;
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280, 500 + r, null);
                    g.drawString("x" + requiredResType1[i], 280, 510 + r);
                    r = r +20;
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280, 500 + r, null);
                    g.drawString("x" + requiredResType1[i], 280, 510 + r);
                    r = r + 20;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 510, 500 + r, null);
                    g.drawString("x" + requiredResType2[i], 300, 510 + p);
                    p = p + 20;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 510, 500 + r, null);
                    g.drawString("x" + requiredResType2[i], 300, 510 + p);
                    p = p +20;
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 510, 500 + r, null);
                    g.drawString("x" + requiredResType2[i], 300, 510 + p);
                    p = p + 20;
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 510, 500 + r, null);
                    g.drawString("x" + requiredResType2[i], 300, 510 + p);
                    p = p + 20;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + requiredResType3[i], 320, 510 + q);
                    q = q + 20;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + requiredResType3[i], 320, 510 + q);
                    q = q + 20;
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + requiredResType3[i], 320, 510 + q);
                    q = q + 20;
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + requiredResType3[i], 320, 510 + q);
                    q = q + 20;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < 3; i++) {
            g.drawString("Given:", 280 + k, 580);
            k = k + 230;
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes1[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + givenRes1[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes1[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes1[i], 320, 590 + q);
                    q = q + 20;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes2[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" +  totGivenRes2[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" +  totGivenRes2[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" +  totGivenRes2[i], 320, 590 + q);
                    q = q + 20;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < 4; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" +  totGivenRes3[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes3[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes3[i], 320, 590 + q);
                    q = q + 20;
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 740, 500 + r, null);
                    g.drawString("x" + totGivenRes3[i], 320, 590 + q);
                    q = q + 20;
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == selectStand){
            try{
                handler.sendMessageToServer("standard production", 169);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == selectSlot1){
            try{
                handler.sendMessageToServer("card production 1", 170);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == selectSlot2){
            try{
                handler.sendMessageToServer("card production 2", 170);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == selectSlot3){
            try{
                handler.sendMessageToServer("card production 3", 170);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == back){
            MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
        }


    }
}
