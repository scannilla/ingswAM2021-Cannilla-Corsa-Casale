package it.polimi.ingsw.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

    public class CustomFrameInit extends JFrame implements ActionListener {


        public CustomFrameInit(){

            JButton local = new JButton("Play Local");
            JButton multi = new JButton("Play Multiplayer");
            JLabel label = new JLabel();
            local.setBounds(30, 300, 150, 50);
            multi.setBounds(190, 300, 150, 50);
            label.add(local);
            label.add(multi);
            this.add(label);
            this.setSize(800, 800);
            this.setVisible(true);
            this.setLayout(null);
            local.addActionListener(this);

        }

        public void paint(Graphics g){
            String title = "Welcome to Master of Renaissance";
            //title.setFont(new Font("Welcome to Master of Renaissance", Font.PLAIN, 20));
            //g.drawString(, 100, 50);
            myDrawImagePNG(g);
        }


        private void myDrawImagePNG(Graphics g){
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream url = cl.getResourceAsStream("homepage.jpeg");
            BufferedImage img = null;
            try {
                img = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            g.drawImage(img, 100,100, 200,200, null);
        }

    public void actionPerformed(ActionEvent e){

        Local local = new Local();

    }


    }



