package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActiveProduction extends JPanel implements ActionListener{

    private static JButton selectStand, selectSlot1, selectSlot2, selectSlot3;

    public ActiveProduction(){
        selectStand = new JButton("select");
        selectSlot1 = new JButton("select");
        selectSlot2 = new JButton("select");
        selectSlot3 = new JButton("select");
        selectStand.setBounds(100, 600, 200, 200);

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

    }
}
