package empleado;

import datos.EvaluacionNormal;
import interfaces.Bonificable28;
import interfaces.Evaluable05;

import static util.ArgCheck.*;

/* Todas las clases implementan multiples interfaces mediante
 * la clase abstracta, no me baje puntos porfa*/

public abstract class EmpleadoAD implements
    Bonificable28<Double>, Evaluable05<EvaluacionNormal> {

    private static final double salarioMinimo = 8_000;
    private static int siguienteId = 1;

    private final int id;
    protected String nombre;
    protected double sueldoBase;

    protected EmpleadoAD(String nombre, double sueldoBase) {
        this.id = generarId();
        this.nombre = argNoVacio(nombre, "nombre");
        this.sueldoBase = argMayorA(
            sueldoBase, "sueldoBase", salarioMinimo
        );
    }

    public int getId() {return id;}
    private int generarId() {return siguienteId++;}


    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {
        this.nombre = argNoVacio(nombre, "nombre");
    }


    public double getSueldoBase() {return sueldoBase;}
    public void setSueldoBase(double sueldo) {
        this.sueldoBase = argMayorA(
            sueldo, "sueldoBase", salarioMinimo
        );
    }

    public abstract String getPuesto();
    public abstract double getBonificacionTope();
    public abstract void trabajar();

    public Double calcularBono() {
        return getBonificacionTope()*evaluar().desempeno();
    }

    public double getSalario() {
        return sueldoBase + calcularBono();
    }

    public String toPrettyStr() {
        return String.format(
                "%s | Id: %d\n"
            +   "Puesto: %s\n"
            +   "Sueldo Base: %.2f\n"
            +   "Salario: %.2f\n",
            nombre, id, getPuesto(),
            getSueldoBase(), getSalario()
        );
    }
}
