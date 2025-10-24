package org.example.calculadora;

import java.awt.Color;

public class BotonOperacion extends BotonCalculadora {

    private static final Color COLOR_OPERACION = new Color(255, 150, 0); // Naranja

    public BotonOperacion(String texto) {
        super(texto, COLOR_OPERACION);
    }

    @Override
    protected void aplicarEstiloUnico() {
        // Estilo único para las operaciones: Fuente de otro color
        setForeground(Color.BLACK);
        this.colorBase = COLOR_OPERACION; // Asegura que el color base esté sincronizado
        this.colorPresionado = COLOR_OPERACION.darker();
    }
}
