/*
 * Lectura/escritura de archivos de texto -> para modificar csv
 * Manejo de archivos binarios -> para escribir los bkp de forma eficiente
 * Operaciones con directorios -> para enumerar archivos encontrados
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

class GestorArchivosDavila4122 {

    private Path cwd;

    public GestorArchivosDavila4122(String dir) {
        this.cwd = Path.of(dir)
                        .toAbsolutePath()
                        .normalize();
    }

    public List<String> leerArchivo(String nombre)
        throws IOException
    {
        try {
            return Files.readAllLines(cwd.resolve(nombre));
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar leer el archivo", e
            );
        }
    }

    public byte[] leerArchivoBin(String nombre) throws IOException {
        try {
            return Files.readAllBytes(cwd.resolve(nombre));
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar leer el archivo", e
            );
        }
    }

    public void escribirArchivo(String nombre, String contenido)
        throws IOException {
        try {
            Files.writeString(cwd.resolve(nombre), contenido);
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar leer el archivo", e
            );
        } 
    }

    public void escribirArchivo(String nombre, Iterable<String> contenido)
        throws IOException
    {
        try {
            Path path = cwd.resolve(nombre);
            if(!Files.isDirectory(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            Files.write(cwd.resolve(nombre), contenido);
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar escribir el archivo", e
            );
        }
    }

    public void escribirArchivoBin(String nombre, byte[] contenido)
        throws IOException
    {
        try {
            Path path = cwd.resolve(nombre);
            if(!Files.isDirectory(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            Files.write(cwd.resolve(nombre), contenido);
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar escribir el archivo", e
            );
        }
    }

    public void cd(String nombre)
        throws IOException
    {
        Path dir = Path.of(nombre);
        if(!dir.isAbsolute()) {
            dir = cwd.resolve(dir).normalize();
        }

        if(!Files.exists(dir)) {
            throw new IOException(
                "La dirección " + dir.toString() +  " no existe"
            );
        }
        if(!Files.isDirectory(dir)) {
            throw new IOException(
                "La dirección " + dir.toString() + " no representa un directorio"
            );
        }
        if(!Files.isExecutable(dir)) {
            throw new IOException(
                "La dirección " + dir.toString() + " no es accesible"
            );
        }

        cwd = dir;
    }

    public List<String> enlistarArchivos()
        throws IOException
    {
        return Files.list(cwd).filter(
            (f) -> Files.isRegularFile(f)
                && Files.isWritable(f)
                && Files.isReadable(f)
        ).map((f) -> f.toString()).toList();
    }

    public void crearArchivo(String nombre)
        throws IOException
    {
        try {
            Files.createFile(cwd.resolve(nombre));
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar crear el archivo", e
            );
        }
    }

    public void eliminarArchivo(String nombre)
        throws IOException
    {
        try {
            Files.deleteIfExists(cwd.resolve(nombre));
        }
        catch(IOException e) {
            throw new IOException(
                "Error al intentar crear el archivo", e
            );
        }
    }
}
