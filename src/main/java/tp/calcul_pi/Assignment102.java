// Estimate the value of Pi using Monte-Carlo Method, using parallel program
package tp.calcul_pi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class PiMonteCarlo {
    AtomicInteger nAtomSuccess;
    int nThrows, n_proc;
    double value;

    class MonteCarlo implements Runnable {
        @Override
        public void run() {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1)
                nAtomSuccess.incrementAndGet();
        }
    }

    public PiMonteCarlo(int i, int n_proc) {
        this.nAtomSuccess = new AtomicInteger(0);
        this.nThrows = i;
        this.value = 0;
        this.n_proc = n_proc;
    }

    public double getPi() {
        ExecutorService executor = Executors.newWorkStealingPool(this.n_proc);
        for (int i = 1; i <= nThrows; i++) {
            Runnable worker = new MonteCarlo();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        value = 4.0 * nAtomSuccess.get() / nThrows;
        return value;
    }
}

public class Assignment102 {
    public static void main(String[] args) {
        int n_total = 100000000;
        int n_proc = 7;

        PiMonteCarlo PiVal = new PiMonteCarlo(n_total, n_proc);

        long startTime = System.currentTimeMillis();
        double value = PiVal.getPi();
        long stopTime = System.currentTimeMillis();

        System.out.println("Valeur approché: " + value);
        System.out.println("Erreur: " + String.format("%e", Math.abs((value - Math.PI) / Math.PI)));
        System.out.println("N total: " + n_total);
        System.out.println("Nombre process: " + n_proc);
        System.out.println("Temps d'execution: " + (stopTime - startTime) + "ms");


        try {
            // Code tiré d'openclassroom
            // Création d'un fileWriter pour écrire dans un fichier
            FileWriter fileWriter = new FileWriter("./out_ass102_g26_4c.txt", true);

            // Création d'un bufferedWriter qui utilise le fileWriter
            BufferedWriter writer = new BufferedWriter(fileWriter);

            // ajout d'un texte à notre fichier
            writer.write(String.format("%e", Math.abs((value - Math.PI) / Math.PI)) + " " + n_total + " " + n_proc + " " + (stopTime - startTime));

            // Retour à la ligne
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}