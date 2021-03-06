package it.polimi.ingsw.gui.multi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.VaticanReport;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.production.ProdCardSlot;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;
import it.polimi.ingsw.resources.Strongbox;
import it.polimi.ingsw.resources.WarehouseDepot;


public class ShowPersonalBoard extends JPanel implements ActionListener {

    private PersonalBoard personalBoard;
    private VaticanReport vatReport;
    private boolean fromTurn;
    private final ClientMessageHandler handler;
    private final JButton back;

    public ShowPersonalBoard(ClientMessageHandler handler, boolean fromTurn) {
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.addActionListener(this);
        back.setBounds(650, 650, 100, 50);
        this.add(back);
        this.setSize(800,800);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        myDrawingPNG(g);
        }


    public void myDrawingPNG(Graphics g){
        BufferedImage img;
        InputStream url;
        personalBoard = Data.instanceCreator().getPersonalBoard();
        vatReport = Data.instanceCreator().getVatReport();
        int[] activationPos = vatReport.getActivationPosition();
        int[] winPoints = vatReport.getWinPoints();
        int[] length = vatReport.getReportsLength();
        Font f = new Font("Times New Roman", Font.BOLD, 10);
        g.setFont(f);
        ClassLoader cl = this.getClass().getClassLoader();
//FAITH TRACK
        for (int j = 0; j <= activationPos[0] - length[0]; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[0] - length[0] + 1; j <= activationPos[0]; j++) {
            g.setColor(Color.red);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[0] + 1; j <= activationPos[1] - length[1]; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[1] - length[1] + 1; j <= activationPos[1]; j++) {
            g.setColor(Color.red);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[1] + 1; j <= activationPos[2] - length[2]; j++) {
            g.setColor(Color.red);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[2] - length[2] + 1; j <= activationPos[2]; j++) {
            g.setColor(Color.red);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[2] + 1; j < 25; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        g.setColor(Color.black);
        drawPosition(g, personalBoard.getPosition(), cl);
        int index = 0;
        for (int i = 0; i<25;i++){
            if((i+1) % 3 == 0){
                g.drawString("WP" + winPoints[index], 25+i*32, 51);
                index++;
            }

        }

//STRONGBOX
        g.drawRect(50, 600, 200, 100);

        Strongbox strongbox = personalBoard.getStrongbox();
        int[] values = strongbox.getStrongboxResourcesAmount();
        for (int i = 0; i < values.length; i++) {
            switch (i){
                case 0:
                    url = cl.getResourceAsStream("coin2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55, 605, 40, 40, null);
                    g.drawString("x" + values[0], 95, 605);
                    break;
                case 1:
                    url = cl.getResourceAsStream("stone2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55, 650, 40, 40, null);
                    g.drawString("x" + values[1], 95, 650);
                    break;
                case 2:
                    url = cl.getResourceAsStream("servant2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55, 695, 40, 40, null);
                    g.drawString("x" + values[0], 95, 695);
                    break;
                case 3:
                    url = cl.getResourceAsStream("shield2.png");
                    try {
                        img = ImageIO.read(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    g.drawImage(img, 55, 740, 40, 40, null);
                    g.drawString("x" + values[0], 95, 740);
                    break;
                default:
                    break;
            }
        }
//WAREHOUSE DEPOT

        g.drawRect(55, 400, 50, 50);
        g.drawRect(30, 450, 50, 50);
        g.drawRect(80, 450, 50, 50);
        g.drawRect(5, 500, 50, 50);
        g.drawRect(55, 500, 50, 50);
        g.drawRect(105, 500, 50, 50);

        Resource[][] depot = personalBoard.getWarehouseDepot().getDepot();
try{
        switch (depot[0][0].getResType()) {
            case 0:
                url = cl.getResourceAsStream("coin2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 52, 402, 40, 40, null);
                break;
            case 1:
                url = cl.getResourceAsStream("stone2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 52, 402, 40, 40, null);
                break;
            case 2:
                url = cl.getResourceAsStream("servant2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 52, 402, 40, 40, null);
                break;
            case 3:
                url = cl.getResourceAsStream("shield2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 52, 402, 40, 40, null);
                break;
            default:
                break;
        }

    } catch (NullPointerException ignored){
        }
        int k = 0;
try {
    for (int i = 0; i < 2; i++) {
        switch (depot[1][i].getResType()) {
            case 0:
                url = cl.getResourceAsStream("coin2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 32 + k, 452, 40, 40, null);
                k += 50;
                break;
            case 1:
                url = cl.getResourceAsStream("stone2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 32 + k, 452, 40, 40, null);
                k += 50;
                break;
            case 2:
                url = cl.getResourceAsStream("servant2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 32 + k, 452, 40, 40, null);
                k += 50;
                break;
            case 3:
                url = cl.getResourceAsStream("shield2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 32 + k, 452, 40, 40, null);
                k += 50;
                break;
            default:
                k += 50;
                break;
        }
    }
} catch (NullPointerException ignored){

}
        k = 0;
try {
    for (int j = 0; j < 3; j++) {
        switch (depot[2][j].getResType()) {
            case 0:
                url = cl.getResourceAsStream("coin2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 7 + k, 502, 40, 40, null);
                k += 50;
                break;
            case 1:
                url = cl.getResourceAsStream("stone2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 7 + k, 502, 40, 40, null);
                k += 50;
                break;
            case 2:
                url = cl.getResourceAsStream("servant2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 7 + k, 502, 40, 40, null);
                k += 50;
                break;
            case 3:
                url = cl.getResourceAsStream("shield2.png");
                try {
                    img = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(img, 7 + k, 502, 40, 40, null);
                k += 50;
                break;
            default:
                k += 50;
                break;
        }
    }
} catch (NullPointerException ignored){
}


        //PROD CARD SLOT
        for (int j = 300; j < 600; j = j + 150) {
            g.drawRect(j, 600, 100, 200);
        }
        ProdCardSlot prodCardSlot = personalBoard.getProdCardSlot();
        ProductionCard[] topCards = prodCardSlot.getTopCards();
        k = 0;
        for (int i = 0; i < 3; i++) { //DRAW TYPE
            if (topCards[i] != null) {
                switch (topCards[i].getType()) {
                    case 1:
                        url = cl.getResourceAsStream("type 1.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 302 + k, 602, 40, 40, null);
                        k += 150;
                        break;
                    case 2:
                        url = cl.getResourceAsStream("type 2.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 302 + k, 602, 40, 40, null);
                        k += 150;
                        break;
                    case 3:
                        url = cl.getResourceAsStream("type 3.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 302 + k, 602, 40, 40, null);
                        k += 150;
                        break;
                    case 4:
                        url = cl.getResourceAsStream("type 4.png");
                        try {
                            img = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        g.drawImage(img, 302 + k, 602, 40, 40, null);
                        k += 150;
                        break;
                    default:
                        k += 150;
                        break;
                }
            }
        }
        k = 0;
        for (int i = 0; i < 3; i++) { //DRAW LEVEL
            if(topCards[i]!=null) {
                g.drawString("Level:" + topCards[i].getLevel(), 302 + k, 447);
            }
            k += 150;
        }
        k = 0;
        for (int i = 0; i < 3; i++) { //DRAW WIN POINTS
            if (topCards[i]!=null){
                g.drawString("Win Points: " + topCards[i].getWp(), 302 + k, 450);
            }
            k += 150;
        }
        k = 0;
        int w = 0;
        int[] costArray;
        g.drawString("Cost:", 302, 452); //DRAW COST
        for (int i = 0; i < 3; i++) {
            if (topCards[i] != null) {
                costArray = ResourceCounter.resCount(topCards[i].getCostArray());
                for (int j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            url = cl.getResourceAsStream("coin2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 454 + (w / 2), 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 494, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 454, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 1:
                            url = cl.getResourceAsStream("stone2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 454 + (w / 2), 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 494, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 454, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 2:
                            url = cl.getResourceAsStream("servant2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 454 + (w / 2), 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 494, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 454, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 3:
                            url = cl.getResourceAsStream("shield2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 454 + (w / 2), 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 494, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 454, 40, 40, null);
                                g.drawString("x" + costArray[j], 342, 454);
                                w += 40;
                            }
                            break;
                        default:
                            break;

                    }
                }
                k += 150;
            }
        }

        k = 0;
        w = 0;
        int[] requiredRes;
        g.drawString("Required Resource:", 302, 532); //DRAW REQUIRED RES
        for (int i = 0; i < 3; i++) {
            if (topCards[i] != null) {
                requiredRes = ResourceCounter.resCount(topCards[i].getRequiredRes());
                for (int j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            url = cl.getResourceAsStream("coin2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 534 + (w / 2), 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 574, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 534, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 1:
                            url = cl.getResourceAsStream("stone2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 534 + (w / 2), 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 574, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 534, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 2:
                            url = cl.getResourceAsStream("servant2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 534 + (w / 2), 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 574, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 534, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 3:
                            url = cl.getResourceAsStream("shield2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 534 + (w / 2), 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 574, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 534, 40, 40, null);
                                g.drawString("x" + requiredRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        default:
                            break;

                    }
                }
                k += 150;
            }
        }
        k = 0;
        w = 0;
        int[] givenRes;
        g.drawString("Given Resource:", 302, 532); //DRAW GIVEN RESOURCE
        for (int i = 0; i < 3; i++) {
            if (topCards[i] != null) {
                givenRes = ResourceCounter.resCount(topCards[i].getGivenRes());
                for (int j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            url = cl.getResourceAsStream("coin2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 614 + (w / 2), 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 654, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 614, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 1:
                            url = cl.getResourceAsStream("stone2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 614 + (w / 2), 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 654, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 614, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 2:
                            url = cl.getResourceAsStream("servant2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 614 + (w / 2), 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 654, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 614, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        case 3:
                            url = cl.getResourceAsStream("shield2.png");
                            try {
                                img = ImageIO.read(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                            if (w == 80) {
                                g.drawImage(img, 302 + k, 614 + (w / 2), 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                            } else if (w > 80) {
                                g.drawImage(img, 342 + k, 654, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            } else {
                                g.drawImage(img, 302 + w, 614, 40, 40, null);
                                g.drawString("x" + givenRes[j], 342, 454);
                                w += 40;
                            }
                            break;
                        default:
                            break;

                    }
                }
                k += 150;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back && fromTurn){
            if(e.getSource() == back && fromTurn){
                MainGUI.changePanel(new Turn(handler));
            } else if (e.getSource() == back && !fromTurn){
                MainGUI.changePanel(new WaitingTurn(handler));
            }
        }
    }
    private void drawPosition(Graphics g, int position, ClassLoader cl){
        BufferedImage img;
        InputStream url;
        url = cl.getResourceAsStream("croce.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 10 + 32*position, 60, 12, 12, null);
    }
}
