package it.polimi.ingsw.gui.multi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;

public class WaitingRoom extends JPanel {

private final ClientMessageHandler handler;


    public WaitingRoom(ClientMessageHandler handler){
        this.handler = handler;
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);

    }

    public void paint(Graphics g){
            g.drawString("Waiting for other players", 350, 100);
    }

    }

