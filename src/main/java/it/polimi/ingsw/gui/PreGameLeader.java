package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreGameLeader extends JPanel implements ActionListener {

    private JButton leaderOne, leaderTwo, leaderThree, leaderFour, goAhead;

   public PreGameLeader(){
       leaderOne = new JButton("Select");
       leaderTwo = new JButton("Select");
       leaderThree = new JButton("Select");
       leaderFour = new JButton("Select");
       goAhead = new JButton("Continue");
       leaderOne.setBounds(100, 550, 100, 50);
       leaderTwo.setBounds(250, 550, 100, 50);
       leaderThree.setBounds(400, 550, 100, 50);
       leaderFour.setBounds(550, 550, 100, 50);
       goAhead.setBounds(750, 750, 50, 50);
       leaderOne.addActionListener(this);
       leaderTwo.addActionListener(this);
       leaderThree.addActionListener(this);
       leaderFour.addActionListener(this);
       goAhead.addActionListener(this);
       this.add(leaderOne);
       this.add(leaderTwo);
       this.add(leaderThree);
       this.add(leaderFour);
       this.add(goAhead);
       this.setLayout(null);
       this.setSize(800, 800);
       this.setVisible(true);
       this.setBackground(Color.WHITE);

   }

   public void paint(Graphics g){
       g.drawString("Choose two of the four leader cards available", 200,200);
   }

   private void myDrawImagePNG(Graphics g){

   }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==goAhead){
            Main.frame.remove(this);
            Main.frame.add(new WaitingTurn());
            Main.frame.revalidate();
        }
    }
}