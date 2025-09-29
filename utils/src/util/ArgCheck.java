package util;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public final class ArgCheck {
 
    public static IllegalArgumentException argInv(String msg) {
       return new IllegalArgumentException(msg); 
    }

    public static <T> T atribNoNulo(T obj, String nombreAtrib ) {
        if(obj == null) {
            throw argInv(
                String.format(
                    "El atributo %s no puede ser nulo", nombreAtrib
                )
            );
        }
        return obj;
    }

    public static <T> T atribNoVacio(T obj, String nombreAtrib) {
        obj = atribNoNulo(obj, nombreAtrib);
        try {
            Method isEmpty = obj.getClass().getMethod("isEmpty");
            if((boolean)isEmpty.invoke(obj)) {
                throw argInv(
                    String.format(
                        "El atributo %s no puede ser vacio", nombreAtrib
                    )
                );
            }
        } catch(NoSuchMethodException e) {
            throw argInv(
                String.format(
                    "El argumento pasado como %s no soporta el metodo isEmpty",
                    nombreAtrib
                )
            );
        } catch(IllegalAccessException e) {
            throw argInv(
                String.format(
                    "El argumento pasado como %s tiene su metodo isEmpty"
                    + "marcado como privado, en vez de publico", nombreAtrib
                )
            );
        } catch(InvocationTargetException e) {
            throw argInv(
                String.format(
                    "Ocurrio una excepcion invocando al metodo isEmpty del"
                    + "atributo %s: %s", nombreAtrib, e.getMessage()
                )
            );
        }
        return obj;
    }
}
