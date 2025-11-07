class OperacionInvalidaException extends RuntimeException {
    
    public OperacionInvalidaException() {
        super();
    }

    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }

    public OperacionInvalidaException(Throwable causa) {
        super(causa);
    }

    public OperacionInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
