package banco;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.*;
import java.time.LocalDate;
import java.io.IOException;

import excepciones.*;

public class SistemaBancoAD {

    private final Random random = new Random();

    private final String nombre;
    private final Logger logger; 
    private final List<ClienteBanco> clientes;

    public SistemaBancoAD(String nombre) {
        this.nombre = nombre;
        this.logger = Logger.getLogger(nombre);
        this.clientes = new ArrayList<>();

        int numClientes = random.nextInt(20);

        try {
            for(int i=1; i<=numClientes; ++i) {
                clientes.add(
                    new ClienteBanco(
                        i,
                        random.nextInt(10_000_000)
                        + random.nextInt(99)/100
                    )
                );
            }
        } 
        catch(Matricula28InvalidaException e) {
            throw new IllegalStateException(
                "inicializacion invalida", e
            );
        }
    }

    public void simularOperaciones(LocalDate fecha) throws IOException {

        try(var archivo = new LogPorFecha(nombre, fecha)) {
            
            logger.addHandler(archivo.getHandler());
 
            logger.info(
                "Enlistando errores de las transacciones"
                + "efectuadas el dia " + fecha.toString()
                + " en el banco " + nombre
            );

            int numTransacciones = random.nextInt(30);
            for(int i=0; i<numTransacciones; ++i) {
                try {
                    int matricula = random.nextInt(clientes.size()*3)-clientes.size();

                    if(matricula < 1) {
                        throw new Matricula28InvalidaException(
                            "Intentando efectuar operaciones sobre un"
                            + "numero de matricula invalido: " + matricula
                        );
                    }

                    ClienteBanco cliente = clientes
                        .stream()
                        .filter(u -> u.getMatricula() == matricula)
                        .findFirst()
                        .orElseThrow(
                            () -> new Usuario4122NoEncontradoException(
                                    "usuario con matricula "
                                    + matricula + 
                                    " no encontrado"
                                )
                        );

                    cliente.depositar(random.nextDouble()*10_000);
                    double retiro = random.nextDouble()*10_000;
                    if(cliente.getSaldoActual() - retiro < 0) {
                        throw new Saldo05InsuficienteException(
                            "usuario con matricula "
                            + matricula
                            + " no presento el saldo suficiente"
                            + " para efectuar un retiro de $"
                            + retiro
                        );
                    }
                } catch(ExceptionDavilaBase e) {
                    logger.info("ERROR: " + e.getMessage());
                }
            }

            logger.removeHandler(archivo.getHandler());

        } 
    }
}
