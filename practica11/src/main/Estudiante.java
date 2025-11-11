
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Estudiante {
    private final StringProperty nombre;
    private final IntegerProperty edad;
    private final DoubleProperty promedio;

    public Estudiante(String nombre, int edad, double promedio) {
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleIntegerProperty(edad);
        this.promedio = new SimpleDoubleProperty(promedio);
    }

    public StringProperty propiedadNombre() { return nombre; }
    public String getNombre() { return nombre.get(); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }

    public IntegerProperty propiedadEdad() { return edad; }
    public int getEdad() { return edad.get(); }
    public void setEdad(int edad) { this.edad.set(edad); }

    public DoubleProperty propiedadPromedio() { return promedio; }
    public double getPromedio() { return promedio.get(); }
    public void setPromedio(double promedio) { this.promedio.set(promedio); }
}
