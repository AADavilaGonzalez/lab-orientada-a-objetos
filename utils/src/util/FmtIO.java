package util;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.OutputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.io.IOException;

public final class FmtIO {
    
    private static String _prompt = "> ";
    private static boolean escanearPorLinea = true;
    private static Scanner sc = new Scanner(System.in);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(
            () -> {sc.close();}
        ));
    }

    public static void prompt() {System.out.print(_prompt);}

    public static void setPrompt(String s) {_prompt = s;}

    public static void setEscanearPorLinea(boolean b) {escanearPorLinea = b;}
    
    public static void print(OutputStream out, String... strs) throws IOException{
        if(strs == null || strs.length == 0) return;
        out.write(strs[0].getBytes());
        for(int i=1; i<strs.length; ++i) {
            out.write((" " + strs[i]).getBytes());
        }
    }

    public static void print(String... strs) {
        try {print(System.out, strs);} catch(IOException e) {}
    }
    
    public static void err(String... strs) {
        try {print(System.err, strs);} catch(IOException e) {}
    }

    public static void println(OutputStream out, String... strs) throws IOException {
        if(strs == null || strs.length == 0) return;
        for(var s : strs) {
            out.write((s + "\n").getBytes());
        } 
    }

    public static void println(String... strs) {
        try {println(System.out, strs);} catch(IOException e) {}
    }

    public static Integer scanInt() {
        Integer i = null;
        if(sc.hasNextInt()) { i = sc.nextInt(); }
        if(escanearPorLinea) { sc.nextLine(); } 
        return i;
    }

    public static int getInt(Predicate<Integer> cond, String msg) {
        while(true) {
            prompt();
            if(!sc.hasNextInt()) {
                System.out.println("Introduzca un entero");
                sc.nextLine();
                continue;
            }
            int i = sc.nextInt();
            sc.nextLine();
            if(cond == null || cond.test(i)) { return i; }
            System.out.println(msg != null ? msg : "Error" );
        }
    }

    public static int getInt() { return getInt(null, null); }
    public static int getInt(int min, int max) {
        return getInt(
            (i) -> i >= min && i <= max, 
            String.format("Ingrese un valor entre %d y %d", min, max)
        );
    }

    public static String scanString() {
        var s = sc.next();
        if(escanearPorLinea) {sc.nextLine();}
        return s;
    }
    public static String scanLine() { return sc.nextLine(); }

    public static String getString(Predicate<String> cond, String msg) {
        while(true) {
            prompt();
            String s = sc.next();
            sc.nextLine();
            if(cond == null || cond.test(s)) { return s; }
            System.out.println(msg != null ? msg : "Error");
        }
    }

    public static String getString() { return getString(null, null); }
    
    public static String getLine(Predicate<String> cond, String msg) {
        while(true) {
            prompt();
            String s = sc.nextLine();
            if(cond == null || cond.test(s)) { return s;}
            System.out.println(msg != null ? msg : "Error");
        }
    }

    public static String getLine() { return getLine(null, null); }

    private static Pattern regexFecha = Pattern.compile(
        "^(\\d{1,2})[\\s\\-\\/](\\d{1,2})[\\s\\-\\/](\\d{1,4})$"
    );

    public static LocalDate scanLocalDate() {
        int dia, mes, ano;
        var input = sc.next();
        if(escanearPorLinea) { sc.nextLine(); }
        Matcher m  = regexFecha.matcher(input);
        if(m.matches()) {
            dia = Integer.parseInt(m.group(1));
            mes = Integer.parseInt(m.group(2));
            ano = Integer.parseInt(m.group(3));
        }
        else { return null; }
        LocalDate fecha;   
        try { fecha = LocalDate.of(ano, mes, dia); }
        catch(DateTimeException e){ return null; }
        return fecha;
    }

    public static LocalDate getLocalDate(Predicate<LocalDate> cond, String msg) {
        while(true) {
            prompt();
            int dia, mes, ano;
            while(true) {
                var input = sc.nextLine().strip();
                Matcher m  = regexFecha.matcher(input);
                if(m.matches()) {
                    dia = Integer.parseInt(m.group(1));
                    mes = Integer.parseInt(m.group(2));
                    ano = Integer.parseInt(m.group(3));
                }
                else {
                    System.out.println(
                        "Introduzca una fecha con formato DIA-MES-AÑO"
                    );
                    continue;
                }
                LocalDate fecha;
                try { fecha = LocalDate.of(ano, mes, dia); }
                catch(DateTimeException e){
                    System.out.println("Introduzca una fecha valida");
                    continue;
                }
                if(cond == null || cond.test(fecha)) { return fecha; }
                System.out.println(msg != null ? msg : "Error");
            }
        }
    }

    public static LocalDate getLocalDate() { return getLocalDate(null, null); }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
