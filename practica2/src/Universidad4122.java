import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Consumer;

class Universidad4122 {
    private List<EstudianteAD> estudiantes;

    public Universidad4122() {estudiantes = new ArrayList<>();}

    public void agregarEstudiante(EstudianteAD estudiante) {
        estudiantes.add(estudiante);
    }

    public void agregarEstudiante(String nombre, String carrera, int edad) {
        estudiantes.add(new EstudianteAD(nombre, carrera, edad));
    }

    public void agregarEstudiante(String nombre,
        String carrera, int edad, int sem
    ) {
        estudiantes.add(new EstudianteAD(nombre, carrera, edad, sem));
    }


    public <T> List<EstudianteAD> buscarEstudiantes(T valor,
       Function<EstudianteAD, T> getter 
    ) {
        List<EstudianteAD> aciertos = new ArrayList<>();
        for(var estudiante : estudiantes) {
            if(getter.apply(estudiante).equals(valor)) {
                aciertos.add(estudiante);
            }
        }
        return aciertos;
    }


    public static void mostrarEstudiantes(List<EstudianteAD> lista, 
        Consumer<EstudianteAD> print, String sep
    ) {
        for(var estudiante : lista) {
            print.accept(estudiante);
            System.out.print(sep);
        }
    }

    public static void mostrarEstudiantes(List<EstudianteAD> lista) {
       mostrarEstudiantes(lista, EstudianteAD::print, "\n"); 
    }

    public void mostrarEstudiantes() {
        mostrarEstudiantes(estudiantes, EstudianteAD::print, "\n");
    }
}
