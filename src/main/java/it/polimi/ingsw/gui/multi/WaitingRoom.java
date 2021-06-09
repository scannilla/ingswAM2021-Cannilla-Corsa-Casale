package it.polimi.ingsw.gui.multi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.MainGUI;

public class WaitingRoom extends JPanel implements ActionListener {



private final int numOfPlayer;
private final ClientMessageHandler handler;


    public WaitingRoom(int numOfPlayer, ClientMessageHandler handler){
        this.numOfPlayer = numOfPlayer;
        this.handler = handler;
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);

    }

    public void paint(Graphics g){
            g.drawString("Waiting for other players", 350, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectMessage receivedMarket = null;
        ObjectMessage receivedProdMarket = null;
        try {
            handler.sendMessageToServer("setup game");
        } catch (EndingGameException endingGameException) {
            //TODO disconnect
        }
        try {
            receivedMarket = (ObjectMessage) handler.readMessage();
            receivedProdMarket = (ObjectMessage) handler.readMessage();
        } catch (EndingGameException endingGameException) {
            //TODO disconnect
        }
        if(receivedMarket.getMessage().equals("object")){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new PreGameRes(handler));
            MainGUI.frame.revalidate();
        }


    }


}
