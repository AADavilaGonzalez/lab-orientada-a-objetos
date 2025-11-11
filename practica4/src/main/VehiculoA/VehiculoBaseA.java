package VehiculoA;

import static util.ArgCheck.*; 

public abstract class VehiculoBaseA {

    private final String marca;
    private final String modelo;
    private double precioBase;
    private int kilometraje;

    protected VehiculoBaseA(
        String marca,
        String modelo,
        double precioBase,
        int kilometraje
    ) {
        this.marca = argNoVacio(marca, "marca");
        this.modelo = argNoVacio(modelo, "modelo");
        this.precioBase = validarPrecioBase(precioBase);
        this.kilometraje = validarKilometraje(kilometraje);
    }


    public String getMarca() {return marca;}
    public String getModelo() {return modelo;}


    public double getPrecioBase() {return precioBase;}

    private double validarPrecioBase(double precioBase) {
       if(precioBase <= 0) {
            throw argInv("El precio debe ser mayor a 0");
        }
       return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = validarPrecioBase(precioBase);
    }


    public int getKilometraje() {return kilometraje;}

    private int validarKilometraje(int kilometraje) {
        if(kilometraje < 0) {
            throw argInv("El kilometraje debe ser mayor o igual a 0"); 
        }
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = validarKilometraje(kilometraje);
    }


    //Metodos base compuestos de la interfaz abstracta
    public double getPrecioDeVenta() {
        return precioBase*Math.max(
            1 - (getKilometraje()/getMaxKilometraje()),
            getMinPorcentaje()
        );
    }

    public double getPrecioFinal() {
       return getPrecioDeVenta()*getDescuento(); 
    }
   

    public String getResumen() {
        return String.format(
              "| %s %s |\n"
            + "Tipo: %s\n"
            + "Precio de Venta: $%.2f\n"
            + "Precio Final: $%.2f\n"
            + "Kilometraje: %d km\n",
            marca,
            modelo,
            getTipoVehiculo(),
            getPrecioDeVenta(),
            getPrecioFinal(),
            kilometraje
        );
    }

    //Metodos a impementar en cada clase para permitir la
    //implmementacion por defecto de los metodos base
    public abstract String getTipoVehiculo(); 
    protected abstract int getMaxKilometraje();
    protected abstract double getMinPorcentaje(); 
    protected abstract double getDescuento();

}
