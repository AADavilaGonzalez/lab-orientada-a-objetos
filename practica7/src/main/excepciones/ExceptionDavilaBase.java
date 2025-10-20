package excepciones;

public class ExceptionDavilaBase extends Exception {
    
    public ExceptionDavilaBase() {
        super();
    }

    public ExceptionDavilaBase(String mensaje) {
        super(mensaje);
    }

    public ExceptionDavilaBase(Throwable causa) {
        super(causa);
    }

    public ExceptionDavilaBase(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
