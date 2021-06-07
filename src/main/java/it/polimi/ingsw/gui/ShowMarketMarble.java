package it.polimi.ingsw.gui;

import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.marbles.MarketStructure;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import it.polimi.ingsw.Game;

public class ShowMarketMarble extends JPanel{
    private final Game game;

    public ShowMarketMarble(Game game) {
        this.game = game;
    }

    private void myDrawImagePNG(Graphics g, Game game){
        MarketMarble[][] marketStructure = game.getMarket().getMarketStructure();
        int outmarble = game.getMarket().getOutMarble().getColor();
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
                switch (marketStructure[j][i].getColor()){
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
                g.drawImage(img, 65*i+177, 65*j+173, 65, 65, null);
            }
        }
        switch (outmarble){
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
    myDrawImagePNG(g, game);
    }
}
