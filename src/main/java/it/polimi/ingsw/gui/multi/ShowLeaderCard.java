package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.leader.LeaderCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowLeaderCard extends JPanel implements ActionListener {

    private LeaderCard[] leaderCards;
    private final JButton back;
    private final ClientMessageHandler handler;
    private final boolean fromTurn;

    public ShowLeaderCard(ClientMessageHandler handler, boolean fromTurn){
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.setBounds(500, 500, 100, 50);
        back.addActionListener(this);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(800, 800);
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g){
        leaderCards = Data.instanceCreator().getLeaderCards();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back && fromTurn){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
        } else if (e.getSource() == back && !fromTurn){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new WaitingTurn(handler));
            MainGUI.frame.revalidate();
        }
    }
}


