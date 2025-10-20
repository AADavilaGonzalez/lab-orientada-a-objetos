package banco;

import static util.ArgCheck.argMayorOIgualA;
import excepciones.Matricula28InvalidaException;

class ClienteBanco {
    private final int matricula;
    private double saldo;

    public ClienteBanco(int matricula, double saldoInicial) 
        throws Matricula28InvalidaException {

        if(matricula < 1) {
            throw new Matricula28InvalidaException(
                "la matricula debe ser mayor o igual a 1"
            );
        }
        this.matricula = matricula;
        this.saldo = saldoInicial < 0 ? 0 : saldoInicial;

    }

    public int getMatricula() { return this.matricula; }

    public double getSaldoActual() { return this.saldo; }
    public void depositar(double cantidad) {
        this.saldo += argMayorOIgualA(cantidad, "cantidad", 0d);
    }
    public void retirar(double cantidad) {
        this.saldo -= argMayorOIgualA(cantidad, "cantidad", 0d);
    }
}

