package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.MainGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BuyMarble extends JPanel implements ActionListener {

    private final JButton column1, column2, column3, column4, line1, line2, line3, back;
    private ClientMessageHandler handler;
    public BuyMarble(ClientMessageHandler handler){
        this.handler = handler;
        column1 = new JButton("Buy");
        column2 = new JButton("Buy");
        column3 = new JButton("Buy");
        column4 = new JButton("Buy");
        line1 = new JButton("Buy");
        line2 = new JButton("Buy");
        line3 = new JButton("Buy");
        back = new JButton("Go Back");
        column1.setBounds(100, 400, 100, 100);
        column2.setBounds(200, 400, 100, 100);
        column3.setBounds(300, 400, 100, 100);
        column4.setBounds(400, 400, 100, 100);
        line1.setBounds(500, 100, 100, 50);
        line2.setBounds(500, 200, 100, 50);
        line3.setBounds(500, 300, 100, 50);
        back.setBounds(650, 650, 100, 50);
        line1.addActionListener(this);
        line2.addActionListener(this);
        line3.addActionListener(this);
        column1.addActionListener(this);
        column2.addActionListener(this);
        column3.addActionListener(this);
        column4.addActionListener(this);
        back.addActionListener(this);
        this.add(line1);
        this.add(line2);
        this.add(line3);
        this.add(column1);
        this.add(column2);
        this.add(column3);
        this.add(column4);
        this.add(back);
        this.setSize(800, 800);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        for (int x = 100; x<400; x = x + 100){ //market marble
            for (int j = 100; j < 300; j = j + 100){
                g.drawRect(x, j, 100, 100);
            }
        }
    g.drawRect(550, 100, 100, 100); //out marble
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == line1){
            try{
                handler.sendMessageToServer("buy marble line, 1");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == line2){
            try{
                handler.sendMessageToServer("buy marble line, 2");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if (e.getSource() == line3){
            try{
                handler.sendMessageToServer("buy marble line, 3");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if (e.getSource() == column1){
            try{
                handler.sendMessageToServer("buy marble column, 1");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if (e.getSource() == column2){
            try{
                handler.sendMessageToServer("buy marble column, 2");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == column3){
            try{
                handler.sendMessageToServer("buy marble column, 3");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == column4){
            try{
                handler.sendMessageToServer("buy marble column, 4");
            } catch (EndingGameException ex){
                //TODO disconnect
            }
        } else if(e.getSource() == back){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
        }
    }
}
