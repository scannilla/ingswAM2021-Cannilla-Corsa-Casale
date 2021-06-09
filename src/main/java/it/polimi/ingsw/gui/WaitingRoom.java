package it.polimi.ingsw.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;

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
        Message received = null;
        try {
            handler.sendMessageToServer("setup game");
        } catch (EndingGameException endingGameException) {
            //TODO disconnect
        }
        try {
            received = handler.readMessage();
        } catch (EndingGameException endingGameException) {
            //TODO disconnect
        }
        if(received.getMessage().equals("ok")){
            Main.frame.remove(this);
            Main.frame.add(new PreGameRes(handler));
            Main.frame.revalidate();
        }


    }

    private void setProgressBar(){

    }

}
