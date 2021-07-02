package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.production.ProductionCardsMarket;
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

public class ShowProductionMarket extends JPanel implements ActionListener {

    private ProductionCardsMarket productionCardsMarket;
    private boolean fromTurn;
    private final JButton back;
    private final ClientMessageHandler handler;

    public ShowProductionMarket(ClientMessageHandler handler, boolean fromTurn){
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.setBounds(650, 650, 100, 50);
        back.addActionListener(this);
        this.add(back);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        productionCardsMarket = Data.instanceCreator().getProductionCardsMarket();
        for(int x = 50; x < 400; x = x + 150){
            for (int y = 50; y < 500; y = y + 150){
                g.drawRect(x, y, 100, 150);
            }
        }
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g){
        ProductionCard[][] topCard = productionCardsMarket.getTopCards();
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url;
        BufferedImage img;
        int[] type = new int[12];
        int[] level = new int[12];
        int[] winPoints = new int[12];
        int x = 0;
        int y = 0;
        Resource[][] costResource = new Resource[12][];
        for (int i = 0; i<4; i++) {
            for (int k = 0; k<3; k++) {
                if (topCard[i][k] != null && topCard[i][k].getCostArray()[0] != null) {
                    costResource[i] = new Resource[topCard[i][k].getCostArray().length];
                    for (int j = 0; j < topCard[i][k].getCostArray().length; j++) {
                        costResource[i][j] = topCard[i][k].getCostArray()[j];
                    }
                }
            }
        }
        int[][] costTotArray = new int[12][4];
        for (int i = 0; i < 12; i++) {
            if (costResource[i] != null) {
                for (int j = 0; j < 3; j++) {
                    costTotArray[i][j] = ResourceCounter.resCount(costResource[i])[j];
                }
            }
        }
        int[][] requiredRes = new int[12][4];
        for (int i = 0; i<12; i++) {
            for (int k = 0; k<3; k++) {
                if (topCard[i][k] != null && topCard[i][k].getRequiredRes() != null) {
                    for (int j = 0; j < topCard[i][k].getRequiredRes().length; j++) {
                        requiredRes[i][j] = ResourceCounter.resCount(topCard[i][k].getRequiredRes())[j];
                    }
                }
            }
        }

        int[][] givenRes = new int[12][4];
        for (int i = 0; i<12; i++) {
            for (int k = 0; k<3; k++) {
                if (topCard[i][k] != null && topCard[i][k].getGivenRes() != null) {
                    for (int j = 0; j < topCard[i][k].getGivenRes().length; j++) {
                        givenRes[i][j] = ResourceCounter.resCount(topCard[i][k].getRequiredRes())[j];
                    }
                }
            }
        }
        for (int i = 0; i<12; i++) {
            for (int k = 0; k<3; k++) {
                if (topCard[i][k] != null && topCard[i][k] != null) {
                    type[i] = topCard[i][k].getType();
                    i++;
                }
            }
            i--;
        }

        for (int i = 0; i < 12; i++) {
            switch (type[i]) {
                case 1:
                    url = cl.getResourceAsStream("type 1.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x, 420 + y, null);
                    x = x + 150;
                    break;
                case 2:
                    url = cl.getResourceAsStream("type 2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55 + x, 420 + y, null);
                    x = x + 150;
                    break;
                case 3:
                    url = cl.getResourceAsStream("type 3.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280 + x, 420 + y, null);
                    x = x + 150;
                    break;
                case 4:
                    url = cl.getResourceAsStream("type 4.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 280 + x, 420 + y, null);
                    x = x + 150;
                    break;
                default:
                    break;
            }
            if (i%3 == 0 && i !=0){
                y = y + 150;
                x = 0;
            }
        }
        for (int i = 0; i < 12; i++) {
            for(int k = 0; k<3; k++) {
                if (topCard[i][k] != null && topCard[i][k] != null) {
                    level[i] = topCard[i][k].getLevel();
                }
            }
        }

        drawCostArray(g, costTotArray);

        for (int i = 0; i < 12; i++) {
            g.drawString("Level:" + level[i], 55 + x, 440 + y);
            x += 150;
            if(i%3==0 && i!=0) {
                x = 0;
                y = y + 150;
            }

        }



        drawRequiredRes(g, requiredRes);

        for (int i = 0; i < 12; i++) {
            for(int k = 0; k<3; k++) {
                if(topCard[i][k] != null && topCard[i][k] != null) {
                    winPoints[i] = topCard[i][k].getWp();
                    i++;
                }
                i--;
            }
        }

        for (int i = 0; i < 12; i++) {
            g.drawString("Win Points:" + winPoints[i], 55 + x, 450 + y);
            x += 150;
            if(i%3==0 && i!=0) {
                x = 0;
                y = y + 150;
            }
        }

        drawGivenRes(g, givenRes);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back && fromTurn){
            MainGUI.changePanel(new Turn(handler));
        } else if (e.getSource() == back && !fromTurn){
            MainGUI.changePanel(new WaitingTurn(handler));
        }
    }
    private void drawCostArray(Graphics g, int[][] costArrays) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url;
        BufferedImage img = null;
        int x = 0,y = 0;
        for (int i = 0; i < 12; i++) {
            for(int j = 0; j<4; j++) {
                switch (j){
                    case 0:
                        url = cl.getResourceAsStream("coin2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        break;
                    case 1:
                        url = cl.getResourceAsStream("stone2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        break;
                    case 2:
                        url = cl.getResourceAsStream("servant2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        break;
                    case 3:
                        url = cl.getResourceAsStream("shield2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        break;
                    default:
                        break;
                }
                g.drawImage(img, 55 + x, 440 + y, null);
                g.drawString("x" + costArrays[i][j], 55 + x, 460 + y);
                x += 150;
                if(i%3==0 && i!=0) {
                    x = 0;
                    y += 150;
                }
            }
        }
    }

    private void drawRequiredRes(Graphics g, int[][] requiredRes) {
        int x = 0;
        int y = 0;
        ClassLoader cl = this.getClass().getClassLoader();
        BufferedImage img;
        InputStream url;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        url = cl.getResourceAsStream("coin2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 55 + x, 440 + y, null);
                        g.drawString("x" + requiredRes[i][j], 55 + x + 20, 500 + y);
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
                        g.drawString("x" + requiredRes[i][j], 55 + x + 20, 500 + y);
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
                        g.drawString("x" + requiredRes[i][j], 55 + x + 20 , 500 + y);
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
                        g.drawString("x" + requiredRes[i][j], 55 + x + 20, 500 + y);
                        break;
                    default:
                        break;
                }
            }
            if(i%3==0 && i!=0) {
                x = 0;
                y = y + 150;
            }
        }
    }
    private void drawGivenRes(Graphics g, int[][] givenRes){
        int x = 0;
        int y = 0;
        ClassLoader cl = this.getClass().getClassLoader();
        BufferedImage img;
        InputStream url;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        url = cl.getResourceAsStream("coin2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 55 + x, 440 + y, null);
                        g.drawString("x" + givenRes[i][j], 55 + x + 20, 540 + y);
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
                        g.drawString("x" + givenRes[i][j], 55 + x + 20, 540 + y);
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
                        g.drawString("x" + givenRes[i][j], 55 + x + 20 , 540 + y);
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
                        g.drawString("x" + givenRes[i][j], 55 + x + 20, 540 + y);
                        break;
                    default:
                        break;
                }
            }
            if(i%3==0 && i!=0) {
                x = 0;
                y = y + 150;
            }
        }
    }
}
