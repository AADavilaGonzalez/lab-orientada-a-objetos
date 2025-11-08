/*
 * Métodos para guardar/cargar objetos serializados -> metodos auxiliares
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PersonaASerializable {
    
    private String nombre; 
    private String preferencia;
    private int edad;

    public PersonaASerializable(String nombre, String preferencia, int edad) {
        this.nombre = nombre;
        this.preferencia = preferencia;
        this.edad =  edad;
    }

    public static String SerializarCSV(PersonaASerializable persona) {
        return String.format(
            "\"%s\", \"%s\", %d",
            persona.nombre, persona.preferencia, persona.edad
        );
    }

    private static Pattern regexCSV= Pattern.compile(
        "(.+)?\\s*,\\s*(.+)?\\s*,(\\d+)?"
    );

    private static String eliminarComas(String str) {
        if(str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length());
        }
        return str;
    }

    public static PersonaASerializable DeserializarCSV(String csv) {
        Matcher m = regexCSV.matcher(csv);
        if(!m.matches()) { return null; }
        String nombre = eliminarComas(m.group(1));
        String preferencia = eliminarComas(m.group(2));
        Integer edad;
        try {
            edad = Integer.parseInt(m.group(3));
        } catch(RuntimeException e) {
            edad = null; 
        }
        return new PersonaASerializable(
            nombre.isEmpty() ? null : nombre,
            preferencia.isEmpty() ? null : preferencia,
            edad
        );
    }

    @Override
    public String toString() {
        return String.format(
            "nombre: %s", "preferencia: %s, edad: %d",
            nombre, preferencia, edad
        );
    }

}
