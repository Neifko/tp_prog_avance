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
        int[] n_total = {12000, 12000000, 120000000}; // 12000, 12000000, 120000000
        int n_total_faible = 25000000; // 15000000, 25000000
        int[] n_proc = {1, 2, 3, 4, 5, 6, 8, 10, 12}; // 1, 2, 3, 4, 5, 6, 8, 10, 12
        String filename = "./out_pimw_g26_4c_faible.txt"; // "./out_pimw_g26_4c.txt" forte | "./out_pimw_g26_4c_faible.txt" faible

        boolean scalabilite_forte = false;

        if (scalabilite_forte) {
            // Scalabilité forte
            for (int k : n_total) {
                for (int n : n_proc) {
                    for (int j = 0; j < 10; j++) {
                        total = new Master().doRun(k / n, n, filename);

                    }
                }
            }
        } else {
            // scalabilite faible
            for (int n : n_proc) {
                for (int j = 0; j < 10; j++) {
                    total = new Master().doRun(n_total_faible, n, filename);

                }
            }

        }


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
