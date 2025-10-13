package empleado;

import java.util.List;
import java.util.ArrayList;

import datos.EvaluacionNormal;

public class GerenteDavila extends EmpleadoAD {

    private List<? extends EmpleadoAD> subordinados;
    public EmpleadoAD[] getSubordinados() {
        return subordinados.toArray(new EmpleadoAD[0]);
    }
    public void asignarSubordinados(List<? extends EmpleadoAD> empleados) {
        subordinados = empleados;
    }

    private int proyectosSupervisados = 0;
    public int getProyectosSupervisados() {return proyectosSupervisados;}

    public GerenteDavila(
        String nombre,
        double salarioBase,
        List<EmpleadoAD> subordinados
    ) {
        super(nombre, salarioBase);
        this.subordinados = subordinados;
    }

    public GerenteDavila(String nombre, double salarioBase) {
        this(nombre, salarioBase, new ArrayList<>());
    }

    public String getPuesto() {return "Gerente";}

    public EvaluacionNormal evaluar() {
        return new EvaluacionNormal(
            subordinados.size() == 0 ? 0d :
            subordinados.stream()
                        .mapToDouble(s -> s.evaluar().desempeno())
                        .sum()/subordinados.size()
        );
    }

    public double getBonificacionTope() {return 20_000;}
    public void trabajar() {proyectosSupervisados+=1;}

}
