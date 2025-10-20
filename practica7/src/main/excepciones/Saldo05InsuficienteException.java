package excepciones;

public class Saldo05InsuficienteException extends ExceptionDavilaBase {
    
    public Saldo05InsuficienteException() {
        super();
    }

    public Saldo05InsuficienteException(String mensaje) {
        super(mensaje);
    }

    public Saldo05InsuficienteException(Throwable causa) {
        super(causa);
    }

    public Saldo05InsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
