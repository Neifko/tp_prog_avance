package tp.boitealettre;


/**
 *
 */
public class BoiteALettre {

    /**
     * Default constructor
     */
    public BoiteALettre() {
    }

    /**
     *
     */
    private char lettre;

    /**
     *
     */
    private boolean boiteVide = true;

    /**
     *
     */
    public synchronized void deposer(char lettreAPoster) {
        boiteVide = false;
        lettre = lettreAPoster;

    }

    /**
     *
     */
    public synchronized char retirer() {
        boiteVide = true;
        return lettre;
    }

}
