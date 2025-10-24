package org.example.calculadora;

import java.awt.Color;
import javax.swing.border.LineBorder;

public class BotonNumerico extends BotonCalculadora {

    private static final Color COLOR_NUMERICO = new Color(50, 50, 50); // Gris oscuro

    public BotonNumerico(String texto) {
        super(texto, COLOR_NUMERICO);
    }

    @Override
    protected void aplicarEstiloUnico() {
        // Estilo único para los números: un borde sutil
        setBorder(new LineBorder(Color.GRAY, 1));
        this.colorBase = COLOR_NUMERICO; // Asegura que el color base esté sincronizado
        this.colorPresionado = COLOR_NUMERICO.darker();
    }
}