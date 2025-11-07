/*
 *  Implementacion Comparable
 *  --Comparador Personalizado con difentes
 *  criterios de ordenamiento
 *  --Metodos que demuesten
 *  * Busqueda y filtrado usando Streams
 *  * Operaciones CRUD
 *  * Manejo de Iteradores
 * */

import java.time.LocalDate;
import java.util.Comparator;

import static util.ArgCheck.*;

record IdLibro(int id) implements Comparable<IdLibro>{
    private static int siguienteId = 1;

    public static IdLibro generar() {
        return new IdLibro(siguienteId++);
    }

    @Override
    public int compareTo(IdLibro id) {
        return Integer.compare(this.id, id.id);
    }
}

class DatosLibro {
    public IdLibro id;
    public String titulo;
    public String categoria;
    public Integer inventario;
    public Integer unidadesPrestadas;
    public Integer unidadesDisponibles;
    public LocalDate fechaDePublicacion;
}

class Libro2805 implements Comparable<Libro2805> {

    private final IdLibro id;
    private String titulo;
    private String categoria;
    private int inventario;
    private int unidadesPrestadas;
    private LocalDate fechaDePublicacion;

    public Libro2805(
        String titulo,
        String categoria,
        int inventario, 
        LocalDate fechaDePublicacion
    ) {
        this.id = IdLibro.generar();
        this.titulo = argNoVacio(titulo, "titulo");
        this.categoria = argNoVacio(categoria, "categoria");
        this.inventario = argMayorOIgualA(inventario, "inventario", 0);
        this.unidadesPrestadas = 0;
        this.fechaDePublicacion = argNoNulo(fechaDePublicacion, "fechaDePublicacion");
    }

    public IdLibro getId() { return id;}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        this.titulo = argNoVacio(titulo, "titulo");
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) {
        this.categoria = argNoVacio(categoria, "categoria");
    }

    public int getInventario() { return inventario; }
    public void setInventario(int unidades) {
        unidades = argMayorOIgualA(unidades, "unidades", 0);
        if(unidades < unidadesPrestadas) {
            throw new OperacionInvalidaException(
                "Las unidades en el inventario no pueden"
                + "ser menores que las prestadas"
            );
        }
        this.inventario = unidades;
    }

    public int getUnidadesPrestadas() { return unidadesPrestadas; }
    public void setUnidadesPrestadas(int unidades) {
        unidades = argMayorOIgualA(unidades, "unidades", 0);
        if(unidades > inventario) {
            throw new OperacionInvalidaException(
                "Las unidades prestadas no pueden"
                + "ser mayores al inventario"
            );
        }
        this.unidadesPrestadas = unidades;
    }

    public int getUnidadesDisponibles() {
        return inventario - unidadesPrestadas;
    }

    public LocalDate getFechaDePublicacion() { return fechaDePublicacion; }
    public void setFechaDePublicacion(LocalDate fecha) {
        this.fechaDePublicacion = argNoVacio(fecha, "fecha");
    }

    @Override
    public int compareTo(Libro2805 libro) {
        return id.compareTo(libro.id);
    }

    public static final Comparator<Libro2805> compararPorTitulo =
        Comparator.comparing(
            Libro2805::getTitulo,
            String.CASE_INSENSITIVE_ORDER
        );

    public static final Comparator<Libro2805> compararPorCategoria =
        Comparator.comparing(
            Libro2805::getTitulo,
            String.CASE_INSENSITIVE_ORDER
        );

    public static final Comparator<Libro2805> compararPorInventario =
        Comparator.comparingInt(Libro2805::getInventario);

    public static final Comparator<Libro2805> compararPorUnidadesPrestadas =
        Comparator.comparingInt(Libro2805::getUnidadesPrestadas);

    public static final Comparator<Libro2805> compararPorUnidadesDisponibles = 
        Comparator.comparingInt(Libro2805::getUnidadesDisponibles);

    public static final Comparator<Libro2805> compararPorFechaDePublicion =
        Comparator.comparing(Libro2805::getFechaDePublicacion);

}
