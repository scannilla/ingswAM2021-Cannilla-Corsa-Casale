package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyMarble extends JPanel implements ActionListener {

    private final JButton column1, column2, column3, column4, line1, line2, line3, back;

    public BuyMarble(){
        column1 = new JButton("Buy");
        column2 = new JButton("Buy");
        column3 = new JButton("Buy");
        column4 = new JButton("Buy");
        line1 = new JButton("Buy");
        line2 = new JButton("Buy");
        line3 = new JButton("Buy");
        back = new JButton("Go Back");
        column1.setBounds(100, 400, 100, 100);
        column2.setBounds(200, 400, 100, 100);
        column3.setBounds(300, 400, 100, 100);
        column4.setBounds(400, 400, 100, 100);
        line1.setBounds(500, 100, 100, 50);
        line2.setBounds(500, 200, 100, 50);
        line3.setBounds(500, 300, 100, 50);
        back.setBounds(650, 650, 100, 50);
        line1.addActionListener(this);
        line2.addActionListener(this);
        line3.addActionListener(this);
        column1.addActionListener(this);
        column2.addActionListener(this);
        column3.addActionListener(this);
        column4.addActionListener(this);
        back.addActionListener(this);
        this.add(line1);
        this.add(line2);
        this.add(line3);
        this.add(column1);
        this.add(column2);
        this.add(column3);
        this.add(column4);
        this.add(back);
        this.setSize(800, 800);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        for (int x = 100; x<400; x = x + 100){ //market marble
            for (int j = 100; j < 300; j = j + 100){
                g.drawRect(x, j, 100, 100);
            }
        }
    g.drawRect(550, 100, 100, 100); //out marble
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == line1){
            //buymarblefrommarket(line1, line)
        } else if(e.getSource() == line2){
            //buymarblefrommarket(line2, line)
        } else if (e.getSource() == line3){
            //buyfrommarket(line3, line)
        } else if (e.getSource() == column1){
            //buyfrommarket(column1, column)
        } else if (e.getSource() == column2){
            //buyfrommarket(column2, column)
        } else if(e.getSource() == column3){
            //buyfrommarket(column3, column)
        } else if(e.getSource() == column4){
            //buyfrommarket(column4, column)
        } else if(e.getSource() == back){
            Main.frame.remove(this);
            Main.frame.add(new Turn());
            Main.frame.revalidate();
        }
    }
}
