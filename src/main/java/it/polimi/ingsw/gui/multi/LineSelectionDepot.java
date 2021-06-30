package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.resources.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LineSelectionDepot extends JPanel implements ActionListener {

    private final JButton one, two, three;
    private final ClientMessageHandler cmHandler;
private PersonalBoard personalBoard;
    public LineSelectionDepot(ClientMessageHandler cmHandler){
        this.cmHandler = cmHandler;
        one = new JButton("Line 1");
        two = new JButton("Line 2");
        three = new JButton("Line 3");
        one.setBounds(350, 600, 100, 50);
        two.setBounds(450, 600, 100, 50);
        three.setBounds(550, 600, 100 ,50);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        this.add(one);
        this.add(two);
        this.add(three);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.white);

    }

    public void paint(Graphics g){
        g.drawString("Select the line of your Warehouse Depot", 300, 300);
        myDrawPNG(g);
    }

    public void myDrawPNG(Graphics g){
        personalBoard = Data.instanceCreator().getPersonalBoard();
        BufferedImage img;
        InputStream url;
        ClassLoader cl = this.getClass().getClassLoader();
        g.drawRect(55, 400, 50, 50);
        g.drawRect(30, 450, 50, 50);
        g.drawRect(80, 450, 50, 50);
        g.drawRect(5, 500, 50, 50);
        g.drawRect(55, 500, 50, 50);
        g.drawRect(105, 500, 50, 50);

        Resource[][] depot = personalBoard.getWarehouseDepot().getDepot();

        try {
            switch (depot[0][0].getResType()) {
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 52, 402, 40, 40, null);
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 52, 402, 40, 40, null);
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 52, 402, 40, 40, null);
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 52, 402, 40, 40, null);
                    break;
                default:
                    break;
            }
        } catch (NullPointerException ignored) {
        }
        int k = 0;
        try {
            for (int i = 0; i < 2; i++) {
                switch (depot[1][i].getResType()) {
                    case 0:
                        url = cl.getResourceAsStream("coin2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 32 + k, 452, 40, 40, null);
                        k += 50;
                        break;
                    case 1:
                        url = cl.getResourceAsStream("stone2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 32 + k, 452, 40, 40, null);
                        k += 50;
                        break;
                    case 2:
                        url = cl.getResourceAsStream("servant2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 32 + k, 452, 40, 40, null);
                        k += 50;
                        break;
                    case 3:
                        url = cl.getResourceAsStream("shield2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 32 + k, 452, 40, 40, null);
                        k += 50;
                        break;
                    default:
                        k += 50;
                        break;
                }
            }
        } catch (NullPointerException ignored) {
        }
        k = 0;
        try {
            for (int j = 0; j < 3; j++) {
                switch (depot[2][j].getResType()) {
                    case 0:
                        url = cl.getResourceAsStream("coin2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 7 + k, 502, 40, 40, null);
                        k += 50;
                        break;
                    case 1:
                        url = cl.getResourceAsStream("stone2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 7 + k, 502, 40, 40, null);
                        k += 50;
                        break;
                    case 2:
                        url = cl.getResourceAsStream("servant2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 7 + k, 502, 40, 40, null);
                        k += 50;
                        break;
                    case 3:
                        url = cl.getResourceAsStream("shield2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 7 + k, 502, 40, 40, null);
                        k += 50;
                        break;
                    default:
                        k += 50;
                        break;
                }
            }
        } catch (NullPointerException ignored) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == one){
            try{
                cmHandler.sendMessageToServer("1");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if (e.getSource() == two){
            try{
                cmHandler.sendMessageToServer("2");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if (e.getSource() == three){
            try{
                cmHandler.sendMessageToServer("1");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        }
    }
}
