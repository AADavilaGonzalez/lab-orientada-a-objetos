package VehiculoA;

public final class MotocicletaDavila extends VehiculoBaseA {

    private static String tipoVehiculo = "Motocicleta";
    private static int maxKilometraje = 500_000;
    private static float minPorcentaje = 0.4f;

    public MotocicletaDavila(
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
    protected double getDescuento() {return 0.95;}
 
}
