package empresa;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Random;

import empleado.*;

public class EmpresaTIA4122 {

    private GerenteDavila gerenteDesarrollo;
    private List<DesarrolladorDavila> desarrolladores;

    private GerenteDavila gerenteVentas;
    private List<VendedorDavila> vendedores;

    public EmpresaTIA4122(
        GerenteDavila gerenteDesarrollo,
        List<DesarrolladorDavila> desarrolladores,
        GerenteDavila gerenteVentas,
        List<VendedorDavila> vendedores
    ) {
        gerenteDesarrollo.asignarSubordinados(desarrolladores);
        this.gerenteDesarrollo = gerenteDesarrollo;
        this.desarrolladores = desarrolladores;

        gerenteVentas.asignarSubordinados(vendedores);
        this.gerenteVentas = gerenteVentas;
        this.vendedores = vendedores; 
    }

    public GerenteDavila getGerenteDesarrollo() {
        return gerenteDesarrollo;
    }
    public List<DesarrolladorDavila> getDesarolladores() {
        return desarrolladores;
    }

    public GerenteDavila getGerenteVentas() {
        return gerenteVentas;
    }
    public List<VendedorDavila> getVendedores() {
        return vendedores;
    }

    public EmpleadoAD getEmpleadoDelMes() {
        List<EmpleadoAD> empleadosSobresalientes = Stream.concat(
            desarrolladores.stream(), vendedores.stream())
            .filter(e -> e.evaluar().desempeno() >= 0.7d)
            .collect(Collectors.toList());

        if(empleadosSobresalientes.isEmpty()) {return null;}
        Random rand = new Random();
        return empleadosSobresalientes.get(
            rand.nextInt(empleadosSobresalientes.size())
        ); 
    }

    public void trabajar() {
        Stream<EmpleadoAD> empleados = Stream.concat(desarrolladores.stream(),vendedores.stream());
        Stream<EmpleadoAD> gerentes  = Stream.of(gerenteDesarrollo, gerenteVentas);
        Stream.concat(empleados, gerentes).forEach(e -> e.trabajar());
    }
}
