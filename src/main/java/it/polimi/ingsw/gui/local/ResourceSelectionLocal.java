package it.polimi.ingsw.gui.local;



import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceSelectionLocal extends JPanel implements ActionListener {

    private final JButton coin, stone, servant, shield;
    private final SPClientMessageHandler cmHandler;

    public ResourceSelectionLocal(SPClientMessageHandler cmHandler){
        this.cmHandler = cmHandler;
        coin = new JButton("Select");
        stone = new JButton("Select");
        servant = new JButton("Select");
        shield = new JButton("Select");
        coin.setBounds(250, 600, 100, 50);
        stone.setBounds(350, 600, 100, 50);
        servant.setBounds(450, 600, 100, 50);
        shield.setBounds(550, 600, 100, 50);
        coin.addActionListener(this);
        stone.addActionListener(this);
        servant.addActionListener(this);
        shield.addActionListener(this);
        this.add(coin);
        this.add(stone);
        this.add(servant);
        this.add(shield);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
    }

   public void myDrawPNG(Graphics g){
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
            g.drawImage(imgCoin, 250, 400, 100, 100, null);
            g.drawImage(imgStone, 350, 400, 100, 100, null);
            g.drawImage(imgServant, 450, 400, 100, 100, null);
            g.drawImage(imgShield, 550, 400, 100, 100, null);
        }

        public void paint(Graphics g){
        g.drawString("Select Resource", 300, 300);
        myDrawPNG(g);
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == coin){
            try{
                cmHandler.sendMessageToServer("coin");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if(e.getSource() == stone){
            try{
                cmHandler.sendMessageToServer("stone");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if(e.getSource() == servant){
            try{
                cmHandler.sendMessageToServer("servant");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if(e.getSource() == shield){
            try{
                cmHandler.sendMessageToServer("coin");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        }
    }
}

