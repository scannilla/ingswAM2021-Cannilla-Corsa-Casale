package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.multi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Turn extends JPanel implements ActionListener {
    private final JButton buyMarble;
    private final JButton buyProd;
    private final JButton activeLeader;
    private final JButton activeProd;
    private final JButton viewMyPB;
    private final JButton viewPB1;
    private final JButton viewMarketMarble;
    private final JButton viewProdMarket;
    private final JButton endTurn;
    private final JButton leaderCard;
    private final SPClientMessageHandler handler;
    public Turn(SPClientMessageHandler handler){
        this.handler = handler;
        buyMarble = new JButton ("Buy marble");
        buyMarble.setBounds(10, 110, 180, 180);
        buyProd = new JButton("Buy production card");
        buyProd.setBounds(210, 110, 180, 180);
        activeLeader = new JButton("Active leader card");
        activeLeader.setBounds(410, 110, 180, 180);
        activeProd = new JButton("Active production card");
        activeProd.setBounds(610, 110, 180, 180);
        viewMyPB = new JButton("View my personal board");
        viewMyPB.setBounds(10, 510, 180, 180);
        viewMarketMarble = new JButton("View market marble");
        viewMarketMarble.setBounds(10, 310, 380, 180);
        viewProdMarket = new JButton("View production cards market");
        viewProdMarket.setBounds(410, 310, 380, 180);
        endTurn = new JButton("End turn");
        endTurn.setBounds(660, 710, 130, 80);
        viewPB1 = new JButton("View Lorenzo's personal board");
        viewPB1.setBounds(210, 510, 180, 180);
        viewPB1.addActionListener(this);
        leaderCard = new JButton("View Leader Card");
        leaderCard.setBounds(410, 510, 180, 180);
        this.add(viewPB1);
        this.add(buyMarble);
        this.add(buyProd);
        this.add(activeLeader);
        this.add(activeProd);
        this.add(viewMyPB);
        this.add(viewMarketMarble);
        this.add(viewProdMarket);
        this.add(endTurn);
        this.add(leaderCard);
        buyMarble.addActionListener(this);
        buyProd.addActionListener(this);
        activeLeader.addActionListener(this);
        activeProd.addActionListener(this);
        viewMyPB.addActionListener(this);
        viewMarketMarble.addActionListener(this);
        viewProdMarket.addActionListener(this);
        endTurn.addActionListener(this);
        leaderCard.addActionListener(this);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }


    public void paint(Graphics g){
        g.drawString("It is your turn", 300, 50);
        g.drawString("Choose your action", 300, 100);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        chooseAction(e);
    }

    public void chooseAction(ActionEvent e){
        if (e.getSource() == buyMarble){
            MainGUI.changePanel(new BuyMarble(handler));
        } else if(e.getSource() == buyProd){
            MainGUI.changePanel(new BuyProductionCard(handler));
        } else if(e.getSource() == activeLeader){
            MainGUI.changePanel(new ActiveLeader(handler));
        } else if(e.getSource() == activeProd){
            MainGUI.changePanel(new ActiveProduction(handler));
        } else if(e.getSource() == viewMarketMarble){
            try{
                handler.sendMessageToServer("view market", 163);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }

            MainGUI.changePanel(new ShowMarketMarble(handler, true));

        } else if(e.getSource() == viewProdMarket){
            try{
                handler.sendMessageToServer("view card market", 162);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }

            MainGUI.changePanel(new ShowProductionMarket(handler, true));

        } else if(e.getSource() == viewMyPB){
            try{
                handler.sendMessageToServer("view -personalboard -1", 164);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
            MainGUI.changePanel(new ShowPersonalBoard(handler, true));
        } else if (e.getSource() == viewPB1) {
            try {
                handler.sendMessageToServer("view -personalboard -2");
            } catch (EndingGameException ex) {
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }

            MainGUI.changePanel(new ShowPersonalBoard(handler, true));

        } else if(e.getSource() == leaderCard){
            try{
                handler.sendMessageToServer("view leader card");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }

            MainGUI.changePanel(new ShowLeaderCard(handler, true));

        } else if(e.getSource() == endTurn){
            try{
                handler.sendMessageToServer("end turn", 165);
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }
    }

}