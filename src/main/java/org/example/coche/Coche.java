
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Clase Coche: Define las propiedades y encapsula el comportamiento complejo.
 */
public class Coche extends JLabel {
    // Atributos
    private int posicionX;
    private Color colorActual;
    private static final int PASO = 30;

    public Coche(int x, int y, Color colorInicial) {
        this.posicionX = x;
        this.colorActual = colorInicial;

        //java.net.URL imageUrl = getClass().getResource("/imagenes/sedan.png");

        // Configuración visual (Hereda de JLabel)
        //ImageIcon iconoCoche = new ImageIcon(imageUrl);
        setText("\uD83D\uDE82");
        setFont(getFont().deriveFont(30f));
        setBounds(x, y, 50, 50);
        setBackground(colorInicial);
        setOpaque(true);
        setHorizontalAlignment(CENTER);
    }

    /**
     * Método Encapsulado: Contiene la lógica compleja de mover y cambiar color.
     * Este método se reutilizará en diferentes manejadores de eventos.
     */
    public void avanzarConEfecto(int limiteVentana) {
        // 1. Mover el coche (comportamiento de avanzar)
        int nuevaX = posicionX + PASO;

        // Si llega al final, reposicionar al inicio
        if (nuevaX > limiteVentana - getWidth()) {
            nuevaX = 0;
        }

        // Actualizar posición y GUI
        this.posicionX = nuevaX;
        setLocation(this.posicionX, getY());

        // 2. Cambiar el color (efecto visual)
        Random rand = new Random();
        Color nuevoColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        this.colorActual = nuevoColor;
        setBackground(nuevoColor);

        // Imprimir la acción para la consola
        System.out.println("Coche ha avanzado a X:" + posicionX + " y cambió a color: " + nuevoColor);
    }
}
