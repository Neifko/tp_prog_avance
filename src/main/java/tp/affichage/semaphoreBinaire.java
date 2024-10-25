package tp.affichage;

public final class semaphoreBinaire extends semaphore {
    public semaphoreBinaire(int valeurInitiale) {
        super((valeurInitiale != 0) ? 1 : 0);
    }

    public final synchronized void syncSignal() {
        super.syncSignal();
        if (valeur > 1) valeur = 1;
    }
}

