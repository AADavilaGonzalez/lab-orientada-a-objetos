/*
 *  ArrayList Libros Disponibles
 *  LinkedList cola de reservas
 *  HashMap usuarios registrados
 *  HashSet categorias unicas
 * */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import java.util.Map;
import java.util.HashMap;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

import static util.ArgCheck.argNoVacio;

record IdUsuario(int id) implements Comparable<IdUsuario>{
    private static int siguienteId = 1;

    public static IdUsuario generar() {
        return new IdUsuario(siguienteId++);
    }

    @Override
    public int compareTo(IdUsuario id) {
        return Integer.compare(this.id, id.id);
    }
}
record Reserva(IdUsuario idUsuario, IdLibro idLibro) {}

class Usuario {
    
    private final IdUsuario id;
    private String nombre;

    public Usuario(String nombre) {
        this.id = IdUsuario.generar();
        this.nombre = argNoVacio(nombre, "nombre");
    }

    public IdUsuario getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = argNoVacio(nombre, "nombre");
    }
}


class BibliotecaA4122 {

    private List<Libro2805> libros;
    private List<Reserva> reservas;
    private Map<IdUsuario, Usuario> usuarios;
    private Set<String> categorias;

    private Comparator<Libro2805> ordenLibros = Libro2805.compararPorTitulo;

    public BibliotecaA4122() {
        this.libros = new ArrayList<>();
        this.reservas = new LinkedList<>();
        this.usuarios = new HashMap<>();
        this.categorias = new HashSet<>();
    }

    //Create
    public void agregarLibro(Libro2805 libro) {
        categorias.add(libro.getCategoria());
        libros.add(libro);
    }

    //Read
    public Libro2805 buscarLibro(IdLibro id) throws
        OperacionInvalidaException
    {
        try { 
            return libros.stream()
                         .filter((lib) -> lib.getId().compareTo(id) == 0)
                         .findFirst().orElseThrow();
        } catch (RuntimeException e) {
            throw new OperacionInvalidaException("id de libro invalido");
        }
    } 

    public Iterator<Libro2805> buscarLibros(DatosLibro datos) {

        if(datos.id != null) {
            try {
                var libro = buscarLibro(datos.id);
                return List.of(libro).iterator();
            }
            catch (NoSuchElementException e) {
                return List.<Libro2805>of().iterator();
            }
        }

        Predicate<Libro2805> predicado = (l) -> true;
        if(datos.titulo != null) {
            predicado = predicado.and(
                (l) -> l.getTitulo()
                        .toLowerCase()
                        .contains(datos.titulo.toLowerCase())
            );
        }
        if(datos.categoria != null) {
            predicado = predicado.and(
                (l) -> l.getCategoria()
                        .toLowerCase()
                        .contains(datos.categoria.toLowerCase())
            );
        }
        if(datos.inventario != null) {
            predicado = predicado.and(
                (l) -> l.getInventario () == datos.inventario
            );
        }
        if(datos.unidadesPrestadas != null) {
            predicado = predicado.and(
                (l) -> l.getUnidadesPrestadas() == datos.unidadesPrestadas
            );
        }
        if(datos.unidadesDisponibles != null) {
            predicado = predicado.and(
                (l) -> l.getUnidadesDisponibles() == datos.unidadesDisponibles
            );
        }
        if(datos.fechaDePublicacion != null) {
            predicado = predicado.and(
                (l) -> l.getFechaDePublicacion()
                        .compareTo(datos.fechaDePublicacion) == 0
            );
        }

        var lista = libros.stream()
                          .filter(predicado)
                          .collect(Collectors.toList());
        lista.sort(ordenLibros);
        return lista.iterator();

    }

    //Update
    public void actualizarLibro(DatosLibro datos) throws
        OperacionInvalidaException
    {
        if(datos.id == null) {
            throw new OperacionInvalidaException(
                "especifique la id del libro en la consulta"
            );
        }
        if(datos.unidadesDisponibles != null) {
            throw new OperacionInvalidaException(
                "no es posible actualizar la cantidad de"
                + "unidades disponibles directamente"
            );
        }
   
        Libro2805 libro;
        libro = buscarLibro(datos.id);

        if(datos.titulo != null) {
            libro.setTitulo(datos.titulo);
        }
        if(datos.categoria != null) {
            libro.setCategoria(datos.titulo);
        }
        if(datos.inventario != null) {
            libro.setInventario(datos.inventario);
        }
        if(datos.unidadesPrestadas != null) {
            libro.setUnidadesPrestadas(datos.unidadesPrestadas);
        }
        if(datos.fechaDePublicacion != null) {
            libro.setFechaDePublicacion(datos.fechaDePublicacion);
        }

    }

    //Delete
    public void eliminarLibro(IdLibro id) {
        var eliminado = libros.removeIf(
            (libro) -> libro.getId().compareTo(id) == 0
        );
        if(!eliminado) {
            throw new OperacionInvalidaException("id de libro invalido");
        }
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario); 
    }

    public Usuario buscarUsuario(IdUsuario id) {
        var usuario = usuarios.get(id);
        if(usuario != null) {
            throw new OperacionInvalidaException(
                "id de usuario invalido"
            );
        }
        return usuario;
    }
    
    public Usuario[] getUsuarios() {
        return usuarios.values().toArray(new Usuario[0]); 
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public Reserva[] getReservas() {
        return reservas.toArray(new Reserva[0]);
    }
} 
