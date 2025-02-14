package tp.picuda;

import jcuda.*;
import jcuda.driver.*;
import static jcuda.driver.JCudaDriver.*;

public class PiGPU {
    public static void main(String[] args) {
        JCudaDriver.setExceptionsEnabled(true);
        cuInit(0);

        CUdevice device = new CUdevice();
        cuDeviceGet(device, 0);
        CUcontext context = new CUcontext();
        cuCtxCreate(context, 0, device);

        CUmodule module = new CUmodule();
        cuModuleLoad(module, "C:\\Users\\victor\\Documents\\programmation\\tp_prog_avance\\src\\main\\java\\tp\\picuda\\MonteCarloPiKernel.ptx");

        CUfunction function = new CUfunction();
        cuModuleGetFunction(function, module, "monteCarloPi");

        int totalIterations = 2000000000;
        int blockSize = 256;
        int gridSize = (totalIterations + blockSize - 1) / blockSize;

        // Allocation de la mémoire pour le tableau de résultats
        CUdeviceptr devCount = new CUdeviceptr();
        cuMemAlloc(devCount, Sizeof.DOUBLE * totalIterations);  // Allocation de mémoire pour le tableau

        Pointer kernelParameters = Pointer.to(
                Pointer.to(devCount),
                Pointer.to(new int[]{totalIterations})
        );

        // Lancement du noyau
        cuLaunchKernel(function,
                gridSize, 1, 1,
                blockSize, 1, 1,
                0, null,
                kernelParameters, null
        );
        cuCtxSynchronize();

        // Copie des résultats du GPU vers le CPU
        double[] hostCount = new double[totalIterations];
        System.out.println("Avant la copie de la valeur de count sur le CPU...");
        cuMemcpyDtoH(Pointer.to(hostCount), devCount, Sizeof.DOUBLE * totalIterations);

        // Réduction sur le CPU
        double count = 0;
        for (double c : hostCount) {
            count += c;
        }

        System.out.println("Valeur de count après la copie: " + count); // Affichage du premier élément pour débogage


        // Calcul de Pi
        double pi = 4.0 * count / totalIterations;
        System.out.println("Valeur approchée de Pi : " + pi);

        // Libération de la mémoire GPU
        cuMemFree(devCount);
        cuCtxDestroy(context);
    }
}
