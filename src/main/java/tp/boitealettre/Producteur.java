package tp.boitealettre;


import java.io.*;
import java.util.*;

/**
 *
 */
public class Producteur extends Thread {

    /**
     * Default constructor
     */
    public Producteur(BoiteALettre bal, char lettre) {
        boiteALettre = bal;
        lettreAPoster = lettre;
    }

    /**
     *
     */
    private char lettreAPoster;
    private BoiteALettre boiteALettre;

    /**
     *
     */
    public void run() {
        boiteALettre.deposer(lettreAPoster);
        System.out.println("J'ai deposé la lettre : " + lettreAPoster);
    }

}