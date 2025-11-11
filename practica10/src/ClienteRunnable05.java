import java.util.List;
import java.util.Random;

public class ClienteRunnable05 implements Runnable {
    private final String nombre;
    private final List<BancoConcurrenteAD.CuentaBancaria> cuentas;
    private final BancoConcurrenteAD.BufferCompartido buffer;
    private final int operaciones;
    private final Random random = new Random();

    public ClienteRunnable05(
        String nombre,
        List<BancoConcurrenteAD.CuentaBancaria> cuentas,
        BancoConcurrenteAD.BufferCompartido buffer,
        int operaciones
    ) {
        this.nombre = nombre;
        this.cuentas = cuentas;
        this.buffer = buffer;
        this.operaciones = operaciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < operaciones; i++) {
            try {
                int monto = random.nextInt(200) + 1;
                BancoConcurrenteAD.CuentaBancaria cuenta = cuentas.get(random.nextInt(cuentas.size()));
                cuenta.depositar(monto);
                System.out.println(nombre + " depositó $" + monto + " en " + cuenta);
                buffer.agregar(monto);
                System.out.println(nombre + " agregó $" + monto + " al buffer. Tamaño actual: " + buffer.getSize());
                Thread.sleep(random.nextInt(50) + 10);
            } catch (InterruptedException e) {
                System.out.println(nombre + " interrumpido.");
                break;
            }
        }
        System.out.println(nombre + " terminó sus operaciones.");
    }
}
