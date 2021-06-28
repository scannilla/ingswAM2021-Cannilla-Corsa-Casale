package it.polimi.ingsw.gui;

import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.multi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error extends JPanel implements ActionListener {

    private final JButton goBack;
    private String error;
    private final ClientMessageHandler cmHandler;
    private int code;   // 0 = Error, 1 = ActiveLeader, 2 = ActiveProduction, 3 = AskNicknameMulti, 4 = BuyMarble, 5 = BuyProductionCard
                        // 6 = Turn, 7 = LineSelectionDepot, 8 = JoinGame

    public Error(String error, ClientMessageHandler cmHandler, int code){
        this.error = error;
        this.cmHandler = cmHandler;
        this.code = code;
        goBack = new JButton("Go Back");
        goBack.setBounds(650, 650, 100, 50);
        goBack.addActionListener(this);
        this.add(goBack);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void paint(Graphics g){
        g.drawString(error, 350, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBack && code == 1){
            MainGUI.changePanel(new ActiveLeader(cmHandler));
        } else if (e.getSource() == goBack && code == 2){
            MainGUI.changePanel(new ActiveProduction(cmHandler));
        } else if (e.getSource() == goBack && code == 3){
            MainGUI.changePanel(new AskNicknameMulti(cmHandler));
        } else if (e.getSource() == goBack && code == 4){
            MainGUI.changePanel(new BuyMarble(cmHandler));
        } else if (e.getSource() == goBack && code == 5){
            MainGUI.changePanel(new BuyProductionCard(cmHandler));
        } else if (e.getSource() == goBack && code == 6){
            MainGUI.changePanel(new Turn(cmHandler));
        } else if (e.getSource() == goBack && code == 0){
            MainGUI.changePanel(new Intro("error", 1));
        } else if(e.getSource() == goBack && code == 7){
            MainGUI.changePanel(new LineSelectionDepot(cmHandler));
        } else if (e.getSource() == goBack && code == 8){
            MainGUI.changePanel(new JoinGame(cmHandler));
        }
    }
}
