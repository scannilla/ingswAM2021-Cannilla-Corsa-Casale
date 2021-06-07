package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalBoard extends JPanel implements ActionListener {

    public PersonalBoard(){

    }

    public void paint(Graphics g){
        for(int x = 50; x<1200; x = x + 50){ //percorso fede
            g.drawRect(x, 100, 50, 50);
        }
        g.drawRect(50, 600, 200, 100); //strongbox
        for (int j = 300; j<600; j = j + 150){ //prodcard slot
            g.drawRect(j, 600, 100, 200);
        }

    }







    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
