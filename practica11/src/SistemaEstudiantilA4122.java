/*
 * Ventana principal con menu personalizado
 * Formularios CRUD para gestion de estudiantes
 * Tabla con datos dinamicos
 * Manejo de eventos con mouse y teclado
 * Validacion de formularios en tiempo real
 * Dialogos modales personalizados (popups de confirmacion)
 * Compilar a JAR!
 * Manual de usuario
 */


import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class SistemaEstudiantilA4122 extends Application {

    private Estudiante estudianteSeleccionado = null; 

    @Override
    public void start(Stage escenarioPrincipal) {
        //Colleccion de datos de los estudiantes 
        ObservableList<Estudiante> datos = FXCollections.observableArrayList();

        //Cuadriculo
        GridPane cuadricula = new GridPane();
        cuadricula.setHgap(10);
        cuadricula.setVgap(5);
        cuadricula.setPadding(new Insets(10));
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(75);
        col2.setHgrow(Priority.ALWAYS);
        cuadricula.getColumnConstraints().addAll(col1, col2);


        //Tabla
        TableView<Estudiante> tabla = new TableView<>();
        tabla.setItems(datos);
        GridPane.setColumnIndex(tabla, 1);
        GridPane.setRowIndex(tabla, 0);
        GridPane.setHalignment(tabla, HPos.CENTER);
        
        TableColumn<Estudiante, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(
            celda -> celda.getValue().propiedadNombre()
        );

        TableColumn<Estudiante, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(
            celda -> celda.getValue().propiedadEdad().asObject()
        );

        TableColumn<Estudiante, Double> colPromedio = new TableColumn<>("Promedio");
        colPromedio.setCellValueFactory(
            celda -> celda.getValue().propiedadPromedio().asObject()
        );

        tabla.getColumns().addAll(
            Arrays.asList(colNombre, colEdad, colPromedio)
        );
       

        //Campos y formulario
        var campoNombre = new CampoDavilaValidado<String>(
            "nombre", CampoDavilaValidado.formateadorString(255)
        );
        var campoEdad = new CampoDavilaValidado<Integer>(
            "edad", CampoDavilaValidado.formateadorEntero(1, 150)
        );
        var campoPromedio = new CampoDavilaValidado<Double>(
            "promedio", CampoDavilaValidado.formateadorDouble(0, 100)
        );

        VBox formulario = new VBox(10);
        formulario.getChildren().addAll(campoNombre, campoEdad, campoPromedio);
        GridPane.setColumnIndex(formulario, 0);
        GridPane.setRowIndex(formulario, 0);
        GridPane.setHalignment(formulario, HPos.CENTER);

        var botonCrear = new Boton28Estilizado("Crear", (e) -> {
            System.out.println("Entramos al handler de crear");
            String nombre = campoNombre.getValue();
            Integer edad = campoEdad.getValue();
            Double promedio = campoPromedio.getValue();
            System.out.println(nombre);
            System.out.println(edad);
            System.out.println(promedio);

            if (nombre != null && edad != null && promedio != null) {
                datos.add(new Estudiante(nombre, edad, promedio));
                campoNombre.setText("");
                campoEdad.setText("");
                campoPromedio.setText("");
            }
        });

        var botonActualizar = new Boton28Estilizado("Actualizar", (e) -> {
  
            if (estudianteSeleccionado != null) {
                String nombre = campoNombre.getValue();
                Integer edad = campoEdad.getValue();
                Double promedio = campoPromedio.getValue();

                if (nombre != null) estudianteSeleccionado.setNombre(nombre);
                if (edad != null) estudianteSeleccionado.setEdad(edad);
                if (promedio != null) estudianteSeleccionado.setPromedio(promedio);
                
                tabla.refresh(); 
            }
        });

        HBox botones = new HBox(10, botonCrear, botonActualizar);
        formulario.getChildren().add(botones);


        //Handler de seleccion de la tabla
        tabla.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                estudianteSeleccionado = newSelection;         
                campoNombre.setText(newSelection.getNombre());
                campoEdad.setText(String.valueOf(newSelection.getEdad()));
                campoPromedio.setText(String.valueOf(newSelection.getPromedio()));
            } else {
                estudianteSeleccionado = null; 
            }
        });
  

        //Agregar todo a la cuadriculo y poner la cuadricula como escena
        cuadricula.getChildren().addAll(formulario, tabla);
        Scene escena = new Scene(cuadricula, 640, 480);

        escenarioPrincipal.setTitle("Sistema Estudiantil");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }
}

class Main {
    public static void main(String[] args) {
        Application.launch(
            SistemaEstudiantilA4122.class, args
        );
    }
}
