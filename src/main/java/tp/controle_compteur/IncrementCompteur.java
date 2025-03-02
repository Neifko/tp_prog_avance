package tp.controle_compteur;

public class IncrementCompteur {
    public static void main(String[] args){
        int nb= 4;

        Compteur[] monCompteur = new Compteur[nb];

        for (int i = 0; i < nb; i++){
            monCompteur[i] = new Compteur();
        }

        for (int i = 0; i < nb; i++){
            monCompteur[i].start();
        }
    }
}
