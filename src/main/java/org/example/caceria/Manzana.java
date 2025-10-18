import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase Manzana: Objeto recolectable.
 */
public class Manzana extends JLabel {
    private static final int TAMANO = 30;

    public Manzana() {
        // Configuración visual
        setText("Manzana");
        setFont(getFont().deriveFont(25f));
        setSize(TAMANO, TAMANO);
        setHorizontalAlignment(CENTER);
    }

    /**
     * Reubica la manzana en una posición aleatoria dentro del contenedor.
     * @param panel El contenedor (JPanel o JFrame) donde se ubicará.
     */
    public void reubicar(JPanel panel) {
        Random rand = new Random();

        int maxX = panel.getWidth() - TAMANO;
        int maxY = panel.getHeight() - TAMANO;

        // Garantizar que el límite sea al menos 1 para evitar el error.
        int boundX = Math.max(1, maxX - 20);
        int boundY = Math.max(1, maxY - 20);

        int newX = rand.nextInt(boundX) + 10;
        int newY = rand.nextInt(boundY) + 10;

        setLocation(newX, newY);
        setVisible(true);
    }

    // Método para obtener el área de colisión
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}