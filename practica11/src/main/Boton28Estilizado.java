/*Button con estilos CSS personalizados*/

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Boton28Estilizado extends Button {

    private static String claseCSS = "boton-estilizado";

    public Boton28Estilizado(
        String texto,
        EventHandler<ActionEvent> manejadorEvento
    ) {
        this.setText(texto);
        this.getStyleClass().add(claseCSS);
        this.setOnAction(manejadorEvento);
    }

}
