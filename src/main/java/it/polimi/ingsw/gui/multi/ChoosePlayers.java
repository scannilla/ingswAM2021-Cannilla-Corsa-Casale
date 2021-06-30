package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePlayers extends JPanel implements ActionListener {

    private final ClientMessageHandler cmHandler;
    private final JButton choosePlayers;

    public ChoosePlayers(ClientMessageHandler cmHandler){
        this.cmHandler = cmHandler;
        choosePlayers = new JButton("Choose Players");
        choosePlayers.setBounds(350, 400, 100, 100);
        choosePlayers.addActionListener(this);
        this.add(choosePlayers);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choosePlayers){
            try{
                cmHandler.sendMessageToServer("choose players");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        MainGUI.changePanel(new Multi(cmHandler));
        }
    }
}
