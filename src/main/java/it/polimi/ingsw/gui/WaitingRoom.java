package it.polimi.ingsw.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingRoom extends JPanel implements ActionListener {

private JProgressBar progressBar;
private int player;
    public WaitingRoom(int player){
        this.player = player;
        progressBar = new JProgressBar();
        progressBar.setBounds(100, 100, 200, 50);
        this.add(progressBar);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g){
        g.drawString("Waiting for other players (/"+ player + ")", 100, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if(progressBar.getValue() == 100){
            Main.frame.remove(this);
            Main.frame.add(new PreGameRes());
            Main.frame.revalidate();
        }
    }
}
