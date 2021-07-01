package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.multi.Turn;
import it.polimi.ingsw.gui.multi.WaitingTurn;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.marbles.MarketStructure;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ShowMarketMarble extends JPanel implements ActionListener {

    private MarketStructure marketStructure;
    private SPClientMessageHandler handler;
    private MarketMarble outMarble;
    private final JButton back;
    private final boolean fromTurn;

    public ShowMarketMarble(SPClientMessageHandler handler, boolean fromTurn){
        this.handler = handler;
        this.fromTurn = fromTurn;
        back = new JButton("Go Back");
        back.setBounds(650, 650, 100, 50);
        back.addActionListener(this);
        this.add(back);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    private void myDrawImagePNG(Graphics g){
        marketStructure = Data.instanceCreator().getMarketStructure();
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("plancia portabiglie.png");
        BufferedImage img;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 0,0, 614, 800, null);
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++) {
                switch (marketStructure.getMarketStructure()[i][j].getColor()){
                    case 0: url = cl.getResourceAsStream("BigliaBianca.png");
                        break;
                    case 1: url = cl.getResourceAsStream("BigliaNera.png");
                        break;
                    case 2: url = cl.getResourceAsStream("BigliaAzzurra.png");
                        break;
                    case 3: url = cl.getResourceAsStream("BigliaGialla.png");
                        break;
                    case 4: url = cl.getResourceAsStream("BigliaViola.png");
                        break;
                    case 5: url = cl.getResourceAsStream("BigliaRossa.png");
                        break;
                }
                outMarble = marketStructure.getOutMarble();
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 65*i+177, 65*j+173, 65, 65, null);
            }
        }
        switch (outMarble.getColor()){
            case 0: url = cl.getResourceAsStream("BigliaBianca.png");
                break;
            case 1: url = cl.getResourceAsStream("BigliaNera.png");
                break;
            case 2: url = cl.getResourceAsStream("BigliaAzzurra.png");
                break;
            case 3: url = cl.getResourceAsStream("BigliaGialla.png");
                break;
            case 4: url = cl.getResourceAsStream("BigliaViola.png");
                break;
            case 5: url = cl.getResourceAsStream("BigliaRossa.png");
                break;
        }
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 100, 100, 65, 65, null);
    }


    public void paint(Graphics g) {
    myDrawImagePNG(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back && fromTurn){
            MainGUI.changePanel(new Turn(handler));
        } else if (e.getSource() == back && !fromTurn){
            MainGUI.changePanel(new WaitingTurn(handler));
        }
    }
}
