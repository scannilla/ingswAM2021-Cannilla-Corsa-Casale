package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepotSelectionLocal extends JPanel implements ActionListener {

    private final JButton depot;
    private final JButton strongbox;
    private final JButton extraDepot;
    private final SPClientMessageHandler cmHandler;

    public DepotSelectionLocal(SPClientMessageHandler cmHandler, int code){
        JButton discard = new JButton("discard");
        discard.setBounds(550, 500, 100, 50);
        discard.addActionListener(this);
        if (code == 2){
            this.add(discard);
        }
        this.cmHandler = cmHandler;
        depot = new JButton("WareHouse Depot");
        depot.setBounds(250, 500, 100, 50);
        depot.addActionListener(this);
        strongbox = new JButton("Strongbox");
        strongbox.setBounds(350, 500, 100, 50);
        strongbox.addActionListener(this);
        extraDepot = new JButton("Extra Depot");
        extraDepot.setBounds(450,500, 100, 50);
        extraDepot.addActionListener(this);
    }

    public void paint(Graphics g){
        g.drawString("Select where you want insert this resource", 300, 200);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depot){
            try {
                cmHandler.sendMessageToServer("depot");
            } catch (EndingGameException endingGameException) {
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if (e.getSource() == strongbox){
            try {
                cmHandler.sendMessageToServer("strongbox");
            } catch (EndingGameException endingGameException) {
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if(e.getSource() == extraDepot){
            try {
                cmHandler.sendMessageToServer("extradepot");
            } catch (EndingGameException endingGameException) {
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        }
    }
}
