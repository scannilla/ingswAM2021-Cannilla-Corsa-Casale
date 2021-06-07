package it.polimi.ingsw.gui;

import javax.swing.*;
import java.awt.*;

public class ShowProductionMarket extends JPanel {




    public void paint(Graphics g) {
        for (int x = 50; x < 400; x = x + 150) {
            for (int y = 50; y < 750; y = y + 250) {
                g.drawRect(x, y, 100, 200);
            }
        }
    }



}
