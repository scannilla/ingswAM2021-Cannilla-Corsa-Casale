package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActiveLeader extends JPanel implements ActionListener {

    private final JButton active1, active2, back;
    private ClientMessageHandler handler;

    public ActiveLeader(ClientMessageHandler handler){
        this.handler = handler;
        active1 = new JButton("Active");
        active2 = new JButton("Active");
        back = new JButton("Go Back");
        active1.setBounds(200, 250, 100, 50);
        active2.setBounds(350, 250, 100, 50);
        back.setBounds(650, 650, 100, 50);
        active1.addActionListener(this);
        active2.addActionListener(this);
        back.addActionListener(this);
        this.add(active1);
        this.add(active2);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.WHITE);

    }

    public void paint(Graphics g){
        g.drawRect(200, 200, 100, 200);
        g.drawRect(350, 200, 100, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == active1){
            try{
                handler.sendMessageToServer("activate leader card 1", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == active2){
            try{
                handler.sendMessageToServer("activate leader card 2", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == back){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
    }
}
