package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Multi extends JPanel implements ActionListener {

    private final JButton two;
    private final JButton three;
    private final JButton four;
    private final JButton back;

    public Multi(){
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        back = new JButton("Go Back");
        back. setBounds(590, 600, 200, 50);
        two.setBounds(200, 120, 50, 50);
        three.setBounds(250, 120, 50, 50);
        four.setBounds(300, 120, 50, 50);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(back);
        back.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        g.drawString("Insert number of player (2-4)", 200, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == back){
            Main.frame.remove(this);
            Main.frame.add(new Intro());
            Main.frame.revalidate();
        } else if (e.getSource() == two){
            Main.frame.remove(this);
            Main.frame.add(new WaitingRoom(2));
            Main.frame.revalidate();
        } else if (e.getSource() == three){
            Main.frame.remove(this);
            Main.frame.add(new WaitingRoom(3));
            Main.frame.revalidate();
        } else if (e.getSource() == four){
            Main.frame.remove(this);
            Main.frame.add(new WaitingRoom(4));
            Main.frame.revalidate();
        }


    }
}
