package VehiculoA;

public final class CamionDavila extends VehiculoBaseA {

    private static String tipoVehiculo = "Camion";
    private static int maxKilometraje = 2_000_000;
    private static float minPorcentaje = 0.7f;

    public CamionDavila(
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
    protected double getDescuento() {return 1;}
 
}
