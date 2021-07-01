package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
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
import java.util.Arrays;

public class ActiveLeader extends JPanel implements ActionListener {

   private LeaderCard[] leaderCards;
    private final JButton active1, active2, back;
    private ClientMessageHandler handler;

    public ActiveLeader(ClientMessageHandler handler){
        this.handler = handler;
        active1 = new JButton("Active");
        active2 = new JButton("Active");
        back = new JButton("Go Back");
        active1.setBounds(200, 250, 100, 50);
        active2.setBounds(350, 250, 100, 50);
        back.setBounds(650, 650, 100, 50);
        active1.addActionListener(this);
        active2.addActionListener(this);
        back.addActionListener(this);
        this.add(active1);
        this.add(active2);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.WHITE);

    }

    public void paint(Graphics g){
        leaderCards = Data.instanceCreator().getLeaderCards();
        g.drawRect(100, 100, 300, 100);
        g.drawRect(400, 100, 300, 100);
        g.drawRect(100, 200, 300, 100);
        g.drawRect(400, 200, 300, 100);
        g.drawRect(100, 300, 300, 100);
        g.drawRect(400, 300, 300, 100);
        g.drawRect(100, 400, 300, 100);
        g.drawRect(400, 400, 300, 100);
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        int wp1 = leaderCards[0].getWp();
        int wp2 = leaderCards[1].getWp();
        String wpString1 = String.valueOf(wp1);
        String wpString2 = String.valueOf(wp2);
        int ability1 = leaderCards[0].getAbility();
        int ability2 = leaderCards[1].getAbility();
        Resource[] reqRes1 = leaderCards[0].getRequiredRes();
        Resource[] reqRes2 = leaderCards[1].getRequiredRes();
        int[] reqType1 = leaderCards[0].getRequiredType();
        int[] reqType2 = leaderCards[1].getRequiredType();
        int[] reqLevel1 = leaderCards[0].getRequiredLevel();
        int[] reqLevel2 = leaderCards[1].getRequiredLevel();
        int [] reqCards1 = new int [12];
        int [] reqCards2 = new int [12];

        if (reqType1!=null && reqLevel1!=null) {
            reqCards1 = requiredCardCounter(reqType1, reqLevel1);
        }
        if (reqType2!=null && reqLevel2!=null) {
            reqCards2 = requiredCardCounter(reqType2, reqLevel2);
        }
        InputStream url;
        BufferedImage img;
        int[] resourceReq1 = new int [4];
        int[] resourceReq2 = new int [4];
        String[] resString1 = new String[4];
        String[] resString2 = new String[4];
        if (reqRes1!=null) {
            resourceReq1 = ResourceCounter.resCount(reqRes1);
        }
        if (reqRes2!=null) {
            resourceReq2 = ResourceCounter.resCount(reqRes2);
        }
        if (resourceReq1!=null) {
            resString1 = Arrays.stream(resourceReq1)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
        }
        if (resourceReq2 != null) {
            resString2 = Arrays.stream(resourceReq2)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
        }
        Font f = new Font("Times New Roman", Font.BOLD, 16);
        g.setFont(f);


        //DRAWING REQUIRED CARDS
        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 1.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 125, 50, 50, 50, null);
            g.drawImage(img, 125, 100, 50, 50, null);
            g.drawImage(img, 125, 150, 50, 50, null);
            g.drawString("1", 125, 50);
            g.drawString("2", 125, 100);
            g.drawString("3", 125, 150);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 425, 50, 50, 50, null);
            g.drawImage(img, 425, 100, 50, 50, null);
            g.drawImage(img, 425, 150, 50, 50, null);
            g.drawString("1", 425, 50);
            g.drawString("2", 425, 100);
            g.drawString("3", 425, 150);
        }

        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }if (reqCards1!=null) {
            g.drawImage(img, 200, 50, 50, 50, null);
            g.drawImage(img, 200, 100, 50, 50, null);
            g.drawImage(img, 200, 150, 50, 50, null);
            g.drawString("1", 200, 50);
            g.drawString("2", 200, 100);
            g.drawString("3", 200, 150);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 500, 50, 50, 50, null);
            g.drawImage(img, 500, 100, 50, 50, null);
            g.drawImage(img, 500, 150, 50, 50, null);
            g.drawString("1", 500, 50);
            g.drawString("2", 500, 100);
            g.drawString("3", 500, 150);
        }

        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 3.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 275, 50, 50, 50, null);
            g.drawImage(img, 275, 100, 50, 50, null);
            g.drawImage(img, 275, 150, 50, 50, null);
            g.drawString("1", 275, 50);
            g.drawString("2", 275, 100);
            g.drawString("3", 275, 150);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 575, 50, 50, 50, null);
            g.drawImage(img, 575, 100, 50, 50, null);
            g.drawImage(img, 575, 150, 50, 50, null);
            g.drawString("1", 575, 50);
            g.drawString("2", 575, 100);
            g.drawString("3", 575, 150);
        }


        url = cl.getResourceAsStream("Cards/Leader/Requisiti Leader/Requisiti No Level/type 4.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 350, 50, 50, 50, null);
            g.drawImage(img, 350, 100, 50, 50, null);
            g.drawImage(img, 350, 150, 50, 50, null);
            g.drawString("1", 350, 50);
            g.drawString("2", 350, 100);
            g.drawString("3", 350, 150);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 650, 50, 50, 50, null);
            g.drawImage(img, 650, 100, 50, 50, null);
            g.drawImage(img, 650, 150, 50, 50, null);
            g.drawString("1", 650, 50);
            g.drawString("2", 650, 100);
            g.drawString("3", 650, 150);
        }

        if (reqCards1!=null) {
            g.drawString(String.valueOf(reqCards1[0]), 100, 65);
            g.drawString(String.valueOf(reqCards1[1]), 100, 115);
            g.drawString(String.valueOf(reqCards1[2]), 100, 165);
            g.drawString(String.valueOf(reqCards1[3]), 175, 65);
            g.drawString(String.valueOf(reqCards1[4]), 175, 115);
            g.drawString(String.valueOf(reqCards1[5]), 175, 165);
            g.drawString(String.valueOf(reqCards1[6]), 250, 65);
            g.drawString(String.valueOf(reqCards1[7]), 250, 115);
            g.drawString(String.valueOf(reqCards1[8]), 250, 165);
            g.drawString(String.valueOf(reqCards1[9]), 325, 65);
            g.drawString(String.valueOf(reqCards1[10]), 325, 115);
            g.drawString(String.valueOf(reqCards1[11]), 325, 165);
        }

        if (reqCards2!=null) {
            g.drawString(String.valueOf(reqCards2[0]), 400, 65);
            g.drawString(String.valueOf(reqCards2[1]), 400, 115);
            g.drawString(String.valueOf(reqCards2[2]), 400, 165);
            g.drawString(String.valueOf(reqCards2[3]), 475, 65);
            g.drawString(String.valueOf(reqCards2[4]), 475, 115);
            g.drawString(String.valueOf(reqCards2[5]), 475, 165);
            g.drawString(String.valueOf(reqCards2[6]), 550, 65);
            g.drawString(String.valueOf(reqCards2[7]), 550, 115);
            g.drawString(String.valueOf(reqCards2[8]), 550, 165);
            g.drawString(String.valueOf(reqCards2[9]), 625, 65);
            g.drawString(String.valueOf(reqCards2[10]), 625, 115);
            g.drawString(String.valueOf(reqCards2[11]), 625, 165);
        }


        //WIN POINTS DRAWING
        if (reqCards1!=null) {
            g.drawString(wpString1, 245, 340);
            g.drawString("WP: ", 150, 340);
        }
        if (reqCards2!=null) {
            g.drawString(wpString2, 545, 340);
            g.drawString("WP: ", 450, 340);
        }

        //REQUIRED RESOURCES DRAWING
        if (reqCards1!=null) {
            g.drawString(resString1[0], 110, 240);
            g.drawString(resString1[1], 185, 240);
            g.drawString(resString1[2], 260, 240);
            g.drawString(resString1[3], 335, 240);
        }
        if (reqCards2!=null) {
            g.drawString(resString2[0], 410, 240);
            g.drawString(resString2[1], 485, 240);
            g.drawString(resString2[2], 560, 240);
            g.drawString(resString2[3], 635, 240);
        }
        url = cl.getResourceAsStream("coin2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 125, 225, 50, 50, null);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 425, 225, 50, 50, null);
        }
        url = cl.getResourceAsStream("stone2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 200, 225, 50, 50, null);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 500, 225, 50, 50, null);
        }
        url = cl.getResourceAsStream("servant2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 275, 225, 50, 50, null);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 575, 225, 50, 50, null);
        }
        url = cl.getResourceAsStream("shield2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (reqCards1!=null) {
            g.drawImage(img, 350, 225, 50, 50, null);
        }
        if (reqCards2!=null) {
            g.drawImage(img, 650, 225, 50, 50, null);
        }


        //ABILITY DRAWING
        if (reqCards1!=null) {
        switch (switchRes(ability1, leaderCards[0])) {
            case 1:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
        }
        }
        if (reqCards2!=null) {
        switch (switchRes(ability2, leaderCards[1])) {
            case 1:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 2:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 3:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 4:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Conversions/conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 5:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 6:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 7:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 8:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Depots/depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 9:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 10:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 11:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 12:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Discounts/disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 13:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 14:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 15:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 16:
                url = cl.getResourceAsStream("Cards/Leader/Poteri Leader/Leader Of Productions/prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == active1){
            try{
                handler.sendMessageToServer("activate leader card -1", 168);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == active2){
            try{
                handler.sendMessageToServer("activate leader card -2", 168);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        } else if(e.getSource() == back){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
    }
}
