package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame {
    UnMobile sonMobile;
    private final int LARG = 400, HAUT = 250;

    public UneFenetre() {
        super("Le Mobile");

        Container cont = getContentPane();
        cont.setLayout(new GridLayout(2, 2));
        // ajouter sonMobile a la fenetre
        sonMobile = new UnMobile(LARG, HAUT);
        JButton btn = new JButton("SUSPEND/RESUME");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn.doClick();
            }
        });
        cont.add(sonMobile);
        cont.add(btn);
        // creer une thread laThread avec sonMobile
        Thread threadMobile = new Thread(sonMobile);
        // afficher la fenetre
        // lancer laThread



        // Peuvent etre placé apres le thread grace au dev parallele
        // mais par bon principe son au dessus
        setSize(LARG, HAUT);
        setVisible(true);

        threadMobile.start();
    }
}
