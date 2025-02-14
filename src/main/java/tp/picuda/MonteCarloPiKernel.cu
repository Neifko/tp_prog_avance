#include <curand_kernel.h>

extern "C"
__global__ void monteCarloPi(double *count, int totalIterations) {
    int idx = blockIdx.x * blockDim.x + threadIdx.x; // linearisation de tableau

    if (idx >= totalIterations) return;

    curandState state;
    curand_init(idx, 0, 0, &state);

    double x = curand_uniform(&state);
    double y = curand_uniform(&state);

    if (x * x + y * y <= 1.0) {
        count[idx] = 1.0;  // Stocke les hits de chaque thread dans le tableau
    } else {
        count[idx] = 0.0;
    }
}
