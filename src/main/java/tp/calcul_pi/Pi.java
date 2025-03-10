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
import tp.calcul_pi.Worker;

/**
 * Approximates PI using the Monte Carlo method.  Demonstrates
 * use of Callables, Futures, and thread pools.
 */
public class Pi {
    public static void main(String[] args) throws Exception {
        long total = 0;
        int[] n_total = {120000000}; // 12000, 12000000, 120000000 ,1200000000
        int n_total_faible = 120000000; // 15000000, 25000000
        int[] n_proc = {1, 2, 3, 4, 5, 6, 8}; // 1, 2, 3, 4, 5, 6, 8, 10, 12
        String filename = "./out_pimw_mm_4c.txt"; // "./out_pimw_g26_4c.txt" forte | "./out_pimw_g26_4c_faible.txt" faible

        boolean scalabilite_forte = true;

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


