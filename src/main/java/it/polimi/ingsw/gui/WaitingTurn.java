package it.polimi.ingsw.gui;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingTurn extends JPanel implements ActionListener {

    private JButton prodCardMarket, marketMarble, myPP, ppOne, ppTwo, ppThree, goAhead;
    private ClientMessageHandler handler;

    public WaitingTurn(ClientMessageHandler handler) {
        this.handler = handler;

    }


    public void paint(Graphics g){
        g.drawString("Waiting for:", 300, 50);
        g.drawString("You can do actions below", 300, 100);
    }





    private void createLayout(int player){
        switch(player){
            case 1:
                prodCardMarket = new JButton("View Production card market");
                marketMarble = new JButton("View Market Marble");
                myPP = new JButton("View my Personal Board");
                ppOne = new JButton("View Lorenzo's Personal Board");
                goAhead = new JButton("Continue");
                prodCardMarket.setBounds(100, 300, 200, 100);
                marketMarble.setBounds(500, 300, 200, 100);
                myPP.setBounds(200, 410, 100, 50);
                ppOne.setBounds(600, 410, 100, 50);
                goAhead.setBounds(750, 750, 50, 50);
                prodCardMarket.addActionListener(this);
                marketMarble.addActionListener(this);
                myPP.addActionListener(this);
                ppOne.addActionListener(this);
                goAhead.addActionListener(this);
                this.add(marketMarble);
                this.add(myPP);
                this.add(ppOne);
                this.add(prodCardMarket);
                this.add(goAhead);
                break;
            case 2:
                prodCardMarket = new JButton("View Production card market");
                marketMarble = new JButton("View Market Marble");
                myPP = new JButton("View my Personal Board");
                ppOne = new JButton("View nickname's Personal Board");
                goAhead = new JButton("Continue");
                prodCardMarket.setBounds(100, 300, 200, 100);
                marketMarble.setBounds(500, 300, 200, 100);
                myPP.setBounds(200, 410, 100, 50);
                ppOne.setBounds(600, 410, 100, 50);
                goAhead.setBounds(750, 750, 50, 50);
                prodCardMarket.addActionListener(this);
                marketMarble.addActionListener(this);
                myPP.addActionListener(this);
                ppOne.addActionListener(this);
                goAhead.addActionListener(this);
                this.add(marketMarble);
                this.add(myPP);
                this.add(ppOne);
                this.add(prodCardMarket);
                this.add(goAhead);
                break;
            case 3:
                prodCardMarket = new JButton("View Production card market");
                marketMarble = new JButton("View Market Marble");
                myPP = new JButton("View my Personal Board");
                ppOne = new JButton("View nickname's Personal Board");
                ppTwo = new JButton("View nickname's Personal Board");
                goAhead = new JButton("Continue");
                prodCardMarket.setBounds(100, 300, 200, 100);
                marketMarble.setBounds(500, 300, 200, 100);
                myPP.setBounds(100, 410, 100, 50);
                ppOne.setBounds(250, 410, 100, 50);
                ppTwo.setBounds(400, 410, 100, 50);
                goAhead.setBounds(750, 750, 50, 50);
                prodCardMarket.addActionListener(this);
                marketMarble.addActionListener(this);
                myPP.addActionListener(this);
                ppOne.addActionListener(this);
                ppTwo.addActionListener(this);
                goAhead.addActionListener(this);
                this.add(marketMarble);
                this.add(myPP);
                this.add(ppOne);
                this.add(prodCardMarket);
                this.add(ppTwo);
                this.add(goAhead);
                break;
            case 4:
                prodCardMarket = new JButton("View Production card market");
                marketMarble = new JButton("View Market Marble");
                myPP = new JButton("View my Personal Board");
                ppOne = new JButton("View nickname's Personal Board");
                ppTwo = new JButton("View nickname's Personal Board");
                ppThree = new JButton("View nickname's Personal Board");
                goAhead = new JButton("Continue");
                prodCardMarket.setBounds(100, 300, 200, 100);
                marketMarble.setBounds(500, 300, 200, 100);
                myPP.setBounds(100, 410, 100, 50);
                ppOne.setBounds(250, 410, 100, 50);
                ppTwo.setBounds(400, 410, 100, 50);
                ppThree.setBounds(550, 410, 100, 50);
                goAhead.setBounds(750, 750, 50, 50);
                prodCardMarket.addActionListener(this);
                marketMarble.addActionListener(this);
                myPP.addActionListener(this);
                ppOne.addActionListener(this);
                ppTwo.addActionListener(this);
                ppThree.addActionListener(this);
                goAhead.addActionListener(this);
                this.add(marketMarble);
                this.add(myPP);
                this.add(ppOne);
                this.add(prodCardMarket);
                this.add(ppTwo);
                this.add(ppThree);
                this.add(goAhead);
                break;
        }
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == prodCardMarket){
            try{
                handler.sendMessageToServer("show production card market", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if (e.getSource() == marketMarble){
            try{
                handler.sendMessageToServer("show market marble", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == myPP){
            try{
                handler.sendMessageToServer("show production my personal board", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == ppOne){
            try{
                handler.sendMessageToServer("show nickname personal board", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == ppTwo){
            try{
                handler.sendMessageToServer("show nickname personal board", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == ppThree){
            try{
                handler.sendMessageToServer("show nickname personal board", 1);
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == goAhead){
            Main.frame.remove(this);
            Main.frame.add(new Turn(handler));
            Main.frame.revalidate();
        }

    }

}
