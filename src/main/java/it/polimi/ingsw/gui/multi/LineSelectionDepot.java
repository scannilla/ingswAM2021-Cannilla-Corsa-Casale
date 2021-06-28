package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineSelectionDepot extends JPanel implements ActionListener {

    private final JButton one, two, three;
    private final ClientMessageHandler cmHandler;

    public LineSelectionDepot(ClientMessageHandler cmHandler){
        this.cmHandler = cmHandler;
        one = new JButton("Line 1");
        two = new JButton("Line 2");
        three = new JButton("Line 3");
        one.setBounds(350, 500, 100, 50);
        two.setBounds(450, 500, 100, 50);
        three.setBounds(550, 500, 100 ,50);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        this.add(one);
        this.add(two);
        this.add(three);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.white);

    }

    public void paint(Graphics g){
        g.drawString("Select the line of your Warehouse Depot", 300, 300);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == one){
            try{
                cmHandler.sendMessageToServer("1");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if (e.getSource() == two){
            try{
                cmHandler.sendMessageToServer("2");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if (e.getSource() == three){
            try{
                cmHandler.sendMessageToServer("1");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        }
    }
}
