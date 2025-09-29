
import static util.FmtIO.println;
import figura.*;

class Main {
    public static void main(String[] args) {
        println("Calculadora de Figuras Geometricas\n");

        println("Inicializando Calculadora...");
        var c = new CalculadoraGeometrica4122(
            CalculadoraGeometrica4122.Op.PERIMETRO
        );
        println(c.getResumen(), "\n");

        println("Agregando Figuras...");
        c.pushFigura((Figura28) new CirculoDavila(1));
        c.pushFigura((Figura28) new CirculoDavila(2));
        c.pushFigura((Figura28) new CirculoDavila(3));
        c.pushFigura((Figura28) new RectanguloDavila(1,5));
        c.pushFigura((Figura28) new RectanguloDavila(4,3));
        c.pushFigura((Figura28) new TrianguloDavila(4,3));
        c.pushFigura((Figura28) new TrianguloDavila(12,3));
        c.pushFigura((Figura28) new TrianguloDavila(2,6));
        println("Estado Actual...");
        println(c.getResumen(), "\n");

        println("Calculando Valores en Modo Perimetro...");
        println(
            String.format(
                "Suma de Perimetros: %.2f", c.sum()
            ),
            "Figura con Perimetro Maximo:",
            String.format(
                "%s -> %.2f", c.getMax(), c.max()
            ),
            "Figura con Perimetro Minimo",
            String.format(
                "%s -> %.2f", c.getMin(), c.min()
            ),
            "\n"
        );
       
        c.setModo(CalculadoraGeometrica4122.Op.AREA);

        println("Calculando Valores en Modo Area...");
        println(
            String.format(
                "Suma de Perimetros: %.2f", c.sum()
            ),
            "Figura con Perimetro Maximo:",
            String.format(
                "%s -> %.2f", c.getMax(), c.max()
            ),
            "Figura con Perimetro Minimo",
            String.format(
                "%s -> %.2f", c.getMin(), c.min()
            ),
            "\n"
        );

        println("Estado Actual...");
        println(c.getResumen(), "\n");
        
        println("Eliminando Circulos y Rectangulos...");
        c.purgeFiguras(fig -> fig instanceof CirculoDavila);
        c.purgeFiguras(fig -> fig instanceof RectanguloDavila);
        
        println("Estado Actual...");
        println(c.getResumen());
    }
}
