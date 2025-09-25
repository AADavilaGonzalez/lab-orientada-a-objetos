import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class Funcionalidad {
    @Test
    void testClienteDavila() {
        var cliente1 = new ClienteDavila(
            "Cliente1",
            "cliente1@cocos.com",
            "8182838485",
            "Direccion Falsa 1",
            LocalDate.of(2000, 1, 1)
        );
        var cliente2 = new ClienteDavila(
            "Cliente2",
            "cliente2@pasta.com",
            "8687888990",
            "Direccion Falsa 2",
            LocalDate.of(2001,2,2)
        );

        assertEquals(
            Integer.parseInt(cliente1.getNumeroDeCliente()) + 1,
            Integer.parseInt(cliente2.getNumeroDeCliente())
        );
    }

    @Test
    void testCuentaBanaria2805() {
        var cliente = new ClienteDavila(
            "Cliente de Prueba",
            "cliente@prueba.com",
            "5556575859",
            "Direccion Falsisima",
            LocalDate.of(2000, 1, 1)
        );

        cliente.abrirCuentaBancaria(100);
        cliente.abrirCuentaBancaria(200);
        var cuentas = cliente.getCuentasBancarias();
        assertEquals(
           Integer.parseInt(cuentas[0].getNumeroDeCuenta()) + 1,
           Integer.parseInt(cuentas[1].getNumeroDeCuenta())
        );
        assertEquals(cuentas.length, 2);
        cuentas[0].transferir(100, cuentas[1]);
        cliente.cerrarCuentaBancaria(cuentas[0].getNumeroDeCuenta());
        assertEquals(cliente.getCuentasBancarias().length, 1);
    }
}
