import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import VehiculoA.*;

class Tests {
    
    @Test     
    void testAutoDavila() {
        var auto = new AutoDavila(
            "Modelo",
            "Especial",
            35,
            1
        );
        
        assertTrue(auto.getMarca() == "Modelo");

        assertTrue(auto.getModelo() == "Especial");

        assertThrows(IllegalArgumentException.class,
            () -> auto.setPrecioBase(0)
        );

        assertThrows(IllegalArgumentException.class,
            () -> auto.setKilometraje(-100)
        );

    }

    @Test     
    void testMotocicletaDavila() {
        var moto = new MotocicletaDavila(
            "Tecate",
            "Light",
            25,
            1
        );

        assertTrue(moto.getMarca() == "Tecate");

        assertTrue(moto.getModelo() == "Light");

        assertThrows(IllegalArgumentException.class,
            () -> moto.setPrecioBase(0)
        );

        assertThrows(IllegalArgumentException.class,
            () -> moto.setKilometraje(-100)
        );
    }

    @Test     
    void testCamionDavila() {
        var camion = new CamionDavila(
            "Blue",
            "Label",
            10_000,
            3
        );

        assertTrue(camion.getMarca() == "Blue");

        assertTrue(camion.getModelo() == "Label");

        assertThrows(IllegalArgumentException.class,
            () -> camion.setPrecioBase(0)
        );

        assertThrows(IllegalArgumentException.class,
            () -> camion.setKilometraje(-100)
        );
    }

}
