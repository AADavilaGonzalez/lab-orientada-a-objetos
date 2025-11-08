/*
 * Leer un archivo csv de personas
 * Backup automatico con timestamp
 * Implica que:
 * El CLI se utiliza para modificar el archivo csv
 * App -> EditorCSV
 * */

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.time.Instant;

import static util.FmtIO.*;

class EditorCSVPersonas {

    private boolean corriendo;

    private GestorArchivosDavila4122 fs;
    private String nombreArchivo;
    private List<PersonaASerializable> personas;

    public EditorCSVPersonas() {
        
        this.corriendo = true;
        this.fs = new GestorArchivosDavila4122(".");
        this.nombreArchivo = null;
        this.personas = null;

    }

    public void run() {
        while(this.corriendo) { mostrarMenu(); }
    }

    public void guardar(String nombre) throws IOException {
        if(personas == null) { return; }

        fs.escribirArchivo(
            nombre, personas.stream().map(
                (p) -> PersonaASerializable.SerializarCSV(p)
            ).toList()
        );
    }

    private void mostrarMenu() {
        clear();
        
        println(
            "Gestor de archivos csv personas",
            "1. Abrir archivo",
            "2. Enlistar Personas",
            "3. Agregar Persona",
            "4. Guardar y Salir"
        );

        prompt();
        var op = scanInt();
        if(op == null || op < 1 || op > 3) {
            clear();
            return;
        }

        switch(op) {
            case 1:
                abrirArchivo();
                break;
            case 2:
                enlistarPersonas();
                break;
            case 3:
                agregarPersona();
                break; 
            case 4:
                guardarAlSalir();
                corriendo = false;
                break;
        }

    }

    private void abrirArchivo() {
        println("Nombre del Archivo:");
        var nombre = getLine();

        List<String> lineas;
        try {
            lineas = fs.leerArchivo(nombre);
        } catch(IOException e) {
            println(e.getMessage());
            getLine();
            return;
        }
        
        nombreArchivo = nombre;
        personas = lineas.stream().map(
            (l) -> PersonaASerializable.DeserializarCSV(l)
        ).collect(Collectors.toList());

        println("Archivo cargado exitosamente");
        getLine();

    }

    private void enlistarPersonas() {
        clear();
        println(String.format("Personas (%s)\n", nombreArchivo));
        personas.forEach((p) -> println(toString() + "\n"));
        getLine();
    }

    private void agregarPersona() {
        if(personas == null) {
            println("Cargue un archivo antes de agregar una persona");
            getLine();
            return;
        }

        print("nombre"); var nombre = getLine();
        print("preferencia"); var preferencia = getLine();
        print("edad"); var edad = getInt(1, 150);

        personas.add(
            new PersonaASerializable(
                nombre,
                preferencia,
                edad
            )
        );

        try {
            guardar(String.format(
                ".backups/%d_%s.bak",
                Instant.now().toEpochMilli(),
                nombreArchivo
            ));
        } catch(IOException e) {
            println("Hubo un error al escribir el backup de esta accion");
            getLine();
        }

    }

    private void guardarAlSalir() { 
        try { guardar(nombreArchivo); }
        catch (IOException e) {
            println(
                "Error al guardar, revise el directorio .backups"
                + "para encontrar backups de su trabajo"
            );
            getLine();
        }
    }

}

class Main {
     
    public static void main(String[] args) {
        var editor = new EditorCSVPersonas();
        editor.run();
    }

}
