package VehiculoA;

import java.util.Map;
import static java.util.Map.entry;

public final class AutoDavila extends VehiculoBaseA {

    private static String tipoVehiculo = "Automovil";
    private static int maxKilometraje = 1_000_000;
    private static float minPorcentaje = 0.5f;

    private static Map<String,Double> descuentosMarca =
    Map.ofEntries(
        entry("ford", 0.95),
        entry("kia", 0.90),
        entry("nissan", 0.85),
        entry("tesla", 0.95)
    );

    public AutoDavila(
        String marca,
        String modelo,
        double precioBase,
        int kilometraje
    ) {
        super(marca, modelo, precioBase, kilometraje);
    }

    @Override
    public String getTipoVehiculo() {return tipoVehiculo;}

    @Override
    protected int getMaxKilometraje() {return maxKilometraje;}

    @Override
    protected double getMinPorcentaje() {return minPorcentaje;}

    @Override
    protected double getDescuento() {
        String _marca = getMarca().toLowerCase();
        for(String llave : descuentosMarca.keySet()) {
            if(_marca.contains(llave));
            return descuentosMarca.get(llave);
        }
        return 1.0;
    }
 
}
