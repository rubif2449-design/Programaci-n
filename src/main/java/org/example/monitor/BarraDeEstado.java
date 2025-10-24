package org.example.monitor;

import javax.swing.*;
import java.awt.Color;

public class BarraDeEstado extends JProgressBar {

    // Atributos privados (Encapsulamiento)
    private int saludActual;
    private final int saludMaxima;

    // Constructor
    public BarraDeEstado(int saludMaximaInicial) {
        this.saludMaxima = saludMaximaInicial;
        this.saludActual = saludMaximaInicial;

        // Configuración visual de la barra
        setMinimum(0);
        setMaximum(saludMaxima);
        setValue(saludActual);
        setStringPainted(true); // Mostrar el texto (valor/máximo)

        // Estilo inicial
        actualizarVisual();
    }

    /**
     * Reduce la salud del objeto, asegurando que no caiga por debajo de cero.
     * @param cantidad La cantidad de daño a recibir.
     */
    public void recibirDaño(int cantidad) {
        // Lógica de validación de encapsulamiento
        if (cantidad > 0) {
            saludActual -= cantidad;
            if (saludActual < 0) {
                saludActual = 0;
            }
            actualizarVisual();
            System.out.println("¡Daño recibido! Salud actual: " + saludActual);
        }
    }

    /**
     * Aumenta la salud del objeto, asegurando que no exceda el máximo.
     * @param cantidad La cantidad a curar.
     */
    public void curar(int cantidad) {
        // Lógica de validación de encapsulamiento
        if (cantidad > 0) {
            saludActual += cantidad;
            if (saludActual > saludMaxima) {
                saludActual = saludMaxima;
            }
            actualizarVisual();
            System.out.println("¡Curación aplicada! Salud actual: " + saludActual);
        }
    }

    public int getSaludActual() {
        return saludActual;
    }

    public int getSaludMaxima() {
        return saludMaxima;
    }

    /**
     * Método privado que actualiza tanto el valor del JProgressBar
     * como su color, basado en la saludActual.
     */
    private void actualizarVisual() {
        // Actualiza el valor de la barra de progreso
        setValue(saludActual);

        // Actualiza el texto mostrado en la barra
        setString(saludActual + " / " + saludMaxima);

        // Lógica para cambiar el color (Ej: Si baja del 25%, cambia a rojo)
        double porcentaje = (double) saludActual / saludMaxima;

        if (porcentaje > 0.5) {
            setForeground(Color.GREEN.darker());
        } else if (porcentaje > 0.25) {
            setForeground(Color.ORANGE);
        } else {
            setForeground(Color.RED);
        }
    }
}
