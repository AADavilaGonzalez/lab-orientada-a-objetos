import java.util.List;
import java.util.Random;

public class CajeroThread28 extends Thread {
    private final String nombre;
    private final List<BancoConcurrenteAD.CuentaBancaria> cuentas;
    private final BancoConcurrenteAD.BufferCompartido buffer;
    private final Random random = new Random();

    public CajeroThread28(
        String nombre,
        List<BancoConcurrenteAD.CuentaBancaria> cuentas,
        BancoConcurrenteAD.BufferCompartido buffer
    ) {
        super(nombre);
        this.nombre = nombre;
        this.cuentas = cuentas;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer monto = buffer.retirar();
                if (monto == null) {
                    break;
                }
                BancoConcurrenteAD.CuentaBancaria cuenta = cuentas.get(random.nextInt(cuentas.size()));
                boolean exito = cuenta.retirar(monto);
                if (exito) {
                    System.out.println(nombre + " retiró $" + monto + " de " + cuenta);
                } else {
                    System.out.println(nombre + " intentó retirar $" + monto + " pero saldo insuficiente en " + cuenta);
                }
                Thread.sleep(random.nextInt(50) + 10);
            } catch (InterruptedException e) {
                System.out.println(nombre + " interrumpido.");
                break;
            }
        }
        System.out.println(nombre + " terminó sus operaciones.");
    }
}
