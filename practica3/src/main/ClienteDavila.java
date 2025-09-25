import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class ClienteDavila {

    private static IllegalArgumentException argInv(String msg) {
       return new IllegalArgumentException(msg); 
    }

    private static String atribStrNoNulo(String s, String atrib) {
        if(s == null) {
            throw argInv(
                String.format("El atributo %s no puede ser nulo", atrib)
            );
        }
        return s;
    }

    private static String atribStrNoVacio(String s, String atrib) {
        if(s == null || s.isEmpty()) {
            throw argInv(
                String.format(
                    "El atributo %s no puede ser vacio o nulo", atrib
                )
            );
        }
        return s;
    }

    protected final String numeroDeCliente;
    protected String nombre;
    protected String correoElectronico;
    protected String numeroDeTelefono;
    protected String direccion;
    protected LocalDate fechaDeNacimiento;
    private final List<CuentaBancaria2805> cuentas;

    public ClienteDavila(
        String nombre,
        String correoElectronico,
        String numeroDeTelefono,
        String direccion,
        LocalDate fechaDeNacimiento
    ) {  
        numeroDeCliente = generarNumeroDeCliente();
        this.nombre = atribStrNoVacio(nombre, "nombre");
        this.correoElectronico = validarCorreoElectronico(correoElectronico);
        this.numeroDeTelefono = validarNumeroDeTelefono(numeroDeTelefono);
        this.direccion = atribStrNoNulo(direccion, "direccion");
        this.fechaDeNacimiento = validarFechaDeNacimiento(fechaDeNacimiento);
        cuentas = new ArrayList<>();
    }   
   

    private static long siguienteNumeroDeCliente = 1;
    private static final int longitudNumeroDeCliente = 15;
    private String generarNumeroDeCliente() {
        var nc = String.format(
            "%0" + longitudNumeroDeCliente + "d",
            siguienteNumeroDeCliente
        );
        if(nc.length() > 18) {
            throw argInv("No es posible seguir generando numeros de cliente");
        }
        ++siguienteNumeroDeCliente;
        return nc;
    }
    public String getNumeroDeCliente() {return numeroDeCliente;}

    public String getNombre() {return nombre;}
    public void setNombre(String nom) {
        nombre = atribStrNoVacio(nom, "nombre");
    }


    public String getCorreoElectronico() {return correoElectronico;}
    private static Pattern patronCorreo = Pattern.compile("^(\\w+)@(\\w+\\.\\w+)$");
    private static String validarCorreoElectronico(String s) {
        s = atribStrNoVacio(s, "correoElectronico");
        Matcher m = patronCorreo.matcher(s);
        if(!m.matches()) {
            throw argInv("formato incorrecto");
        }
        String local = m.group(1);
        String dominio = m.group(2);
        if(local.length() > 64) {
            throw argInv(
                "el segmento local debe ser como maximo de 64 caracteres"
            );
        }
        if(dominio.length() > 255) {
            throw argInv(
                "el segmento de dominio debe ser como maximo de 255 caracteres"
            );
        }
        return s;
    }
    public void setCorreoElectronico(String ce) {
        correoElectronico = validarCorreoElectronico(ce);
    }


    public String getNumeroDeTelefono() {return numeroDeTelefono;}
    private static String validarNumeroDeTelefono(String s) {
        s = atribStrNoVacio(s, "numeroDeTelefono");
        if(s.length() < 10 || s.length() > 20) {
            throw argInv(
                "numeroDeTelefono debe tener entre 10 y 20 caracteres"
            );
        }
        if(!s.chars().allMatch(Character::isDigit)) {
            throw argInv(
                "numeroDeTelefono debe de conformarse solo de digitos"
            );
        }
        return s;
    }
    public void setNumeroDeTelefono(String nt) {numeroDeTelefono = nt;}


    public String getDireccion() {return direccion;}
    public void setDireccion(String dir) {direccion = dir;}


    public LocalDate getFechaDeNacimiento() {return fechaDeNacimiento;}
    private static int edadMinimaDeRegistro = 16;
    private static LocalDate validarFechaDeNacimiento(LocalDate f) {
        var edad = Period.between(f, LocalDate.now());
        if(edad.getYears() < edadMinimaDeRegistro) {
            throw argInv(
                String.format(
                    "la edad minima es de %d años",
                    edadMinimaDeRegistro
                )
            );
        }
        return f;
    }
    public void setFechaDeNacimiento(LocalDate fn) {
        fechaDeNacimiento = validarFechaDeNacimiento(fn);
    }


    public void abrirCuentaBancaria(double saldoInicial) {
        cuentas.add(new CuentaBancaria2805(this, saldoInicial));
    }


    public CuentaBancaria2805[] getCuentasBancarias() {
        return cuentas.toArray(new CuentaBancaria2805[0]);
    }


    public void cerrarCuentaBancaria(String numeroDeCuenta) {
        cuentas.removeIf(
            cuenta -> cuenta.getNumeroDeCuenta() == numeroDeCuenta
        );
    }


    @Override
    public String toString() {
        return String.format(
              "ClienteDavila {\n"
            + "Numero de Cliente: %s\n"
            + "Nombre: %s\n"
            + "Correo: %s\n"
            + "No. Telefono: %s\n"
            + "Direccion: %s\n"
            + "Fecha de Nacimiento: %S\n"
            + "<==Cuentas==>\n"
            + "%s\n"
            + "}",
            numeroDeCliente,
            nombre,
            correoElectronico,
            numeroDeTelefono,
            direccion,
            fechaDeNacimiento,
            cuentas.toString()
        );
    } 
}
