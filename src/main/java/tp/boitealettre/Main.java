package tp.boitealettre;

public class Main {
    public static void main(String[] args) {
        BoiteALettre bal = new BoiteALettre();
        Producteur prod1 = new Producteur(bal, 'A');
        Producteur prod2 = new Producteur(bal, 'B');

        Consommateur cons = new Consommateur(bal);

        prod1.start();
        prod2.start();
        cons.start();
    }
}
