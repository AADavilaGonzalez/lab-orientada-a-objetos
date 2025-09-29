import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static util.ArgCheck.argInv;
import VehiculoA.*;

class Concesionaria4122 {

    private List<VehiculoBaseA> inventario;
    private float presupuesto;
    private float multMargen;

    public Concesionaria4122(
        float presupuestoInicial,
        float margenMinimo
    ) {
        inventario = new ArrayList<>();
        presupuesto = validarPresupuesto(presupuestoInicial);
        multMargen = margenAMult(validarMargenMinimo(margenMinimo));
    }


    public float getPresupuesto() {return presupuesto;}
    private float validarPresupuesto(float presupuesto) {
        if(presupuesto <= 0) {
            throw argInv("El presupuesto debe ser mayor a cero");
        }
        return presupuesto;
    }


    private float margenAMult(float margen) {return 1+margen/100;}
    private float multAMargen(float mult) {return (mult-1)*100;}
    private float validarMargenMinimo(float margenMinimo) {
        if(margenMinimo < 0 || margenMinimo > 100) {
            throw argInv("El margen minimo debe estar entre 0 y 100");
        }
        return margenMinimo;
    }
    public float getMargenMinimo() {return multAMargen(multMargen);}
    public void setMargenMinimo(float margenMinimo) {
        multMargen = margenAMult(validarMargenMinimo(margenMinimo));
    }


    public boolean esCompraRentable(
        VehiculoBaseA vehiculo,
        float precioDeCompra
    ) {
        if(vehiculo.getPrecioFinal() < precioDeCompra*multMargen)
            return false;
        return true;
    }

    public void comprarVehiculo(
        VehiculoBaseA vehiculo,
        float precioDeCompra
    ) {
        if(presupuesto < precioDeCompra) {
            throw argInv(
                "No existe el presupuesto para comprar este vehiculo"
            );
        }
        presupuesto -= precioDeCompra;
        inventario.add(vehiculo);
    }

    public VehiculoBaseA[] getVehiculos() {
        return inventario.toArray(VehiculoBaseA[]::new);
    }

    public VehiculoBaseA[] getVehiculos(Predicate<VehiculoBaseA> condicion) {
        return inventario.stream()
                         .filter(condicion)
                         .toArray(VehiculoBaseA[]::new);
    }

    public VehiculoBaseA getVehiculo(Predicate<VehiculoBaseA> condicion) {
        return inventario.stream()
                         .filter(condicion)
                         .findFirst()
                         .orElse(null);
    }

    public void venderVehiculo(VehiculoBaseA vehiculo) {
        if(!inventario.remove(vehiculo)) {
            throw argInv("El vehiculo no forma parte del inventario");
        }
        presupuesto += vehiculo.getPrecioFinal();
    }

    public void venderVehiculos(Predicate<VehiculoBaseA> condicion) {
        inventario.removeAll(
            inventario.stream()
                      .filter(condicion)
                      .peek(v -> presupuesto+=v.getPrecioFinal())
                      .collect(Collectors.toList())
        );
    }

    public String getResumen() {
        return String.format(
              "Presupuesto: %.2f\n"
            + "Margen de Venta: %.2f\n"
            + "<===Inventario===>\n"
            + inventario.stream()
                        .map(VehiculoBaseA::getResumen)
                        .collect(Collectors.joining("\n\n")),
            getPresupuesto(),
            getMargenMinimo()
        );
    }

}
