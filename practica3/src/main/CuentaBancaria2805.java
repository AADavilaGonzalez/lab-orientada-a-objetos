import java.time.LocalDate;

class CuentaBancaria2805 {
    private final String numeroDeCuenta;
    private final LocalDate fechaDeCreacion;
    private final ClienteDavila propietario;
    private boolean cuentaActiva;
    private double balance;


    protected CuentaBancaria2805(ClienteDavila cliente, double balanceInicial) {
        if(cliente == null) {
            throw new IllegalArgumentException(
                "El cliente no puede ser nulo"
            );
        }
        if(balanceInicial < 0) {
            throw new IllegalArgumentException(
                "El balance inicial no puede ser menor a cero"
            );
        }
        numeroDeCuenta = generarNumeroDeCuenta();
        fechaDeCreacion = LocalDate.now();
        propietario = cliente;
        cuentaActiva = true;
        balance = balanceInicial;
    }


    private static long siguienteNumeroDeCuenta = 1;
    private static final int longitudNumeroDeCuenta = 18;
    private String generarNumeroDeCuenta() {
        var nc = String.format(
            "%0" + longitudNumeroDeCuenta + "d",
            siguienteNumeroDeCuenta
        );
        if(nc.length() > 18) {
            throw new IllegalStateException(
                "No es posible seguir generando numeros de cuenta"
            );
        }
        ++siguienteNumeroDeCuenta;
        return nc;
    }
    public String getNumeroDeCuenta() {return numeroDeCuenta;}


    public LocalDate getFechaDeCreacion() {return fechaDeCreacion;}
    

    public ClienteDavila getPropietario() {return propietario;}


    public boolean getCuentaActiva() {return cuentaActiva;}
    public void setCuentaActiva(boolean estado) {cuentaActiva=estado;}


    public double getBalance() {return balance;}
    
    public boolean transferir(double monto, CuentaBancaria2805 cuenta) {
        if(monto > balance) return false;
        balance-=monto;
        cuenta.balance+=monto;
        return true;
    }

    //Acceso a los elementos protected del cliente
    public String getEstadoDeCuenta() {
        return "Estado de Cuenta al " + LocalDate.now() + "\n"
            +  "Propietario: " + propietario.nombre + "\n"
            +  "No. de Cliente: " + String.valueOf(propietario.numeroDeCliente) + "\n"
            +  "No. de Cuenta: " + numeroDeCuenta + "\n"
            +  "Saldo Actual: " + balance + "\n";
    }


    @Override
    public String toString() {
        return String.format(
              "CuentaBancaria {\n"
            + "Numero De Cuenta: %s\n"
            + "Propietario: %s\n"
            + "Balance Actual: %.2f\n"
            + "Activa: %s\n"
            + "Fecha de Creacion: %s\n"
            + "}",
            numeroDeCuenta,
            propietario.nombre,
            balance,
            cuentaActiva,
            fechaDeCreacion
        );
    }
}













