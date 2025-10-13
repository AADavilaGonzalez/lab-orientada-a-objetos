package empleado;

import datos.EvaluacionNormal;
import interfaces.Promovible4122;

public class VendedorDavila extends EmpleadoAD
    implements Promovible4122<GerenteDavila> {

    private int ventasCerradas = 0;
    public double getVentasCerradas() {return ventasCerradas;}

    public VendedorDavila(String nombre, double sueldoBase) {
        super(nombre, sueldoBase);
    }

    public String getPuesto() {return "Vendedor";}
    public double getBonificacionTope() {return 10_000;}
    public void trabajar() {ventasCerradas+=1;}

    public EvaluacionNormal evaluar() {
        return new EvaluacionNormal(
            Math.min(ventasCerradas/10d, 1d)
        );
    }

    public GerenteDavila promover() {
        return new GerenteDavila(nombre, sueldoBase);
    }
}
