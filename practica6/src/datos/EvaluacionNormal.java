package datos;

import static util.ArgCheck.argEnRango;

public class EvaluacionNormal {
    private double _desempeno;
    
    public EvaluacionNormal(double eval) {
        _desempeno = argEnRango(eval, "eval", 0d, 1d);
    }

    public double desempeno() {return _desempeno;}
}
