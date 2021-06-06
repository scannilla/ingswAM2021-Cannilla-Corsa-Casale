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


    private JButton coinButton, stoneButton, servantButton, shieldButton, goAhead;

public PreGameRes(){
    int player = 3;
    if(player != 1) {
            coinButton = new JButton("Select");
            stoneButton = new JButton("Select");
            servantButton = new JButton("Select");
            shieldButton = new JButton("Select");
            goAhead = new JButton("Continue");
            coinButton.setBounds(100, 560, 100, 50);
            stoneButton.setBounds(250, 560, 100, 50);
            servantButton.setBounds(400, 560, 100, 50);
            shieldButton.setBounds(550, 560, 100, 50);
            goAhead.setBounds(350, 620, 100, 50);
            coinButton.addActionListener(this);
            stoneButton.addActionListener(this);
            servantButton.addActionListener(this);
            shieldButton.addActionListener(this);
            goAhead.addActionListener(this);
            this.add(goAhead);
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
        int player=3;
        switch(player) {
            case 1:
                g.drawString("You are the first player", 100, 50);
                g.drawString("Please wait for other players", 100, 100);
                break;
            case 2:
                g.drawString("You are the second player", 100, 50);
                g.drawString("You can chose one resource", 100, 200);
                myDrawImagePNG(g);
                break;
            case 3:
                g.drawString("You are the third player", 100, 50);
                g.drawString("You can chose one resource and you receive one faith point", 100, 100);
                myDrawImagePNG(g);
                break;
            case 4:
                g.drawString("You are the fourth player", 100, 50);
                g.drawString("You can chose two resources and you receive one faith point", 100, 100);
                myDrawImagePNG(g);
                break;

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
        g.drawImage(imgCoin, 100,450, 100,100, null);
        g.drawImage(imgStone, 250,450, 100,100, null);
        g.drawImage(imgServant, 400,450, 100,100, null);
        g.drawImage(imgShield, 550,450, 100,100, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (coinButton.equals(source)) {//add a coin resource
        } else if (stoneButton.equals(source)) {//add a stone resource
        } else if (servantButton.equals(source)) {//add a servant resource
        } else if (shieldButton.equals(source)) {//add a shield resource
        } else if (goAhead.equals(source)){
            Main.frame.remove(this);
            Main.frame.add(new PreGameLeader());
            Main.frame.revalidate();
        }
    }
}
