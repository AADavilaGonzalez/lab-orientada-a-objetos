package util;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public final class ArgCheck {
 
    public static IllegalArgumentException argInv(String msg) {
       return new IllegalArgumentException(msg); 
    }

    public static <T> T argNoNulo(T arg, String nombreArg) {
        if(arg == null) {
            throw argInv(
                String.format(
                    "el argumento %s no puede ser nulo", nombreArg
                )
            );
        }
        return arg;
    }

    public static <T> T argNoVacio(T arg, String nombreArg) {
        arg = argNoNulo(arg, nombreArg);
        try {
            Method isEmpty = arg.getClass().getMethod("isEmpty");
            if((boolean)isEmpty.invoke(arg)) {
                throw argInv(
                    String.format(
                        "el argumento %s no puede ser vacio", nombreArg
                    )
                );
            }
        } catch(NoSuchMethodException e) {
            throw argInv(
                String.format(
                    "el argumento pasado como %s no soporta el metodo isEmpty",
                    nombreArg
                )
            );
        } catch(IllegalAccessException e) {
            throw argInv(
                String.format(
                    "el argumento pasado como %s tiene su metodo isEmpty"
                    + "marcado como privado, en vez de publico", nombreArg
                )
            );
        } catch(InvocationTargetException e) {
            throw argInv(
                String.format(
                    "ocurrio una excepcion invocando al metodo isEmpty del"
                    + "argumento %s: %s", nombreArg, e.getMessage()
                )
            );
        }
        return arg;
    }

    public static <T extends Comparable<T>> T argMayorA(
        T arg,
        String nombreArg,
        T valor
    ) {
        arg = argNoNulo(arg, nombreArg);
        if(arg.compareTo(valor) <= 0) {
            throw argInv(
                String.format(
                    "el argumento %s debe ser mayor a ",
                    nombreArg
                ) + valor.toString()
            );
        }
        return arg;
    }

    public static <T extends Comparable<T>> T argMayorOIgualA(
        T arg,
        String nombreArg,
        T valor
    ) {
        arg = argNoNulo(arg, nombreArg);
        if(arg.compareTo(valor) < 0) {
            throw argInv(
                String.format(
                    "el argumento %s debe ser mayor o igual a ",
                    nombreArg
                ) + valor.toString()
            );
        }
        return arg;
    }


    public static <T extends Comparable<T>> T argMenorA(
        T arg,
        String nombreArg,
        T valor
    ) {
        arg = argNoNulo(arg, nombreArg);
        if(arg.compareTo(valor) >= 0) {
            throw argInv(
                String.format(
                    "el argumento %s debe ser menor a ",
                    nombreArg
                ) + valor.toString()
            );
        }
        return arg;
    }

    public static <T extends Comparable<T>> T argMenorOIgualA(
        T arg,
        String nombreArg,
        T valor
    ) {
        arg = argNoNulo(arg, nombreArg);
        if(arg.compareTo(valor) > 0) {
            throw argInv(
                String.format(
                    "el argumento %s debe ser menor o igual a ",
                    nombreArg
                ) + valor.toString()
            );
        }
        return arg;
    }


    public static <T extends Comparable<T>> T argIgualA(
        T arg,
        String nombreArg,
        T valor
    ) {
        arg = argNoNulo(arg, nombreArg);
        if(arg.compareTo(valor) == 0) {
            throw argInv(
                String.format(
                    "el argumento %s debe ser igual a ",
                    nombreArg
                ) + valor.toString()
            );
        }
        return arg;
    }


    public static <T extends Comparable<T>> T argEnRango(
        T arg,
        String nombreArg,
        T valorMin,
        T valorMax
    ) {
        arg = argNoNulo(arg, nombreArg);
        if(arg.compareTo(valorMin) < 0 || arg.compareTo(valorMax) > 0) {
            throw argInv(
                String.format(
                    "el argumento %s debe estar entre ",
                    nombreArg
                ) + valorMin.toString() + " y " + valorMax.toString()
            );
        }
        return arg;
    }

}
