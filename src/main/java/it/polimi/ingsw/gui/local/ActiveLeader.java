package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.multi.Turn;
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
    private SPClientMessageHandler handler;

    public ActiveLeader(SPClientMessageHandler handler){
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
        InputStream url;
        BufferedImage img;
        int[] resourceReq1 = ResourceCounter.resCount(reqRes1);
        int[] resourceReq2 = ResourceCounter.resCount(reqRes2);
        String[] resString1 = Arrays.stream(resourceReq1)
                                    .mapToObj(String::valueOf)
                                    .toArray(String[]::new);
        String[] resString2 = Arrays.stream(resourceReq2)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
        Font f = new Font("Times New Roman", Font.BOLD, 16);
        g.setFont(f);
        g.drawString(wpString1, 245, 340);
        g.drawString("WP: ", 150, 340);
        g.drawString(wpString2, 545, 340);
        g.drawString("WP: ", 450, 340);
        g.drawString(resString1[0], 110, 150);
        g.drawString(resString1[1], 185, 150);
        g.drawString(resString1[2], 260, 150);
        g.drawString(resString1[3], 335, 150);
        g.drawString(resString2[0], 110, 450);
        g.drawString(resString2[1], 185, 450);
        g.drawString(resString2[2], 260, 450);
        g.drawString(resString2[3], 335, 450);
        url = cl.getResourceAsStream("coin2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 125, 125, 50, 50, null);
        url = cl.getResourceAsStream("stone2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 200, 125, 50, 50, null);
        url = cl.getResourceAsStream("servant2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 275, 125, 50, 50, null);
        url = cl.getResourceAsStream("shield2.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 350, 125, 50, 50, null);
        switch (switchRes(ability1, leaderCards[0])) {
            case 1: url = cl.getResourceAsStream("conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 2: url = cl.getResourceAsStream("conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 3: url = cl.getResourceAsStream("conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 4: url = cl.getResourceAsStream("conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 5: url = cl.getResourceAsStream("depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 6: url = cl.getResourceAsStream("depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 7: url = cl.getResourceAsStream("depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 8: url = cl.getResourceAsStream("depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 9: url = cl.getResourceAsStream("disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 10: url = cl.getResourceAsStream("disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 11: url = cl.getResourceAsStream("disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 12: url = cl.getResourceAsStream("disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 13: url = cl.getResourceAsStream("prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 14: url = cl.getResourceAsStream("prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 15: url = cl.getResourceAsStream("prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;
            case 16: url = cl.getResourceAsStream("prod shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 400, 400, 300, 100, null);
                break;

        }
        switch (switchRes(ability2, leaderCards[1])) {
            case 1: url = cl.getResourceAsStream("conv coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 2: url = cl.getResourceAsStream("conv stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 3: url = cl.getResourceAsStream("conv servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 4: url = cl.getResourceAsStream("conv shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 5: url = cl.getResourceAsStream("depot coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 6: url = cl.getResourceAsStream("depot stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 7: url = cl.getResourceAsStream("depot servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 8: url = cl.getResourceAsStream("depot shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 9: url = cl.getResourceAsStream("disc coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 10: url = cl.getResourceAsStream("disc stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 11: url = cl.getResourceAsStream("disc servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 12: url = cl.getResourceAsStream("disc shield.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 13: url = cl.getResourceAsStream("prod coin.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 14: url = cl.getResourceAsStream("prod stone.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 15: url = cl.getResourceAsStream("prod servant.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 100, 400, 300, 100, null);
                break;
            case 16: url = cl.getResourceAsStream("prod shield.png");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == active1){
            try{
                handler.sendMessageToServer("activate leader card -1", 168);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == active2){
            try{
                handler.sendMessageToServer("activate leader card -2", 168);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == back){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
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
}
