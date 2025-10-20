import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.io.IOException;

import banco.SistemaBancoAD;

class Tests {
   
    void simularOperaciones(String nombreBanco) {
        var banco = new SistemaBancoAD(nombreBanco);
        LocalDate fecha = LocalDate.of(2025, 10, 20); 
        try { 
            for(int i=0; i<3; ++i) {
                banco.simularOperaciones(fecha.plusDays(i));
            }
        } catch(IOException e) {
            fail("Error de Entrada y Salida: " + System.getProperty("user.dir") +e);
        }
    }

    @Test
    void BancoBanorte() {
        simularOperaciones("Banorte");
    }

    @Test
    void BancoBanregio() {
        simularOperaciones("Banregio");
    }

    @Test
    void BancoBBVA() {
        simularOperaciones("BBVA"); 
    }

}
