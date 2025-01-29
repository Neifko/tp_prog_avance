package tp.calcul_pi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Approximates PI using the Monte Carlo method.  Demonstrates
 * use of Callables, Futures, and thread pools.
 */
public class Pi {
    public static void main(String[] args) throws Exception {
        long total = 0;
        int[] n_total = {12000, 12000000, 120000000};
        int n_proc = 1;

        for (int k : n_total) {
            for (n_proc = 1; n_proc <= 6; n_proc++) {
                for (int j = 0; j < 10; j++) {
                    total = new Master().doRun(k / n_proc, n_proc);

                }
            }
        }

    }
}

/**
 * Creates workers to run the Monte Carlo simulation
 * and aggregates the results.
 */
class Master {
    public long doRun(int totalCount, int numWorkers) throws InterruptedException, ExecutionException {

        long startTime = System.currentTimeMillis();

        // Create a collection of tasks
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        for (int i = 0; i < numWorkers; ++i) {
            tasks.add(new Worker(totalCount));
        }

        // Run them and receive a collection of Futures
        ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
        List<Future<Long>> results = exec.invokeAll(tasks);
        long total = 0;

        // Assemble the results.
        for (Future<Long> f : results) {
            // Call to get() is an implicit barrier.  This will block
            // until result from corresponding worker is ready.
            total += f.get();
        }
        double pi = 4.0 * total / totalCount / numWorkers;

        long stopTime = System.currentTimeMillis();

        System.out.println("\nValeur approché: " + pi);
        System.out.println("Erreur: " + String.format("%e", (Math.abs((pi - Math.PI)) / Math.PI)));

        System.out.println("N total: " + totalCount * numWorkers);
        System.out.println("Nombre process: " + numWorkers);
        System.out.println("Temps d'execution: " + (stopTime - startTime) + "ms");

        try {
            // Code tiré d'openclassroom
            // Création d'un fileWriter pour écrire dans un fichier
            FileWriter fileWriter = new FileWriter("./out_pimw_g26_4c.txt", true);

            // Création d'un bufferedWriter qui utilise le fileWriter
            BufferedWriter writer = new BufferedWriter(fileWriter);

            // ajout d'un texte à notre fichier
            writer.write(String.format("%e", (Math.abs((pi - Math.PI)) / Math.PI)) + " " + (totalCount * numWorkers) + " " + numWorkers + " " + (stopTime - startTime));

            // Retour à la ligne
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        exec.shutdown();
        return total;
    }
}

/**
 * Task for running the Monte Carlo simulation.
 */
class Worker implements Callable<Long> {
    private int numIterations;

    public Worker(int num) {
        this.numIterations = num;
    }

    @Override
    public Long call() {
        long circleCount = 0;
        Random prng = new Random();
        for (int j = 0; j < numIterations; j++) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if ((x * x + y * y) < 1) ++circleCount;
        }
        return circleCount;
    }
}
