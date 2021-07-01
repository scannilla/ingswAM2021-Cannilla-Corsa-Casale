package it.polimi.ingsw.gui.multi;


import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
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

public class BuyProductionCard extends JPanel implements ActionListener {

    private ProductionCardsMarket productionCardsMarket;
    private final JButton buy1, buy2, buy3, buy4, buy5, buy6, buy7, buy8, buy9, buy10, buy11, buy12, back;
    private final ClientMessageHandler handler;
    public BuyProductionCard(ClientMessageHandler handler){
        this.handler = handler;
        buy1 = new JButton("Buy");
        buy2 = new JButton("Buy");
        buy3 = new JButton("Buy");
        buy4 = new JButton("Buy");
        buy5 = new JButton("Buy");
        buy6 = new JButton("Buy");
        buy7 = new JButton("Buy");
        buy8 = new JButton("Buy");
        buy9 = new JButton("Buy");
        buy10 = new JButton("Buy");
        buy11 = new JButton("Buy");
        buy12 = new JButton("Buy");
        back = new JButton("Go Back");
        buy1.setBounds(50, 50, 100, 50);
        buy2.setBounds(200, 50, 100, 50);
        buy3.setBounds(350, 50, 100, 50);
        buy4.setBounds(50, 200, 100, 50);
        buy5.setBounds(200, 200, 100, 50);
        buy6.setBounds(350, 200, 100, 50);
        buy7.setBounds(50, 350, 100, 50);
        buy8.setBounds(200, 350, 100, 50);
        buy9.setBounds(350, 350, 100, 50);
        buy10.setBounds(350, 500, 100, 50);
        buy11.setBounds(350, 500, 100, 50);
        buy12.setBounds(350, 500, 100, 50);
        back.setBounds(750, 50, 100, 50);
        buy1.addActionListener(this);
        buy2.addActionListener(this);
        buy3.addActionListener(this);
        buy4.addActionListener(this);
        buy5.addActionListener(this);
        buy6.addActionListener(this);
        buy7.addActionListener(this);
        buy8.addActionListener(this);
        buy9.addActionListener(this);
        buy10.addActionListener(this);
        buy11.addActionListener(this);
        buy12.addActionListener(this);
        back.addActionListener(this);
        this.add(buy1);
        this.add(buy2);
        this.add(buy3);
        this.add(buy4);
        this.add(buy5);
        this.add(buy6);
        this.add(buy7);
        this.add(buy8);
        this.add(buy9);
        this.add(buy10);
        this.add(buy11);
        this.add(buy12);
        this.add(back);
        this.setLayout(null);
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
        for (int i = 0; i<12; i++) {
            for (int k = 0; k<3; k++) {
                if (topCard[i][k] != null && topCard[i][k].getCostArray() != null) {
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
                    url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 1.png");
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
                    url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 2.png");
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
                    url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 3.png");
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
                    url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 4.png");
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

        for (int i = 0; i < 9; i++) {
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
        if (e.getSource() == buy1){
            try{
                handler.sendMessageToServer("buy production card -1 -1", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == buy2){
            try{
                handler.sendMessageToServer("buy production card -1 -2", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy3){
            try{
                handler.sendMessageToServer("buy production card -1 -3", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy4){
            try{
                handler.sendMessageToServer("buy production card -2 -1", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy5){
            try{
                handler.sendMessageToServer("buy production card -2 -2", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy6){
            try{
                handler.sendMessageToServer("buy production card -2 -3", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy7){
            try{
                handler.sendMessageToServer("buy production card -3 -1", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy8){
            try{
                handler.sendMessageToServer("buy production card -3 -2", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }  else if(e.getSource() == buy9){
            try{
                handler.sendMessageToServer("buy production card -3 -3", 167);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if (e.getSource() == back){
            MainGUI.changePanel(new Turn(handler));
        }
    }

    private void drawCostArray(Graphics g, int[][] costArrays) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url;
        BufferedImage img = null;
        int x = 0,y = 0;
        for (int i = 0; i < 12; i++) {
            for(int j = 0; j< 4; j++) {
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
