package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Local extends JPanel implements ActionListener {

    private final JButton startGame;
    private final JButton goBack;

    public Local(){
        startGame = new JButton("Start Game");
        goBack = new JButton("Go Back");
        startGame.setBounds(50, 300, 200, 50);
        goBack.setBounds(400, 300, 200, 50);
        startGame.addActionListener(this);
        goBack.addActionListener(this);
        this.add(startGame);
        this.add(goBack);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);


    }

    public void paint(Graphics g){
        g.drawString("You chose to play local", 100, 50);
        g.drawString("Your enemy is Lorenzo il Magnifico", 100, 100);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGame){

        } else if (e.getSource() == goBack){
            Main.frame.remove(this);
            Main.frame.add(new Intro());
            Main.frame.revalidate();
        }
    }
}
