import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import figura.Figura28;

class CalculadoraGeometrica4122 implements CalculableA {
  
    enum Op {
        PERIMETRO(Figura28::getPerimetro),
        AREA(Figura28::getArea);

        private final Function<Figura28, Double> op;

        Op(Function<Figura28,Double> func) {
            this.op = func;
        }

        public double calc(Figura28 fig) {return op.apply(fig);}

        @Override
        public String toString() {
            return switch(this) {
                case PERIMETRO -> "Perimetro";
                case AREA -> "Area";
            };
        }
    }

    private Op op;
    private List<Figura28> stack;

    public CalculadoraGeometrica4122(Op op) {
        this.op = op;
        this.stack = new ArrayList<>();
    }
 
    public CalculadoraGeometrica4122() {this(Op.PERIMETRO);}

    public void setModo(Op op) {this.op = op;}

    public void pushFigura(Figura28 fig) {stack.add(fig);}
    public void popFigura(Figura28 fig) {
        if(stack.size()!=0) {
            stack.remove(stack.size()-1);
        }
    }
    public void purgeFiguras(Predicate<Figura28> condicion) {
        stack.removeAll(
            stack.stream()
                 .filter(condicion)
                 .collect(Collectors.toList())
        );
    }

    public void clearFiguras() {stack.clear();}

    public double sum() {
        return stack.stream().reduce(0d,
            (suma, fig) -> suma+op.calc(fig),
            Double::sum
        );
    }

    public Figura28 getMin() throws NoSuchElementException {
        return stack.stream()
                    .min((fig1, fig2) -> Double.compare(
                        op.calc(fig1), op.calc(fig2))
                    ).orElseThrow();
    }

    public double min() throws NoSuchElementException {
        return op.calc(getMin());
    }

    public Figura28 getMax() throws NoSuchElementException {
        return stack.stream()
                    .max((fig1,fig2) -> Double.compare(
                        op.calc(fig1), op.calc(fig2))
                    ).orElseThrow();
    }

    public double max() throws NoSuchElementException {
        return op.calc(getMax());
    }

    public String getResumen() {
        var figuras = stack.isEmpty() ?
            "" : "\n" + stack.stream()
                             .map(fig -> fig.toString())
                             .collect(Collectors.joining("\n")) + "\n";

        return String.format(
              "<Calculadora Geometrica>\n"
            + "Operacion: %s\n"
            + "Figuras: [%s]\n",
            op, figuras
        );
    }

}
