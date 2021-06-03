package it.polimi.ingsw.gui;

import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Local extends JFrame implements ActionListener{



    public Local(){
        JButton play = new JButton("Play");
        JButton goBack = new JButton("Go Back");
        JLabel label = new JLabel();
        play.setBounds(30, 300, 150, 50);
        goBack.setBounds(190, 300, 150, 50);
        label.add(play);
        label.add(goBack);
        this.add(label);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        play.addActionListener(this);
        goBack.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){


    }

}
