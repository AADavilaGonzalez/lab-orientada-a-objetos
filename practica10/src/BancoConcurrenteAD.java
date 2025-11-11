/*
 * Sincronizacion de acceso a cuentas bancarias
 * Implementacion patron Producto Consumidor
 * Buffer Compartido con tamaño basado en los utimos digitos de matricula
 * Multiples productores y consumidores
 * Uso de: syncronized, wait(), notify()
 * manejo de deadlocks y race conditions
 * Uso de Executor Service
 * Programa Principal que enlace multiples hilos concurrentes
 * Analisis de rendimiento y multiples logs de ejecucion
 * Documentacion sobre estrategias de sincronizacion utilizadas
 */

import java.util.ArrayList;
import java.util.List;

public class BancoConcurrenteAD {
    private final List<CuentaBancaria> cuentas = new ArrayList<>();
    private final BufferCompartido buffer;
    private final int NUM_CAJEROS = 3;
    private final int NUM_CLIENTES = 5;
    private final int TAM_BUFFER = 10;
    private final int OPERACIONES = 50;

    public BancoConcurrenteAD() {
        for (int i = 0; i < 5; i++) {
            cuentas.add(new CuentaBancaria("Cuenta_" + (i+1), 1000));
        }
        buffer = new BufferCompartido(TAM_BUFFER);
    }

    public void iniciarSimulacion() {
        List<Thread> cajeros = new ArrayList<>();
        List<Thread> clientes = new ArrayList<>();
        for (int i = 0; i < NUM_CAJEROS; i++) {
            Thread cajero = new CajeroThread28("Cajero_" + (i+1), cuentas, buffer);
            cajeros.add(cajero);
            cajero.start();
        }
        for (int i = 0; i < NUM_CLIENTES; i++) {
            Thread cliente = new Thread(new ClienteRunnable05("Cliente_" + (i+1), cuentas, buffer, OPERACIONES));
            clientes.add(cliente);
            cliente.start();
        }
        for (Thread cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                System.out.println("Hilo cliente interrumpido: " + cliente.getName());
            }
        }
        buffer.finalizarProduccion();
        for (Thread cajero : cajeros) {
            try {
                cajero.join();
            } catch (InterruptedException e) {
                System.out.println("Hilo cajero interrumpido: " + cajero.getName());
            }
        }
        System.out.println("\n--- Estado final de las cuentas ---");
        for (CuentaBancaria cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }

    static class CuentaBancaria {
        private final String nombre;
        private int saldo;
        public CuentaBancaria(String nombre, int saldoInicial) {
            this.nombre = nombre;
            this.saldo = saldoInicial;
        }
        public synchronized void depositar(int monto) {
            saldo += monto;
        }
        public synchronized boolean retirar(int monto) {
            if (saldo >= monto) {
                saldo -= monto;
                return true;
            }
            return false;
        }
        public String toString() {
            return nombre + ": $" + saldo;
        }
    }

    static class BufferCompartido {
        private final List<Integer> buffer;
        private final int capacidad;
        private boolean produccionFinalizada = false;
        public BufferCompartido(int capacidad) {
            this.capacidad = capacidad;
            this.buffer = new ArrayList<>();
        }

        public synchronized void agregar(int valor) throws InterruptedException {
            while (buffer.size() >= capacidad) {
                wait();
            }
            buffer.add(valor);
            notifyAll();
        }

        public synchronized Integer retirar() throws InterruptedException {
            while (buffer.isEmpty() && !produccionFinalizada) {
                wait();
            }
            if (buffer.isEmpty() && produccionFinalizada) {
                return null;
            }
            int valor = buffer.remove(0);
            notifyAll();
            return valor;
        }
        public synchronized int getSize() {
            return buffer.size();
        }
        public synchronized void finalizarProduccion() {
            produccionFinalizada = true;
            notifyAll();
        }
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("Simulación de banco concurrente");
        BancoConcurrenteAD banco = new BancoConcurrenteAD();
        banco.iniciarSimulacion();
    }
}
