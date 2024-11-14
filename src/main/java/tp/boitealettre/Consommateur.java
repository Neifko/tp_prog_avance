package tp.boitealettre;

import java.io.*;
import java.util.*;

/**
 *
 */
public class Consommateur extends Thread {

    /**
     * Default constructor
     */
    public Consommateur(BoiteALettre bal) {
        boiteALettre = bal;
    }

    /**
     *
     */
    private char lettreRecupere;
    private BoiteALettre boiteALettre;


    /**
     *
     */
    public void run() {
        lettreRecupere = boiteALettre.retirer();
        System.out.println("J'ai recupere la lettre : " + lettreRecupere);
    }

}