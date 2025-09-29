
import VehiculoA.*;
import static util.FmtIO.println;

class Main {
    
    public static void main(String[] args) {
        println("Simulacion de Concecionaria de Vehiculos\n");

        println("Creando Concesionaria...");
        var c = new Concesionaria4122(4_000_000, 10);
        println("Estado Inicial...", c.getResumen(), "\n");
        
        println("Comprando Vehiculos..."); 
        c.comprarVehiculo(
            new AutoDavila(
                "Nissan",
                "Versa",
                500_000,
                10_000
            ),
            400_000
        );

        c.comprarVehiculo(
            new AutoDavila(
                "Tesla",
                "Roadster",
                2_000_000,
                600_000
            ),
            600_000
        );

        c.comprarVehiculo(
            new MotocicletaDavila(
                "Italika",
                "Z200",
                30_000,
                150_000
            ),
            17_000
        );

        c.comprarVehiculo(
            new MotocicletaDavila(
                "Harley Davidson",
                "Sporster",
                250_000,
                50_000
            ),
            210_000
        );

        c.comprarVehiculo(
            new CamionDavila(
                "BMW",
                "Camionson",
                1_500_000,
                700_000
            ),
            1_000_000
        );
        println("Estado Actual...", c.getResumen(), "\n");

        println("Vendiendo Autos...");
        c.venderVehiculos(v -> v instanceof AutoDavila);
        println("Estado Actual...", c.getResumen(), "\n");

        println("Vendiendo los Demas Vehiculos..");
        c.venderVehiculos(v -> true);
        println("Estado Final...", c.getResumen());
        
    }

}
