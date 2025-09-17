import java.util.Scanner;

class FmtIO {
    
    private static String _prompt = "> ";
    private static boolean scan_line = true;
    private static Scanner sc = new Scanner(System.in);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(
            () -> {sc.close();}
        ));
    }

    public static void prompt() {System.out.print(_prompt);}

    public static void setPrompt(String s) {_prompt = s;}

    public static void setScanLine(boolean b) {scan_line = b;}
    
    public static void print(String... strs) {
        if(strs == null || strs.length == 0) return;
        System.out.print(strs[0]);
        for(int i=1; i<strs.length; ++i) {
            System.out.print(strs[i]+" ");
        }
    }

    public static void println(String... strs) {
        if(strs == null || strs.length == 0) return;
        for(var s : strs) {System.out.print(s+"\n");} 
    }

    public static int getInt() {
        int i=0;
        prompt();
        while(!sc.hasNextInt()) {
            System.out.println("Introduzca un entero");
            sc.nextLine();
            prompt();
        }
        i = sc.nextInt();
        if(scan_line) {sc.nextLine();}
        return i;
    }

    public static int getInt(int min, int max) {
        int i;
        while(true){
            i = getInt();
            if(i>=min && i<=max) break;
            System.out.println(String.format(
                "Ingrese un valor entre %d y %d", min, max
            ));
        }
        return i;
    }

    public static Integer getIntOpt() {
        Integer i;
        if(sc.hasNextInt()) {
            i = sc.nextInt();
        } else {
            i = null;
        }
        if(scan_line) {sc.nextLine();}
        return i;
    }

    public static String getStr() {
        prompt();
        var s = sc.next();
        if(scan_line) {sc.nextLine();}
        return s;
    }

    public static String getLine() {
        prompt();
        return sc.nextLine();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
