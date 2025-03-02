package tp.controle_compteur;

public class Compteur extends Thread{
    static int increment = 0;

    public void run()
    {
        synchronized(Compteur.class) {
        increment++;
        System.out.print(increment);
    }
    }
}

