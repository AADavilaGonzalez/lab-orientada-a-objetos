import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class Encapsulamiento {
    @Test
    void testClienteDavila() {
        var cliente = new ClienteDavila(
            "Cliente de Prueba",
            "cliente@prueba.com",
            "5556575859",
            "Direccion Falsisima",
            LocalDate.of(2000, 1, 1)
        );

        assertTrue(
            Integer.parseInt(
                cliente.getNumeroDeCliente()
            ) > 0
        );

        var nombre = "otro nombre";
        cliente.setNombre(nombre);
        assertEquals(nombre, cliente.getNombre());

        var correo = "otro@correo.com";
        cliente.setCorreoElectronico(correo);
        assertEquals(correo, cliente.getCorreoElectronico());

        var telefono = "01010101001";
        cliente.setNumeroDeTelefono(telefono);
        assertEquals(telefono, cliente.getNumeroDeTelefono());

        var direccion = "direccion loca";
        cliente.setDireccion(direccion);
        assertEquals(direccion, cliente.getDireccion());

        var fecha = LocalDate.of(2003, 05, 28);
        cliente.setFechaDeNacimiento(fecha);
        assertEquals(fecha, cliente.getFechaDeNacimiento());
    }

    @Test
    void testCuentaBancaria2805() {    
        var cliente = new ClienteDavila(
            "Cliente de Prueba",
            "cliente@prueba.com",
            "5556575859",
            "Direccion Falsisima",
            LocalDate.of(2000, 1, 1)
        );

        cliente.abrirCuentaBancaria(100);
        var cuenta = cliente.getCuentasBancarias()[0];

        assertNotNull(cuenta.getNumeroDeCuenta());
        assertNotNull(cuenta.getFechaDeCreacion());
        assertNotNull(cuenta.getPropietario());
        cuenta.setCuentaActiva(false);
        assertFalse(cuenta.getCuentaActiva());
        assertEquals(cuenta.getBalance(), 100);

    }
}
