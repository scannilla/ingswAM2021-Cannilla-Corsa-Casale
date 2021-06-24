package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.controller.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGame extends JPanel implements ActionListener {

    private final JButton joinGame;
    private final ClientMessageHandler handler;
    public JoinGame(ClientMessageHandler handler){
        this.handler = handler;
        joinGame = new JButton("Join Game");
        joinGame.addActionListener(this);
        joinGame.setBounds(350, 350, 100, 100);
        this.add(joinGame);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == joinGame){

            try {
                handler.sendMessageToServer("join game");
            } catch (EndingGameException endingGameException) {
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            Message confirm = null;
            try{
                confirm = handler.readMessage();
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            if (confirm.getCode() == 112){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new WaitingRoom(handler));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            } else if (confirm.getCode() == 201){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Multi(handler));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }


        }
    }



}
