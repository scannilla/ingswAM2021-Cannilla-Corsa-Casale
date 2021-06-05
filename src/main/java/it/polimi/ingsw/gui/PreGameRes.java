package it.polimi.ingsw.gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PreGameRes extends JPanel implements ActionListener {


    private JButton coinButton, stoneButton, servantButton, shieldButton;

public PreGameRes(){
    int player = 0;
    if(player != 1) {
            coinButton = new JButton("Select");
            stoneButton = new JButton("Select");
            servantButton = new JButton("Select");
            shieldButton = new JButton("Select");
            coinButton.setBounds(100, 550, 100, 50);
            stoneButton.setBounds(250, 550, 100, 50);
            servantButton.setBounds(400, 550, 100, 50);
            shieldButton.setBounds(550, 550, 100, 50);
            coinButton.addActionListener(this);
            stoneButton.addActionListener(this);
            servantButton.addActionListener(this);
            shieldButton.addActionListener(this);
            this.add(coinButton);
            this.add(stoneButton);
            this.add(servantButton);
            this.add(shieldButton);
            this.setLayout(null);
            this.setSize(800, 800);
            this.setVisible(true);
            this.setBackground(Color.WHITE);
    }
}


    public void paint(Graphics g){
        int player=0;
        switch(player) {
            case 1:
                g.drawString("You are the first player", 100, 50);
                g.drawString("Please wait for other players", 100, 100);
            case 2:
                g.drawString("You are the second player", 100, 50);
                g.drawString("You can chose one resource", 100, 100);
                myDrawImagePNG(g);
            case 3:
                g.drawString("You are the third player", 100, 50);
                g.drawString("You can chose one resource and your receive one faith point", 100, 100);
                myDrawImagePNG(g);
            case 4:
                g.drawString("You are the fourth player", 100, 50);
                g.drawString("You can chose two resources and your receive one faith point", 100, 100);
                myDrawImagePNG(g);


        }

    }

    private void myDrawImagePNG(Graphics g){
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
        g.drawImage(imgCoin, 100,500, 100,100, null);
        g.drawImage(imgStone, 250,500, 100,100, null);
        g.drawImage(imgServant, 400,500, 100,100, null);
        g.drawImage(imgShield, 550,500, 100,100, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
