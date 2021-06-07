package it.polimi.ingsw.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

public class WaitingRoom extends JPanel implements ActionListener {

private final JProgressBar progressBar;
private final JButton prova;


    public WaitingRoom(){
        prova = new JButton("prova");
        prova.setBounds(200, 500, 100, 50);
        progressBar = new JProgressBar();
        progressBar.setBounds(100, 100, 200, 50);
        this.add(progressBar);
        this.add(prova);
        prova.addActionListener(this);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(TODO change){
            Main.frame.remove(this);
            Main.frame.add(new PreGameRes());
            Main.frame.revalidate();
        }*/

        if(progressBar.getValue() == 100){
            Main.frame.remove(this);
            //Main.frame.add(new PreGameRes());
            Main.frame.revalidate();
        }
    }
}
