package it.polimi.ingsw.gui.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;


public class ShowPersonalBoard extends JPanel implements ActionListener {

    private PersonalBoard personalBoard;
    private boolean fromTurn;
    private final ClientMessageHandler handler;
    private final JButton back;

    public ShowPersonalBoard(ClientMessageHandler handler, boolean fromTurn){
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.addActionListener(this);
        back.setBounds(650, 650, 100, 50);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        personalBoard = Data.instanceCreator().getPersonalBoard();
        vatReport = Data.instanceCreator().getVatReport();
        int[] activationPos = vatReport.getActivationPosition();
        int[] winPoints = vatReport.getWinPoints();
        int[] length = vatReport.getReportsLength();
        Font f = new Font("Times New Roman", Font.BOLD, 10);
        g.setFont(f);

        for (int j = 0; j <= activationPos[0] - length[0]; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[0] - length[0] + 1; j <= activationPos[0]; j++) {
            g.setColor(Color.yellow);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[0] + 1; j <= activationPos[61] - length[1]; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[1] - length[1] + 1; j <= activationPos[1]; j++) {
            g.setColor(Color.yellow);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[1] + 1; j <= activationPos[2] - length[2]; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[2] - length[2] + 1; j <= activationPos[2]; j++) {
            g.setColor(Color.yellow);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }
        for (int j = activationPos[2] + 1; j < 25; j++) {
            g.setColor(Color.white);
            g.drawRect(j * 32, 50, 32, 32);
            g.drawString("P" + j, 1 + 32 * j, 51);
        }

        g.setColor(Color.yellow);
        drawPosition(g, personalBoard.getPosition());


        g.drawRect(50, 600, 200, 100); //strongbox
        for (int j = 300; j<600; j = j + 150){ //prodcard slot
            g.drawRect(j, 600, 100, 200);
        }

    }







    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back && fromTurn){
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
}
