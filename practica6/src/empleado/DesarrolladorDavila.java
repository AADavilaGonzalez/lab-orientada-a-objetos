package empleado;

import datos.EvaluacionNormal;
import interfaces.Promovible4122;

public class DesarrolladorDavila extends EmpleadoAD
    implements Promovible4122<GerenteDavila> {

    private double horasTrabajadas = 0;
    public double getHorasTrabajadas() {return horasTrabajadas;}

    public DesarrolladorDavila(String nombre, double sueldoBase) {
        super(nombre, sueldoBase);
    }

    public String getPuesto() {return "Desarrollador";}
    public double getBonificacionTope() {return 10_000;}
    public void trabajar() {horasTrabajadas+=8;}

    public EvaluacionNormal evaluar() {
        return new EvaluacionNormal(
            Math.min(horasTrabajadas/48d, 1d)
        );
    }

    public GerenteDavila promover() {
        return new GerenteDavila(nombre, sueldoBase);
    }
}
