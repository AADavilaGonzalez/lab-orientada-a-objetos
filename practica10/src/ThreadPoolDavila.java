import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDavila {
    private final ExecutorService pool;

    public ThreadPoolDavila(int numHilos) {
        pool = Executors.newFixedThreadPool(numHilos);
    }

    public void ejecutarTarea(Runnable tarea) {
        pool.execute(tarea);
    }

    public void cerrarPool() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}
