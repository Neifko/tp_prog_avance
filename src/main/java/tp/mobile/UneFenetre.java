package tp.mobile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame {
    UnMobile sonMobile1;
    private final int LARG = 900, HAUT = 200;
    final int nb_mobile = 4;

    public UneFenetre() {
        super("Le Mobile");

        Container cont = getContentPane();
        cont.setLayout(new GridLayout(nb_mobile, 1));


        for (int i = 0; i < nb_mobile; i++) {
            // ajouter sonMobile a la fenetre
            sonMobile1 = new UnMobile(LARG, HAUT);

            cont.add(sonMobile1);

            Thread threadMobile = new Thread(sonMobile1);
            threadMobile.start();

        }

        // ajouter sonMobile a la fenetre

        // creer une thread laThread avec sonMobile
        // afficher la fenetre
        // lancer laThread



        // Peuvent etre placé apres le thread grace au dev parallele
        // mais par bon principe son au dessus
        setSize(LARG, HAUT*nb_mobile);
        setVisible(true);

    }
}