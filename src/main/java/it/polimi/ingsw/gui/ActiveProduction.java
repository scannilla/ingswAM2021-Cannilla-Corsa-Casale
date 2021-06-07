package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActiveProduction extends JPanel implements ActionListener{

    private final JButton selectStand, selectSlot1, selectSlot2, selectSlot3, back;

    public ActiveProduction(){
        back = new JButton("Go Back");
        selectStand = new JButton("select");
        selectSlot1 = new JButton("select");
        selectSlot2 = new JButton("select");
        selectSlot3 = new JButton("select");
        selectStand.setBounds(100, 600, 140, 50);
        selectSlot1.setBounds(250, 600, 140, 50);
        selectSlot2.setBounds(400, 600, 140, 50);
        selectSlot3.setBounds(550, 600, 140, 50);
        back.setBounds(650, 660, 100, 50);
        this.add(selectStand);
        this.add(selectSlot1);
        this.add(selectSlot2);
        this.add(selectSlot3);
        this.add(back);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        g.drawString("Select a production power to activate", 300, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == selectStand){
            //active standard production
        } else if(e.getSource() == selectSlot1){
            //active production slot 1
        } else if(e.getSource() == selectSlot2){
            //active production slot 2
        } else if(e.getSource() == selectSlot3){
            //active production slot 3
        } else if(e.getSource() == back){
            Main.frame.remove(this);
            Main.frame.add(new Turn());
            Main.frame.revalidate();
        }


    }
}
