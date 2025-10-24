package org.example.reloj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelojDigital {

    // Define el formato de hora (ej: HH:mm:ss -> 10:30:15)
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Método público para obtener la hora actual del sistema formateada.
     * @return La hora actual como String.
     */
    public String getHoraFormateada() {
        LocalDateTime ahora = LocalDateTime.now();
        return ahora.format(FORMATTER);
    }
}