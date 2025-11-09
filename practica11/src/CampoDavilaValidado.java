/*Textfiled con validaciones*/

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class CampoDavilaValidado<T> extends TextField {

    private final TextFormatter<T> formateador;

    public CampoDavilaValidado(
        String textoPrompt,
        TextFormatter<T> formateador
    ) {
        this.setPromptText(textoPrompt);
        this.formateador = formateador;
        this.setTextFormatter(formateador); 
    }

    public T getValue() { return this.formateador.getValue(); }

    public static final TextFormatter<String> formateadorString(
        int longitudMax
    ) {
       
        UnaryOperator<Change> filtro = (cambio) -> {
            String texto = cambio.getControlNewText();
            if(texto.length() > longitudMax) {
                return null;
            }
            return cambio;
        };

        return new TextFormatter<String>(
            new DefaultStringConverter(),
            null,
            filtro
        );
    }

    public static final TextFormatter<Integer> formateadorEntero(
        int min,
        int max,
        Integer valorDefault
    ) { 

        UnaryOperator<Change> filto = (cambio) -> {
            String texto = cambio.getControlNewText();
            if(texto.isEmpty()) { return cambio; }

            int valor;
            try {
                valor = Integer.parseInt(texto); 
            } catch (NumberFormatException e) {
                return null;
            }
            if(valor < min || valor > max) { return null; }
            return cambio;
        };

        return new TextFormatter<Integer>(
            new IntegerStringConverter(),
            valorDefault,
            filto
        );

    }

    public static final TextFormatter<Integer> formateadorEntero(
        int min, int max
    ) { return formateadorEntero(min, max, null); }

    public static final TextFormatter<Double> formateadorDouble(
        double min,
        double max,
        Double valorDefault
    ) {
        
        UnaryOperator<Change> filtro = (cambio) -> {
            double valor;
            String texto = cambio.getControlNewText();

            if(texto.isEmpty()) { return cambio; }
            try {
                valor = Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                return null;
            }
            if(valor < min || valor > max) { return null; }
            return cambio;
        };

        return new TextFormatter<Double>(
            new DoubleStringConverter(),
            valorDefault,
            filtro
        );
    }

    public static final TextFormatter<Double> formateadorDouble(
        int min, int max
    ) { return formateadorDouble(min, max, null); }
}
