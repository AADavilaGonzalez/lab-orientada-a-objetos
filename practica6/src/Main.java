import static util.FmtIO.println;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.ArrayList;

import empleado.*;
import empresa.EmpresaTIA4122;

public class Main {

    public static void main(String args[]) {
       
        EmpresaTIA4122 empresa =  new EmpresaTIA4122(
            new GerenteDavila("Juanito", 40_000),
            new ArrayList<>(Arrays.asList(
                new DesarrolladorDavila("Nanganito", 22_000),
                new DesarrolladorDavila("Manganito", 15_000),
                new DesarrolladorDavila("Zanganito", 20_000)
            )),
            new GerenteDavila("Pepito", 35_000),
            new ArrayList<>(Arrays.asList(
                new VendedorDavila("Victor", 20_000),
                new VendedorDavila("Virgilio", 16_000),
                new VendedorDavila("Vicente", 18_000)
            )) 
        );

        for(var i=0; i<8; ++i) {empresa.trabajar();} 
        
        EmpleadoAD empleadoDelMes = empresa.getEmpleadoDelMes();

        println(
            "Reporte de empresa TIA 'Cualsea'",
            "",
            "Departamento de Desarrollo:",
            "",
            empresa.getGerenteDesarrollo().toPrettyStr(),
            "",
            empresa.getDesarolladores()
                   .stream()
                   .map(DesarrolladorDavila::toPrettyStr)
                   .collect(Collectors.joining("\n\n")),
            "",
            "Departamento de Ventas",
            "",
            empresa.getGerenteVentas().toPrettyStr(),
            "",
            empresa.getVendedores()
                   .stream()
                   .map(VendedorDavila::toPrettyStr)
                   .collect(Collectors.joining("\n\n")),
            "",
            "Empleado del Mes",
            "",
            empleadoDelMes != null ? empleadoDelMes.toPrettyStr() : "Ninguno :("
        );
    }

}
