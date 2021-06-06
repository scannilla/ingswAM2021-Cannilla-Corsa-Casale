package it.polimi.ingsw.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Intro extends JPanel implements ActionListener {

    private final JButton local;
    private final JButton multi;
    public Intro(){
        local = new JButton("Play Local");
        multi = new JButton("Play Multi");
        local.setBounds(50, 300, 100, 50);
        multi.setBounds(260, 300, 100, 50);
        multi.addActionListener(this);
        local.addActionListener(this);
        this.add(local);
        this.add(multi);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);

    }

    public void paint(Graphics g){
        g.drawString("Welcome to Master of Renaissance", 100, 50);
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g){
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("homepage.jpeg");
        BufferedImage img;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 100,100, 200,200, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == multi) {
            Main.frame.remove(this);
            Main.frame.add(new Multi());
            Main.frame.revalidate();
        } else if (e.getSource() == local){
            Main.frame.remove(this);
            Main.frame.add(new Local());
            Main.frame.revalidate();
        }
    }
}

