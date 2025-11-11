
import java.util.function.Function;

import static util.FmtIO.*;

class Main {
    
    private Universidad4122 uni;

    public Main(boolean auto) {
        uni = new Universidad4122();
        if(!auto) return;
        uni.agregarEstudiante(
            "Aldo Davila",
            "LCC",
            22,
            5
        );
        uni.agregarEstudiante(
            "Roberto Sanchez",
            "LCC",
            20,
            5
        );
        uni.agregarEstudiante(
            "Luis Segobia",
            "LCC",
            20,
            5
        );
        uni.agregarEstudiante(
            "Cesar Davila",
            "Ing. Civil",
            20,
            2
        );
    }

    public Main() {this(true);}

    private void agregarEstudiante() {
        clear();
        println("Ingrese los datos de registro");

        setPrompt("Nombre: ");
        var nombre = getLine();

        setPrompt("Carrera: ");
        var carrera = getLine();

        setPrompt("Edad: ");
        var edad = getInt(8, 120);

        uni.agregarEstudiante(new EstudianteAD(nombre, carrera, edad));
        println("Estudiante Agregado Exitosamente");
        setPrompt("...");
        getLine();
        setPrompt("> ");
    }

    private void buscarEstudiantes() {
        clear();
        println(
            "Seleccine el criterio de busqueda",
            "1. Por nombre",
            "2. Por matricula",
            "3. Por carrera",
            "4. Por semestre",
            "5. Por edad"
        );
        int op = getInt(1,5);

        println("Ingrese el valor de la busqueda");
        Object val;
        if(op == 2 || op == 4 ||op == 5) {
            val = getInt();
        } else {
            val = getLine();
        }

        Function<EstudianteAD,Object> f = switch(op) {
            case 1  -> e->e.nombre;
            case 2  -> e->e.matricula;
            case 3  -> e->e.carrera;
            case 4  -> e->e.semestreActual;
            case 5  -> e->e.edad;
            default -> e->null;
        };

        var lista = uni.buscarEstudiantes(val, f);
        println("Se encontraron los siguientes resultados:\n");
        Universidad4122.mostrarEstudiantes(lista);
        getLine();
    }

    private void mostrarEstudiantes() {
        clear();
        System.out.println("Mostrando todos los estudiantes incritos\n");
        uni.mostrarEstudiantes();
        getLine();
    }

    private void run() {

        var corriendo = true;
        while(corriendo) {
            clear();
            println(
                "Sistema Generico de Gestion de Estudiantes",
                "1. Agregar Estudiante",
                "2. Buscar Estudiantes",
                "3. Mostrar Estudiantes",
                "4. Salir"
            );
            
            switch(scanInt()) {
                case 1:
                    agregarEstudiante();
                    break;
                case 2:
                    buscarEstudiantes();
                    break;
                case 3:
                    mostrarEstudiantes();
                    break;
                case 4:
                    corriendo = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {new Main().run();}
}
