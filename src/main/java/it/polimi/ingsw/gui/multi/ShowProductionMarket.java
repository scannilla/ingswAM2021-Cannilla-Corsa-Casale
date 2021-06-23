package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.production.ProductionCardsMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowProductionMarket extends JPanel implements ActionListener {

    private ProductionCardsMarket productionCardsMarket;
    private boolean fromTurn;
    private final JButton back;
    private final ClientMessageHandler handler;

    public ShowProductionMarket(ClientMessageHandler handler, boolean fromTurn){
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.setBounds(650, 650, 100, 50);
        back.addActionListener(this);
        this.add(back);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        productionCardsMarket = Data.instanceCreator().getProductionCardsMarket();
        for (int x = 50; x < 400; x = x + 150) {
            for (int y = 50; y < 750; y = y + 250) {
                g.drawRect(x, y, 100, 200);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back && fromTurn){
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
