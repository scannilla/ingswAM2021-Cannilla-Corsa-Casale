package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setup extends JPanel implements ActionListener {

    private final ClientMessageHandler cmHandler;
    private final JButton setup;

    public Setup(ClientMessageHandler cmHandler){
        this.cmHandler = cmHandler;
        setup = new JButton("Setup Game");
        setup.setBounds(350, 400, 100, 100);
        setup.addActionListener(this);
        this.add(setup);
        this.setSize(800, 800);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == setup){
            try{
                cmHandler.sendMessageToServer("setup game");
            } catch(EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        }
    }

}
