package banco;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

class LogPorFecha implements AutoCloseable {
   
    private static String dirLogs = "logs";
    FileHandler handler; 

    public LogPorFecha(String prefijo, LocalDate fecha) throws IOException {
        this.handler = new FileHandler(
            dirLogs + "/" + prefijo + "-" + fecha
        );
        this.handler.setFormatter(new SimpleFormatter());
    }

    public FileHandler getHandler() { return this.handler; }

    @Override
    public void close() { this.handler.close(); }
}
