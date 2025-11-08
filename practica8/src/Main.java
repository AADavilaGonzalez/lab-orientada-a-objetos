
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import static util.FmtIO.*;

class App {
    
    private enum SIGMENU {
        CONTINUAR,
        REGRESAR
    }

    private boolean corriendo;
    private BibliotecaA4122 biblioteca;

    public App() {

        var biblioteca = new BibliotecaA4122();

        biblioteca.agregarLibro(new Libro2805(
            "Harry Pepe II: La Camara Oculta",
            "Aventura", 10, LocalDate.of(1999, 10, 23)
        ));

        biblioteca.agregarLibro(new Libro2805(
            "El Señor de las Pulseras",
            "Fantasia", 5, LocalDate.of(1941, 4, 3)
        ));

        biblioteca.agregarLibro(new Libro2805(
            "Una cancion de Llamas y Viento",
            "Fantasia", 3, LocalDate.of(1997, 7, 17)
        ));

        biblioteca.agregarLibro(new Libro2805(
            "Atardecer: Lobos y Vampiros",
            "Drama", 15, LocalDate.of(2005, 5, 10)
        ));

        biblioteca.agregarLibro(new Libro2805(
            "Cómo programar en C",
            "Academico", 2, LocalDate.of(1989, 1, 29)
        ));

        Usuario aldo = new Usuario("Aldo");
        Usuario maria = new Usuario("Maria");

        biblioteca.agregarUsuario(aldo);
        biblioteca.agregarUsuario(maria);

        this.corriendo = true;
        this.biblioteca = biblioteca;
    }

    public void run() {
        while(this.corriendo) { mostrarMenu(); }
    }

    public void mostrarMenu() {
        clear();
        println(
            "Sistema de Organizacion Bibliotecario",
            "-> Elija una opción:",
            "1. Consultar Libros",
            "2. Agregar libro",
            "3. Realizar prestamo",
            "4. Liberar prestamo",
            "5. Benchmark busqueda",
            "6. Salir"
        );

        prompt();
        var op = scanInt();
        if(op == null || op < 1 || op > 6) {
            clear();
            return;
        }

        switch(op) {
            case 1:
                while(consultarLibros() != SIGMENU.REGRESAR){}
                break;
            case 2:
                agregarLibro();
                break;
            case 3:
                realizarReserva();
                break;
            case 4:
                liberarReserva();
                break;
            case 5:
                benchmarkBusqueda();
                break;
            case 6:
                corriendo = false;
                break;
        }
    }

    public SIGMENU consultarLibros() {
        clear();
        println(
            "Seleccione una opcion:",
            "1. Mostrar todos",
            "2. Buscar por titulo",
            "3. Buscar por categoria",
            "4. Buscar por fecha de publica",
            "5. Regresar"
        );

        prompt();
        var op = scanInt();
        if(op == null || op < 1 || op > 5) {
            return SIGMENU.CONTINUAR;
        }

        Iterator<Libro2805> iter;
        DatosLibro datos = new DatosLibro();
        
        switch(op) {
            case 2:
                datos.titulo = getLine();
                break;
            case 3:
                datos.categoria = getLine();
                break;
            case 4:
                try {
                    datos.fechaDePublicacion = LocalDate.parse(getLine());
                } catch(DateTimeParseException e) {
                    println("Fromato: YYYY-MM-DD");
                    return SIGMENU.CONTINUAR;
                }
                break;
            case 5:
                return SIGMENU.REGRESAR;
        }
        iter = biblioteca.buscarLibros(datos);

        while(iter.hasNext()) {
            println(iter.next().toString());
            getLine();
        }
        
        return SIGMENU.CONTINUAR;
    }

    public void agregarLibro() {
        clear();
        var datos = new DatosLibro();

        println("Ingrese los siguientes datos:");
        print("Titulo"); datos.titulo = getLine(
            (s) -> !s.isEmpty(), "El titulo no puede ser vacio"
        );
        print("Categoria"); datos.categoria = getLine(
            (s) -> !s.isEmpty(), "La categoria no puede ser vacia"
        );
        print("Inventario"); datos.inventario = getInt(
            (i) -> i > 0, "El inventario debe ser mayor a 0"
        );
        println("Feacha de Publicacion: (YYYY-MM-DD):");
        datos.fechaDePublicacion = getLocalDate(
            (f) -> 
                f.compareTo(LocalDate.of(1500, 1, 1)) == 1
            &&  f.compareTo(LocalDate.now()) == -1,
            "La fecha debe ser mayor a 1-1-1500 y menor que la fecha actual"
        );

        biblioteca.agregarLibro(
            new Libro2805(
                datos.titulo,
                datos.categoria,
                datos.inventario,
                datos.fechaDePublicacion
            )
        ); 
        return;
    }

    public void realizarReserva() {
        clear();
    
        var usuarios = biblioteca.getUsuarios();
        println("Elija el usuario que desea hacer el prestamo:");
        for(int i=0; i<usuarios.length; ++i) {
            println(String.format(
                "%d. %s", i+1, usuarios[i].getNombre()
            ));
        }

        int i = getInt(1, usuarios.length);
        var usuario = usuarios[i-1];
        
        println(
            "Introduzca el id del libro a alquilar",
            "o cualquier otra cosa si no lo sabe"
        );

        Libro2805 libro;
        try {
            libro = biblioteca.buscarLibro(
                new IdLibro(getInt())
            );
        } catch (OperacionInvalidaException e) {
            println(e.getMessage());
            getLine();
            return;
        }

        var reserva = new Reserva(
            usuario.getId(), libro.getId()
        );

        biblioteca.agregarReserva(reserva);
        println("Reserva eliminada exitosamente");
        getLine();
    }

    public void liberarReserva() {
        clear();

        var reservas = biblioteca.getReservas();

        println("Elija la reserva que quiere liberar");
        for(int i=0; i<reservas.length; ++i) {
            var usuario = biblioteca.buscarUsuario(reservas[i].idUsuario());
            var libro = biblioteca.buscarLibro(reservas[i].idLibro());
            println(String.format(
                "%s : %s", usuario.getNombre(), libro.getTitulo()
            ));
        }

        int i = getInt(1, reservas.length);
        var reserva = reservas[i-1];

        biblioteca.eliminarReserva(reserva);
        println("Reserva elminada exitosamente");
        getLine();
    }

    public void benchmarkBusqueda() {
        clear();

        var datos = new DatosLibro();
        datos.titulo = "Este libro no existe entonces la";
        datos.categoria = "funcion recorrera toda la lista";
        var inicio = System.nanoTime();
        biblioteca.buscarLibros(datos);
        var fin = System.nanoTime();
        println(String.format(
            "Nanosegundos elapsados en buscar un libro: %d", fin - inicio)
        );
        getLine();
    }

}

class Main {

    public static void main(String args[]) {  
        App app = new App();
        app.run();
    }

}
